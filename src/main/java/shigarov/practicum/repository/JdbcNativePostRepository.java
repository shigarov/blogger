package shigarov.practicum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;

import java.util.*;
import java.sql.*;

@Repository
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> findAll() {
        // SQL-запрос
        final String sql = """
                SELECT
                    p.id AS post_id,
                    p.title,
                    p.image,
                    p.text,
                    p.tags,
                    p.likes,
                    c.id AS comment_id,
                    c.comment_text
                FROM posts p
                LEFT JOIN comments c ON p.id = c.post_id
                ORDER BY p.id, c.id
                """;

        // Словарь для хранения постов по их ID
        final Map<Long, Post> postMap = new HashMap<>();

        // Выполнение запроса и обработка результата
        jdbcTemplate.query(sql, rs -> {
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
                post.setTags(rs.getString("tags"));
                post.setLikes(rs.getInt("likes"));
                post.setComments(new ArrayList<>()); // Инициализация списка комментариев
                postMap.put(postId, post); // Добавляем пост в мапу
            }

            // Получение данных о комментарии (если он есть)
            Long commentId = rs.getLong("comment_id");
            if (!rs.wasNull()) { // Проверка, есть ли комментарий
                Comment comment = new Comment();
                comment.setId(commentId);
                comment.setText(rs.getString("comment_text"));
                post.getComments().add(comment); // Добавляем комментарий к посту
            }
        });

        // Возвращаем список постов
        return new ArrayList<>(postMap.values());
    }

    @Override
    public Optional<Post> findById(long id) {
//        Post post = new Post();
//        post.setId(100L);
//        post.setTitle("Мой пост из репозитория");
//        post.setImage("image1.png");
//        post.setText("бла бла бла бла");
//        post.setTags("технологии, жизнь");
//        post.setLikes(1000);
//        List<Comment> comments = Arrays.asList(
//                new Comment(1L, "Вах вах!"),
//                new Comment(2L, "ням ням!")
//        );
//        post.setComments(comments);
//
//        return Optional.ofNullable(post);


        final String sql = """
                SELECT
                    p.id AS post_id,
                    p.title, 
                    p.image,
                    p.text,
                    p.tags,
                    p.likes,
                    c.id AS comment_id,
                    c.comment_text
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
                post.setTags(rs.getString("tags"));
                post.setLikes(rs.getInt("likes"));
                post.setComments(new ArrayList<>()); // Инициализация списка комментариев
                postMap.put(postId, post); // Добавляем пост в мапу
            }

            // Получение данных о комментарии (если он есть)
            Long commentId = rs.getLong("comment_id");
            if (!rs.wasNull()) { // Проверка, есть ли комментарий
                Comment comment = new Comment();
                comment.setId(commentId);
                comment.setText(rs.getString("comment_text"));
                post.getComments().add(comment); // Добавляем комментарий к посту
            }
        };

        jdbcTemplate.query(sql, handler, Long.valueOf(id));

        Post post = postMap.get(id);

        return Optional.ofNullable(post);
    }

    private List<Post> queryPosts(String sql, Object... args) throws SQLException {
        return null;
    }

//    private Post mapPost(ResultSet rs) throws SQLException {
//        Post post = new Post();
//        post.setId(rs.getLong("post_id"));
//        post.setTitle(rs.getString("title"));
//        post.setImage(rs.getString("image"));
//        post.setText(rs.getString("text"));
//        post.setTags(rs.getString("tags"));
//        post.setLikes(rs.getInt("likes"));
//        post.setComments(new ArrayList<>());
//        return post;
//    }
//
//    private void addCommentIfExists(Post post, ResultSet rs) throws SQLException {
//        Long commentId = rs.getLong("comment_id");
//        if (!rs.wasNull()) {
//            Comment comment = new Comment();
//            comment.setId(commentId);
//            comment.setText(rs.getString("comment_text"));
//            post.getComments().add(comment);
//        }
//    }


    @Override
    public List<Post> findByTag(String tag) {
        return null;
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

}
