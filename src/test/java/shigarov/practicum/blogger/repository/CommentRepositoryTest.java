package shigarov.practicum.blogger.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JdbcNativePostRepository.class, JdbcNativeCommentRepository.class})
@ActiveProfiles("test")
public class CommentRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Post postOne;
    private Comment commentOne;

    @BeforeEach
    public void setUp() {
        // Очистка базы данных
        jdbcTemplate.execute("DELETE FROM comments");
        jdbcTemplate.execute("DELETE FROM posts");

        jdbcTemplate.execute("ALTER TABLE comments ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE posts ALTER COLUMN id RESTART WITH 1");

        postOne = new Post(1L, "Заголовок 1", null, "Текст 1");
        commentOne = new Comment(1L, "Комментарий 1", postOne);

        postRepository.add(postOne);
        commentRepository.add(commentOne);
    }

    @Test
    public void testFindCommentById() {
        final long commentId = commentOne.getId();

        final Comment foundComment = commentRepository.findById(commentId).orElse(null);

        assertThat(foundComment).isNotNull();
        assertThat(foundComment.getId()).isEqualTo(commentOne.getId());
        assertThat(foundComment.getText()).isEqualTo(commentOne.getText());
    }

    @Test
    public void testAddComment() {
        final Comment commentTwo = new Comment(null, "Комментарий 2", postOne);

        final Comment addedComment = commentRepository.add(commentTwo);
        final long commentId = addedComment.getId();
        final Comment savedComment = commentRepository.findById(commentId).orElse(null);

        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isEqualTo(commentId);
        assertThat(savedComment.getText()).isEqualTo(commentTwo.getText());
    }

    @Test
    public void testUpdateComment() {
        final long commentId = commentOne.getId();
        commentOne.setText("Обновленный комментарий 1");

        commentRepository.update(commentOne);

        final Comment updatedComment = commentRepository.findById(commentId).orElse(null);

        assertThat(updatedComment).isNotNull();
        assertThat(updatedComment.getId()).isEqualTo(commentId);
        assertThat(updatedComment.getText()).isEqualTo(commentOne.getText());
    }
}
