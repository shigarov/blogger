package shigarov.practicum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcNativeCommentRepository implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcNativeCommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
