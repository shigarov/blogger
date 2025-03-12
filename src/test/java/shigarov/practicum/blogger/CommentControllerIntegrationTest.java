package shigarov.practicum.blogger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.repository.CommentRepository;
import shigarov.practicum.blogger.repository.PostRepository;
import shigarov.practicum.blogger.repository.TagRepository;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CommentControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

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

        postRepository.save(postOne);
        commentRepository.save(commentOne);
    }

    @Test
    void testAddComment() throws Exception {
        mockMvc.perform(post("/posts/1/comments/add")
                        .param("commentText", "Комментарий 2")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        assertTrue(commentRepository.findById(2L).isPresent());
    }

    @Test
    void testEditComment() throws Exception {
        mockMvc.perform(get("/posts/1/comments/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1?editingCommentId=1"));
    }

    @Test
    void testUpdateComment() throws Exception {
        mockMvc.perform(post("/posts/1/comments/update/1")
                        .param("commentText", "Обновленный комментарий 1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        Comment updatedComment = commentRepository.findById(1L).get();

        assertEquals("Обновленный комментарий 1", updatedComment.getText());
    }
}
