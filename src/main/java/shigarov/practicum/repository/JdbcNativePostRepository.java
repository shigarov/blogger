package shigarov.practicum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> findAll() {
        // SQL-запрос
        String sql = """
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
        Map<Long, Post> postMap = new HashMap<>();

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
