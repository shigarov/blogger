package shigarov.practicum.blogger.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JdbcNativePostRepository.class, JdbcNativeTagRepository.class})
@ActiveProfiles("test")
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Post postOne;
    private Post postTwo;

    private Tag tagOne;
    private Tag tagTwo;

    @BeforeEach
    public void setUp() {
        // Очистка базы данных
        jdbcTemplate.execute("DELETE FROM posts_tags");
        jdbcTemplate.execute("DELETE FROM tags");
        jdbcTemplate.execute("DELETE FROM comments");
        jdbcTemplate.execute("DELETE FROM posts");

        jdbcTemplate.execute("ALTER TABLE posts ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE tags ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE comments ALTER COLUMN id RESTART WITH 1");

        tagOne = new Tag(1L, "Тег 1");
        tagTwo = new Tag(2L, "Тег 2");
        postOne = new Post(1L, "Заголовок 1", null, "Текст 1");
        postTwo = new Post(2L, "Заголовок 2", null, "Текст 2");
        postOne.addTag(tagOne);
        postTwo.addTag(tagTwo);

        tagRepository.add(tagOne);
        tagRepository.add(tagTwo);
        postRepository.add(postOne);
        postRepository.add(postTwo);
    }

    @Test
    public void testFindAllPosts() {
        final List<Post> foundPosts = postRepository.findAll();

        assertThat(foundPosts).isNotNull();
        assertThat(foundPosts).hasSize(2);
        assertThat(foundPosts.getFirst().getId()).isEqualTo(postOne.getId());
    }

    @Test
    public void testFindAllPostsByPage() {
        final Pageable pageable = PageRequest.of(0, 10);

        final Page<Post> postsPage = postRepository.findAll(pageable);
        final List<Post> foundPosts = postsPage.getContent();

        assertThat(foundPosts).isNotNull();
        assertThat(foundPosts).hasSize(2);
        assertThat(foundPosts.getFirst().getId()).isEqualTo(postOne.getId());
    }

    @Test
    public void testFindAllPostsByTag() {
        final Pageable pageable = PageRequest.of(0, 10);
        final long tagId = tagOne.getId();

        final Page<Post> postsPage = postRepository.findAllByTag(pageable, tagId);
        final List<Post> foundPosts = postsPage.getContent();

        assertThat(foundPosts).isNotNull();
        assertThat(foundPosts).hasSize(1);
        assertThat(foundPosts.getFirst().getId()).isEqualTo(postOne.getId());
    }

    @Test
    public void testFindPostById() {
        final long postId = postOne.getId();

        Optional<Post> foundPost = postRepository.findById(postId);

        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getId()).isEqualTo(postOne.getId());
        assertThat(foundPost.get().getTitle()).isEqualTo(postOne.getTitle());
        assertThat(foundPost.get().getText()).isEqualTo(postOne.getText());
    }

    @Test
    public void testAddPost() {
        final Post postThree = new Post(null, "Заголовок 3", null, "Текст 3");

        final Post addedPost = postRepository.add(postThree);
        final long postId = addedPost.getId();
        final Post savedPost = postRepository.findById(postId).orElse(null);

        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getId()).isEqualTo(postId);
        assertThat(savedPost.getTitle()).isEqualTo(postThree.getTitle());
        assertThat(savedPost.getText()).isEqualTo(postThree.getText());
    }

    @Test
    public void testUpdatePost() {
        final long postId = postOne.getId();
        postOne.setTitle("Обновленный заголовок 1");
        postOne.setText("Обновленный текст 1");

        postRepository.update(postOne);

        final Post updatedPost = postRepository.findById(postId).orElse(null);

        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getId()).isEqualTo(postId);
        assertThat(updatedPost.getTitle()).isEqualTo(postOne.getTitle());
        assertThat(updatedPost.getText()).isEqualTo(postOne.getText());
    }

    @Test
    public void testDeletePost() {
        final long postId = postOne.getId();

        postRepository.deleteById(postId);
        final Post deletedPost = postRepository.findById(postId).orElse(null);

        assertThat(deletedPost).isNull();
    }

    @Test
    public void testIncrementPostLikes() {
        final long postId = postOne.getId();
        int likes = postOne.getLikes();

        postRepository.incrementLikes(postId);
        final Post updatedPost = postRepository.findById(postId).orElse(null);

        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getLikes()).isEqualTo(++ likes);
    }
}
