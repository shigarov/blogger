package shigarov.practicum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import shigarov.practicum.model.Tag;

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

//    @Override
//    public void addTag(@NonNull String tag) {
//        jdbcTemplate.update("INSERT INTO tags (name) VALUES (?)", tag);
//    }

    @Override
    public void addTag(long id, @NonNull String name) {
        jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", id, name);
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
