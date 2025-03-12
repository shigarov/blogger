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
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;
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
public class PostControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

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

        tagRepository.save(tagOne);
        tagRepository.save(tagTwo);

        postRepository.save(postOne);
        postRepository.save(postTwo);
    }

    @Test
    void testGetPosts() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(xpath("//table/tbody/tr").nodeCount(2))
                .andExpect(xpath("//table/tbody/tr[1]/td[2]").string("Заголовок 1"));
    }

    @Test
    void testGetPostsByTag() throws Exception {
        ResultActions ra = mockMvc.perform(get("/posts").param("tagId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(xpath("//table/tbody/tr").nodeCount(1))
                .andExpect(xpath("//table/tbody/tr[1]/td[2]").string("Заголовок 1"));
    }

    @Test
    void testGetPost() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"));
    }

    @Test
    void testAddPost() throws Exception {
        mockMvc.perform(post("/posts/add")
                        .param("title", "Заголовок 3")
                        .param("text", "Текст 3")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));

        assertTrue(postRepository.findById(3L).isPresent());
    }

    @Test
    void testUpdatePost() throws Exception {
        mockMvc.perform(post("/posts/update/1")
                        .param("title", "Обновленный заголовок 1")
                        .param("text", "Обновленный текст 1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        Post updatedPost = postRepository.findById(1L).get();

        assertEquals("Обновленный заголовок 1", updatedPost.getTitle());
        assertEquals("Обновленный текст 1", updatedPost.getText());
    }

    @Test
    void testDeletePost() throws Exception {
        mockMvc.perform(post("/posts/delete/1")
                        .param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));

        assertFalse(postRepository.findById(1L).isPresent());
    }

    @Test
    void testIncrementPostLikes() throws Exception {
        mockMvc.perform(post("/posts/incrementLikes/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        assertEquals(1, postRepository.findById(1L).get().getLikes());
    }

}
