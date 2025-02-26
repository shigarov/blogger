package shigarov.practicum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
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
    public Page<Post> findAll(Pageable pageable) {
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
    public Page<Post> findAllByTag(Pageable pageable, Long tagId) {
        final String postCountSql = """
                SELECT COUNT(DISTINCT p.id) AS post_count
                FROM posts p
                LEFT JOIN posts_tags pt ON p.id = pt.post_id
                LEFT JOIN tags t ON pt.tag_id = t.id
                WHERE t.id = ?;
                """;

        final long totalPosts = jdbcTemplate.queryForObject(postCountSql, Integer.class, tagId);

//        final String sql = """
//                SELECT
//                    p.id AS post_id,
//                    p.title,
//                    p.image,
//                    p.text,
//                    p.likes,
//                    t.id AS tag_id,
//                    t.name AS tag_name,
//                    c.id AS comment_id,
//                    c.text AS comment_text
//                FROM (
//                    SELECT *
//                    FROM posts
//                    ORDER BY id
//                    LIMIT ? OFFSET ?
//                ) p
//                LEFT JOIN
//                    posts_tags pt ON p.id = pt.post_id
//                LEFT JOIN
//                    tags t ON pt.tag_id = t.id
//                LEFT JOIN
//                    comments c ON p.id = c.post_id
//                WHERE
//                    t.id = ?  -- Фильтр по ID тега
//                ORDER BY
//                    p.id, t.id, c.id;
//                """;

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
    public Optional<Post> findById(long id) {
        final String sql = """
                SELECT
                    p.id AS post_id,
                    p.title,
                    p.image,
                    p.text,
                    p.tags,
                    p.likes,
                    c.id AS comment_id,
                    c.text AS comment_text
                FROM posts p
                LEFT JOIN comments c ON p.id = c.post_id
                WHERE p.id = ?
                ORDER BY c.id
                """;

        final Map<Long, Post> postMap = new HashMap<>();

        RowCallbackHandler handler = rs -> {
            // Получение данных о посте
            Long postId = rs.getLong("post_id");
            Post post = postMap.get(postId);

            // Если пост ещё не добавлен в мапу, создаем его
            if (post == null) {
                post = new Post();
                post.setId(postId);
                post.setTitle(rs.getString("title"));
                post.setImage(rs.getString("image"));
                post.setText(rs.getString("text"));
                post.setLikes(rs.getInt("likes"));
                //post.setComments(new ArrayList<>()); // Инициализация списка комментариев
                postMap.put(postId, post); // Добавляем пост в мапу
            }

            // Получение данных о комментарии (если он есть)
            Long commentId = rs.getLong("comment_id");
            if (!rs.wasNull()) { // Проверка, есть ли комментарий
                Comment comment = new Comment();
                comment.setId(commentId);
                comment.setText(rs.getString("comment_text"));
                post.addComment(comment); // Добавляем комментарий к посту
            }
        };

        jdbcTemplate.query(sql, handler, Long.valueOf(id));

        Post post = postMap.get(id);

        return Optional.ofNullable(post);
    }


    @Override
    public void save(Post post) {
        final String sql = """
                insert into posts(title, image, text, tags)
                values(?, ?, ?, ?)
                """;
        // Формируем insert-запрос с параметрами
        jdbcTemplate.update(sql,
                post.getTitle(), post.getImage(), post.getText(), post.getTags());
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
}
