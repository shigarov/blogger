package shigarov.practicum.blogger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;

import java.sql.*;
import java.util.*;

@Repository
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        final String sql = """
                SELECT
                    p.id AS post_id,
                    p.title,
                    p.image,
                    p.text,
                    p.likes,
                    t.id AS tag_id,
                    t.name AS tag_name,
                    c.id AS comment_id,
                    c.text AS comment_text
                FROM posts p
                LEFT JOIN
                    posts_tags pt ON p.id = pt.post_id
                LEFT JOIN
                    tags t ON pt.tag_id = t.id
                LEFT JOIN
                    comments c ON p.id = c.post_id
                ORDER BY
                    p.id, t.id, c.id;
                """;

        List<Post> posts = jdbcTemplate.query(sql, new PostResultSetExtractor());

        return posts;
    }

    @Override
    public Page<Post> findAll(@NonNull Pageable pageable) {
        final String postCountSql = "SELECT count(1) AS row_count FROM posts";
        final long totalPosts = jdbcTemplate.queryForObject(
                postCountSql,
                (rs, rowNum) -> rs.getLong(1)
        );

        final String sql = """
                SELECT
                    p.id AS post_id,
                    p.title,
                    p.image,
                    p.text,
                    p.likes,
                    t.id AS tag_id,
                    t.name AS tag_name,
                    c.id AS comment_id,
                    c.text AS comment_text
                FROM (
                    SELECT *
                    FROM posts
                    ORDER BY id
                    LIMIT ? OFFSET ?
                ) p
                LEFT JOIN
                    posts_tags pt ON p.id = pt.post_id
                LEFT JOIN
                    tags t ON pt.tag_id = t.id
                LEFT JOIN
                    comments c ON p.id = c.post_id
                ORDER BY
                    p.id, t.id, c.id;
                """;

        final int limit = pageable.getPageSize();
        final long offset = pageable.getOffset();
        List<Post> posts = jdbcTemplate.query(sql, new PostResultSetExtractor(), limit, offset);

        return new PageImpl<>(posts, pageable, totalPosts);
    }

    @Override
    public Page<Post> findAllByTag(@NonNull Pageable pageable, long tagId) {
        final String postCountSql = """
                SELECT COUNT(DISTINCT p.id) AS post_count
                FROM posts p
                LEFT JOIN posts_tags pt ON p.id = pt.post_id
                LEFT JOIN tags t ON pt.tag_id = t.id
                WHERE t.id = ?;
                """;

        final long totalPosts = jdbcTemplate.queryForObject(postCountSql, Long.class, tagId);

        final String sql = """
                        SELECT
                            p.id AS post_id,
                            p.title,
                            p.image,
                            p.text,
                            p.likes,
                            t.id AS tag_id,
                            t.name AS tag_name,
                            c.id AS comment_id,
                            c.text AS comment_text
                        FROM (
                            SELECT DISTINCT p.id  -- Выбираем ID постов, связанных с указанным тегом
                            FROM posts p
                            LEFT JOIN posts_tags pt ON p.id = pt.post_id
                            LEFT JOIN tags t ON pt.tag_id = t.id
                            WHERE t.id = ?  -- Фильтр по ID тега
                            ORDER BY p.id
                            LIMIT ? OFFSET ?
                        ) filtered_posts
                        LEFT JOIN posts p ON filtered_posts.id = p.id  -- Получаем данные постов
                        LEFT JOIN posts_tags pt ON p.id = pt.post_id  -- Получаем все теги для этих постов
                        LEFT JOIN tags t ON pt.tag_id = t.id  -- Получаем данные тегов
                        LEFT JOIN comments c ON p.id = c.post_id  -- Получаем комментарии
                        ORDER BY
                            p.id, t.id, c.id;
                """;

        final int limit = pageable.getPageSize();
        final long offset = pageable.getOffset();
        List<Post> posts = jdbcTemplate.query(sql, new PostResultSetExtractor(), tagId, limit, offset);

        return new PageImpl<>(posts, pageable, totalPosts);
    }

    @Override
    public Optional<Post> findById(long postId) {
        final String sql = """
                SELECT
                    p.id AS post_id,
                    p.title,
                    p.image,
                    p.text,
                    p.likes,
                    t.id AS tag_id,
                    t.name AS tag_name,
                    c.id AS comment_id,
                    c.text AS comment_text
                FROM
                    posts p
                LEFT JOIN
                    posts_tags pt ON p.id = pt.post_id
                LEFT JOIN
                    tags t ON pt.tag_id = t.id
                LEFT JOIN
                    comments c ON p.id = c.post_id
                WHERE
                    p.id = ?;
                """;

        final List<Post> posts = jdbcTemplate.query(sql, new PostResultSetExtractor(), postId);
        final Post post = posts.isEmpty() ? null : posts.getFirst();

        return Optional.ofNullable(post);
    }

    private Long add(@NonNull final Post post) {
        var title = post.getTitle();
        var imageFileName = post.getImageFileName();
        var text = post.getText();
        var tagIds = post.getTagIds();
        var likes = post.getLikes();

       return addPost(title, imageFileName, text, tagIds, likes);
    }

    private Long addPost(
            @NonNull final String title,
            @Nullable final String imageFileName,
            @NonNull final String text,
            @Nullable final List<Long> tagIds,
            final int likes
    ) {
        // Шаг 1: Вставляем новый пост
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO posts (title, image, text, likes) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, title);
            ps.setString(2, imageFileName);
            ps.setString(3, text);
            ps.setInt(4, likes);
            return ps;
        }, keyHolder);

        // Шаг 2: Получаем ID вставленного поста
        final Long postId = keyHolder.getKey().longValue();

        // Шаг 3: Если переданы tagIds, добавляем связи с тегами
        if (tagIds != null && !tagIds.isEmpty()) {
            for (Long tagId : tagIds) {
                jdbcTemplate.update(
                        "INSERT INTO posts_tags (post_id, tag_id) VALUES (?, ?)",
                        postId, tagId
                );
            }
        }

        return postId;
    }

    private void update(@NonNull final Post post) {
        var id = post.getId();
        var title = post.getTitle();
        var image = post.getImageFileName();
        var text = post.getText();
        var tagIds = post.getTagIds();

        updatePost(id, title, image, text, tagIds);
    }

    private void updatePost(
            @NonNull Long postId,
            @NonNull String title,
            @Nullable String image,
            @NonNull String text,
            @Nullable List<Long> tagIds
    ) {
        // Шаг 1: Обновляем данные поста
        jdbcTemplate.update(
                "UPDATE posts SET title = ?, image = ?, text = ? WHERE id = ?",
                title, image, text, postId
        );

        // Шаг 2: Удаляем старые связи поста с тегами
        jdbcTemplate.update("DELETE FROM posts_tags WHERE post_id = ?", postId);

        // Шаг 3: Если переданы tagIds, добавляем новые связи с тегами
        if (tagIds != null && !tagIds.isEmpty()) {
            for (Long tagId : tagIds) {
                jdbcTemplate.update(
                        "INSERT INTO posts_tags (post_id, tag_id) VALUES (?, ?)",
                        postId, tagId
                );
            }
        }
    }

    private boolean postExists(long postId) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM posts WHERE id = ?", Long.class, postId
        ) > 0;
    }

    @Override
    public Post save(@NonNull final Post post) {
        Long postId = post.getId();

        if (postId == null) {
            postId = add(post);
        } else {
            if (postExists(postId)) {
                update(post);
            } else {
                add(post);
            }
        }

        final Post savedPost = findById(postId).orElse(null);

        return savedPost;
    }

    @Override
    public void deleteById(long postId) {
        // Удаляем связанные комментарии
        jdbcTemplate.update("DELETE FROM comments WHERE post_id = ?", postId);

        // Удаляем связанные теги
        jdbcTemplate.update("DELETE FROM posts_tags WHERE post_id = ?", postId);

        // Удаляем сам пост
        jdbcTemplate.update("DELETE FROM posts WHERE id = ?", postId);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM comments");
        jdbcTemplate.update("DELETE FROM posts_tags");
        jdbcTemplate.update("DELETE FROM posts");
    }

    @Override
    public void incrementLikes(long postId) {
        jdbcTemplate.update("UPDATE posts SET likes = likes + 1 WHERE id = ?", postId);
    }

    private static class PostResultSetExtractor implements ResultSetExtractor<List<Post>> {

        @Override
        public List<Post> extractData(ResultSet rs) throws SQLException {
            Map<Long, Post> postMap = new LinkedHashMap<>();

            while (rs.next()) {
                Long postId = rs.getLong("post_id");

                Post post = postMap.get(postId);
                if (post == null) {
                    post = new Post();
                    post.setId(postId);
                    post.setTitle(rs.getString("title"));
                    post.setImageFileName(rs.getString("image"));
                    post.setText(rs.getString("text"));
                    post.setLikes(rs.getInt("likes"));
                    postMap.put(postId, post);
                }

                // Добавляем тег, если он есть
                Long tagId = rs.getLong("tag_id");
                if (!rs.wasNull()) {
                    Tag tag = new Tag();
                    tag.setId(tagId);
                    tag.setName(rs.getString("tag_name"));
                    post.addTag(tag);
                }

                // Добавляем комментарий, если он есть
                Long commentId = rs.getLong("comment_id");
                if (!rs.wasNull()) {
                    Comment comment = new Comment();
                    comment.setId(commentId);
                    comment.setText(rs.getString("comment_text"));
                    post.addComment(comment);
                }
            }

            return new ArrayList<>(postMap.values());
        }
    }
}
