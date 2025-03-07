package shigarov.practicum.blogger.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

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
    public List<Tag> findAllTags() {
        return jdbcTemplate.query(
                "SELECT * FROM tags ORDER BY id",
                new TagResultSetExtractor()
        );
    }

    @Override
    public Optional<Tag> findTagById(long id) {
        List<Tag> tags = jdbcTemplate.query(
                "SELECT t.id, t.name FROM tags t WHERE t.id = ?",
                new TagResultSetExtractor(),
                id
        );

        return Optional.ofNullable(tags.getFirst());
    }

    @Override
    public void addTag(@NonNull String tag) {
        jdbcTemplate.update("INSERT INTO tags (name) VALUES (?)", tag);
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
