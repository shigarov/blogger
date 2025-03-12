package shigarov.practicum.blogger.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shigarov.practicum.blogger.model.Tag;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JdbcNativeTagRepository.class})
@ActiveProfiles("test")
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Tag tagOne;
    private Tag tagTwo;

    @BeforeEach
    public void setUp() {
        // Очистка базы данных
        jdbcTemplate.execute("DELETE FROM tags");
        jdbcTemplate.execute("ALTER TABLE tags ALTER COLUMN id RESTART WITH 1");

        tagOne = new Tag(1L, "Тег 1");
        tagTwo = new Tag(2L, "Тег 2");

        tagRepository.save(tagOne);
        tagRepository.save(tagTwo);
    }

    @Test
    public void testFindAllTags() {
        final List<Tag> foundTags = tagRepository.findAll();

        assertThat(foundTags).isNotNull();
        assertThat(foundTags).hasSize(2);
        assertThat(foundTags.getFirst().getId()).isEqualTo(tagOne.getId());
    }

    @Test
    public void testFindTagById() {
        final long tagId = tagOne.getId();

        final Tag foundTag = tagRepository.findById(tagId).orElse(null);

        assertThat(foundTag).isNotNull();
        assertThat(foundTag.getId()).isEqualTo(tagId);
        assertThat(foundTag.getName()).isEqualTo(tagOne.getName());
    }

    @Test
    public void testAddTagWithNullId() {
        final Tag tagThree = new Tag(null, "Тег 3");

        final Tag addedTag = tagRepository.save(tagThree);
        final long tagId = addedTag.getId();
        final Tag savedTag = tagRepository.findById(tagId).orElse(null);

        assertThat(savedTag).isNotNull();
        assertThat(savedTag.getId()).isEqualTo(tagId);
        assertThat(savedTag.getName()).isEqualTo(tagThree.getName());
    }

    @Test
    public void testAddTagWithNotNullId() {
        final Tag tagThree = new Tag(3L, "Тег 3");

        final Tag addedTag = tagRepository.save(tagThree);
        final long tagId = addedTag.getId();
        final Tag savedTag = tagRepository.findById(tagId).orElse(null);

        assertThat(savedTag).isNotNull();
        assertThat(savedTag.getId()).isEqualTo(tagId);
        assertThat(savedTag.getName()).isEqualTo(tagThree.getName());
    }

}
