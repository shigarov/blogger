package shigarov.practicum.blogger.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcNativeCommentRepository implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcNativeCommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Comment> findById(long id) {
        final String sql = """
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

        final List<Comment> comments = jdbcTemplate.query(sql, new CommentRowMapper(), id);
        final Comment comment = comments.isEmpty() ? null : comments.getFirst();

        return Optional.ofNullable(comment);
    }

    private Comment add(@NonNull final Comment comment) {
        if (comment == null) return null;

        var text = comment.getText();
        var post = comment.getPost();
        var postId = post.getId();

        long commentId = addComment(text, postId);
        return new Comment(commentId, text, post);
    }

    private long addComment(@NonNull final String text, @NonNull final Long postId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO comments (text, post_id) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, text);
            ps.setLong(2, postId);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void update(@NonNull final Comment comment) {
        var id = comment.getId();
        var text = comment.getText();
        updateComment(id, text);
    }

    private void updateComment(long id, @NonNull String text) {
        jdbcTemplate.update("UPDATE comments SET text = ? WHERE id = ?", text, id);
    }

    private boolean commentExists(long commentId) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM comments WHERE id = ?", Long.class, commentId
        ) > 0;
    }

    @Override
    public Comment save(@NonNull final Comment comment) {
        Long commentId = comment.getId();

        if (commentId == null) {
            commentId = add(comment).getId();
        } else {
            if (commentExists(commentId)) {
                update(comment);
            } else {
                add(comment);
            }
        }

        final Comment savedComment = findById(commentId).orElse(null);

        return savedComment;
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
            post.setImageFileName(rs.getString("post_image"));
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
