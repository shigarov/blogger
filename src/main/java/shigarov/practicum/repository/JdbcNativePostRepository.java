package shigarov.practicum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;

import java.sql.*;
import java.util.*;

@Repository
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAllPosts() {
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
    public Page<Post> findAllPosts(@NonNull Pageable pageable) {
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
    public Page<Post> findAllPostsByTag(@NonNull Pageable pageable, Long tagId) {
        final String postCountSql = """
                SELECT COUNT(DISTINCT p.id) AS post_count
                FROM posts p
                LEFT JOIN posts_tags pt ON p.id = pt.post_id
                LEFT JOIN tags t ON pt.tag_id = t.id
                WHERE t.id = ?;
                """;

        final long totalPosts = jdbcTemplate.queryForObject(postCountSql, Integer.class, tagId);

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
    public Optional<Post> findPostById(long postId) {
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

        List<Post> posts = jdbcTemplate.query(sql, new PostResultSetExtractor(), postId);
        Post post = posts.getFirst();

        return Optional.ofNullable(post);
    }

    @Override
    public void addPost(
            @NonNull String title,
            @Nullable String image,
            @NonNull String text,
            @Nullable List<Long> tagIds
    ) {
        // Шаг 1: Вставляем новый пост
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO posts (title, image, text) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, title);
            ps.setString(2, image);
            ps.setString(3, text);
            return ps;
        }, keyHolder);

        // Шаг 2: Получаем ID вставленного поста
        long postId = keyHolder.getKey().longValue();

        // Шаг 3: Если переданы tagIds, добавляем связи с тегами
        if (tagIds != null && !tagIds.isEmpty()) {
            for (Long tagId : tagIds) {
                jdbcTemplate.update(
                        "INSERT INTO posts_tags (post_id, tag_id) VALUES (?, ?)",
                        postId, tagId
                );
            }
        }
    }


//    @Override
//    public void addPostWithTags (
//            @NonNull String title,
//            @Nullable String image,
//            @NonNull String text,
//            @Nullable List<String> tags
//    )
//    {
//        // Шаг 1: Вставляем новый пост и получаем его ID
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(
//                    "INSERT INTO posts (title, image, text) VALUES (?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS
//            );
//            ps.setString(1, title);
//            ps.setString(2, image);
//            ps.setString(3, text);
//            return ps;
//        }, keyHolder);
//
//        long postId = keyHolder.getKey().longValue();
//        // System.out.println("ID нового поста: " + postId);
//
//        if (tags == null || tags.isEmpty()) return;
//
//        // Шаг 2: Вставляем теги и получаем их ID
//        Map<String, Long> tagIds = new HashMap<>();
//        for (String tag : tags) {
//            // Вставляем тег, если он ещё не существует
//            jdbcTemplate.update("MERGE INTO tags (name) KEY (name) VALUES (?)", tag);
//
//            // Получаем ID тега
//            Long tagId = jdbcTemplate.queryForObject(
//                    "SELECT id FROM tags WHERE name = ?",
//                    Long.class,
//                    tag
//            );
//            tagIds.put(tag, tagId);
//        }
//
//        // Шаг 3: Вставляем связи между постом и тегами
//        for (Long tagId : tagIds.values()) {
//            jdbcTemplate.update(
//                    "INSERT INTO posts_tags (post_id, tag_id) VALUES (?, ?)",
//                    postId, tagId
//            );
//        }
//
//        // System.out.println("Пост и связи с тегами успешно добавлены.");
//    }

    public void updatePost(
            long postId,
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

//    public void updatePost(
//            long postId,
//            @NonNull String title,
//            @Nullable String image,
//            @NonNull String text,
//            @Nullable List<String> tags
//    )
//    {
//        // Шаг 1: Обновляем данные поста
//        jdbcTemplate.update(
//                "UPDATE posts SET title = ?, image = ?, text = ? WHERE id = ?",
//                title, image, text, postId
//        );
//
//        // Шаг 2: Удаляем старые связи поста с тегами
//        jdbcTemplate.update("DELETE FROM posts_tags WHERE post_id = ?", postId);
//
//        // Шаг 3: Добавляем новые связи поста с тегами
//        for (String tag : tags) {
//            // Вставляем тег, если он ещё не существует
//            jdbcTemplate.update("MERGE INTO tags (name) KEY (name) VALUES (?)", tag);
//
//            // Получаем ID тега
//            Long tagId = jdbcTemplate.queryForObject(
//                    "SELECT id FROM tags WHERE name = ?",
//                    Long.class,
//                    tag
//            );
//
//            // Вставляем связь между постом и тегом
//            jdbcTemplate.update(
//                    "INSERT INTO posts_tags (post_id, tag_id) VALUES (?, ?)",
//                    postId, tagId
//            );
//        }
//    }

    @Override
    public void deletePost(long postId) {
        // Удаляем связанные комментарии
        jdbcTemplate.update("DELETE FROM comments WHERE post_id = ?", postId);

        // Удаляем связанные теги
        jdbcTemplate.update("DELETE FROM posts_tags WHERE post_id = ?", postId);

        // Удаляем сам пост
        jdbcTemplate.update("DELETE FROM posts WHERE id = ?", postId);
    }

    @Override
    public void incrementPostLikes(long postId) {
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
                    post.setImage(rs.getString("image"));
                    post.setText(rs.getString("text"));
                    //post.setTags(new ArrayList<>()); // Инициализация списка тегов
                    post.setLikes(rs.getInt("likes"));
                    //post.setComments(new ArrayList<>()); // Инициализация списка комментариев
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


    public Optional<Comment> findCommentById(long id) {
        String sql = """
                SELECT 
                    c.id AS comment_id,
                    c.text AS comment_text,
                    p.id AS post_id,
                    p.title AS post_title,
                    p.image AS post_image,
                    p.text AS post_text,
                    p.likes AS post_likes,
                    t.id AS tag_id,
                    t.name AS tag_name
                FROM 
                    comments c
                JOIN 
                    posts p ON c.post_id = p.id
                LEFT JOIN 
                    posts_tags pt ON p.id = pt.post_id
                LEFT JOIN 
                    tags t ON pt.tag_id = t.id
                WHERE 
                    c.id = ?
                """;

        List<Comment> comments = jdbcTemplate.query(sql, new CommentRowMapper(), id);
        return Optional.ofNullable(comments.getFirst());
    }

    @Override
    public void addComment(@NonNull String text, long postId) {
        jdbcTemplate.update(
                "INSERT INTO comments (text, post_id) VALUES (?, ?)",
                text, postId
        );
    }

    @Override
    public void updateComment(long id, @NonNull String text) {
        jdbcTemplate.update("UPDATE comments SET text = ? WHERE id = ?", text, id);
    }

    private static class CommentRowMapper implements RowMapper<Comment> {
        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setId(rs.getLong("comment_id"));
            comment.setText(rs.getString("comment_text"));

            // Данные поста
            Post post = new Post();
            post.setId(rs.getLong("post_id"));
            post.setTitle(rs.getString("post_title"));
            post.setImage(rs.getString("post_image"));
            post.setText(rs.getString("post_text"));
            post.setLikes(rs.getInt("post_likes"));

            // Данные тега (если есть)
            Long tagId = rs.getLong("tag_id");
            if (!rs.wasNull()) {
                Tag tag = new Tag();
                tag.setId(tagId);
                tag.setName(rs.getString("tag_name"));
                post.addTag(tag);
            }

            comment.setPost(post);

            return comment;
        }
    }
}
