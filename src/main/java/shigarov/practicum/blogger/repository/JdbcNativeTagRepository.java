package shigarov.practicum.blogger.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcNativeTagRepository implements TagRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcNativeTagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM tags ORDER BY id",
                new TagResultSetExtractor()
        );
    }

    @Override
    public Optional<Tag> findById(long id) {
        final List<Tag> tags = jdbcTemplate.query(
                "SELECT t.id, t.name FROM tags t WHERE t.id = ?",
                new TagResultSetExtractor(),
                id
        );
        final Tag tag = tags.isEmpty() ? null : tags.getFirst();

        return Optional.ofNullable(tag);
    }

    @Override
    public void add(Tag tag) {
        if (tag == null)
            return;
        var name = tag.getName();
        addTag(name);
    }

    private void addTag(@NonNull String tagName) {
        jdbcTemplate.update("INSERT INTO tags (name) VALUES (?)", tagName);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM posts_tags");
        jdbcTemplate.update("DELETE FROM tags");
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

}
