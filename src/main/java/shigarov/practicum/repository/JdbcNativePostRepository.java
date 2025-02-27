package shigarov.practicum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;

import java.util.*;
import java.sql.*;

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
    public Page<Post> findAllPosts(Pageable pageable) {
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
    public Page<Post> findAllPostsByTag(Pageable pageable, Long tagId) {
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
    public void createPost(Post post) {
        final String sql = """
                 INSERT INTO posts (id, title, image, text, likes)
                 VALUES (?, ?, ?, ?, ?);
                """;
        // Формируем insert-запрос с параметрами
        jdbcTemplate.update(sql, post.getId(), post.getTitle(), post.getImage(), post.getText(), post.getLikes());

        final String sqlCreatePostsTags = """
                 INSERT INTO posts_tags (post_id, tag_id)
                 VALUES (?, ?);
                """;

        List<Tag> tags = post.getTags();
        for (Tag tag : tags) {
            jdbcTemplate.update(sqlCreatePostsTags, post.getId(), tag.getId());
        }
    }

    @Override
    public void updatePost(Post post) {
        final String sql = """
                UPDATE posts
                SET
                    title = ?,
                    text = ?
                WHERE id = ?;
                """;

        jdbcTemplate.update(sql, post.getTitle(), post.getText(), post.getId());
    }

    @Override
    public void deletePost(Post post) {
        jdbcTemplate.update("DELETE FROM comments WHERE post_id = ?;", post.getId());
        jdbcTemplate.update("DELETE FROM posts_tags WHERE post_id = ?;", post.getId());
        jdbcTemplate.update("DELETE FROM posts WHERE id = ?;", post.getId());
    }

    @Override
    public void incrementLikes(Post post) {
        final String sql = """
                UPDATE posts
                SET likes = likes + 1
                WHERE id = ?;
                """;

        jdbcTemplate.update(sql, post.getId());
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

    @Override
    public List<Tag> findAllTags() {
        final String sql = """
                SELECT
                    t.id,
                    t.name,
                FROM tags t
                ORDER BY t.id;
                """;

        return jdbcTemplate.query(sql, new TagResultSetExtractor());
    }

    @Override
    public Optional<Tag> findTagById(long id) {
        final String sql = """
                SELECT
                    t.id,
                    t.name,
                FROM tags t
                WHERE t.id = ?;
                """;

        List<Tag> tags = jdbcTemplate.query(sql, new TagResultSetExtractor(), id);
        return Optional.ofNullable(tags.getFirst());
        //return Optional.empty();
    }

    @Override
    public void createTag(Tag tag) {
        final String sql = """
                 INSERT INTO tags (id, name)
                 VALUES (?, ?);
                """;
        // Формируем insert-запрос с параметрами
        jdbcTemplate.update(sql, tag.getId(), tag.getName());
    }


    private static class TagResultSetExtractor implements ResultSetExtractor<List<Tag>> {
        @Override
        public List<Tag> extractData(ResultSet rs) throws SQLException {
            List<Tag> tags = new LinkedList<>();

            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getLong("id"));
                tag.setName(rs.getString("name"));
                tags.add(tag);
            }

            return tags;
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
    public void createComment(Comment comment) {
        final String sql = """
                 INSERT INTO comments (id, text, post_id)
                 VALUES (?, ?, ?);
                """;
        // Формируем insert-запрос с параметрами
        jdbcTemplate.update(sql, comment.getId(), comment.getText(), comment.getPost().getId());
    }

    @Override
    public void updateComment(Comment comment) {
        final String sql = """
                UPDATE comments
                SET text = ?
                WHERE id = ?;
                """;
        // Формируем insert-запрос с параметрами
        jdbcTemplate.update(sql, comment.getText(), comment.getId());
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
