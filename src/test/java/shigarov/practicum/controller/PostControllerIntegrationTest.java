package shigarov.practicum.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import shigarov.practicum.configuration.TestDataSourceConfiguration;
import shigarov.practicum.configuration.TestWebConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig(classes = {TestDataSourceConfiguration.class, TestWebConfiguration.class})
@WebAppConfiguration
@TestPropertySource(locations = "classpath:test-application.properties")
public class PostControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Очистка базы данных
        jdbcTemplate.execute("DELETE FROM posts_tags");
        jdbcTemplate.execute("DELETE FROM tags");
        jdbcTemplate.execute("DELETE FROM comments");
        jdbcTemplate.execute("DELETE FROM posts");

        // Добавляем тестовые данные
        jdbcTemplate.update(
                "INSERT INTO posts (id, title, image, text, likes) VALUES (?, ?, ?, ?, ?)",
                1L, "Пост 1", "https://example.com/image1.jpg", "Текст поста 1", 10
        );
        jdbcTemplate.update(
                "INSERT INTO posts (id, title, image, text, likes) VALUES (?, ?, ?, ?, ?)",
                2L, "Пост 2", "https://example.com/image2.jpg", "Текст поста 2", 5
        );

        jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (1, 'технологии')");
        jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (2, 'программирование')");

        jdbcTemplate.update("INSERT INTO posts_tags (post_id, tag_id) VALUES (1, 1)");
        jdbcTemplate.update("INSERT INTO posts_tags (post_id, tag_id) VALUES (1, 2)");
        jdbcTemplate.update("INSERT INTO posts_tags (post_id, tag_id) VALUES (2, 1)");

        jdbcTemplate.update(
                "INSERT INTO comments (id, text, post_id) VALUES (?, ?, ?)",
                1L, "Комментарий 1 к посту 1", 1
        );
        jdbcTemplate.update(
                "INSERT INTO comments (id, text, post_id) VALUES (?, ?, ?)",
                2L, "Комментарий 2 к посту 1", 1
        );
        jdbcTemplate.update(
                "INSERT INTO comments (id, text, post_id) VALUES (?, ?, ?)",
                3L, "Комментарий 1 к посту 2", 2
        );
    }

    @Test
    void testGetPosts() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(xpath("//table/tbody/tr").nodeCount(2))
                .andExpect(xpath("//table/tbody/tr[1]/td[2]").string("Пост 1"));
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
                        .param("title", "Пост 3")
                        .param("text", "Текст поста 3")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));
    }

    @Test
    void testUpdatePost() throws Exception {
        mockMvc.perform(post("/posts/update/1")
                        .param("title", "Обновленный Пост 1")
                        .param("text", "Обновленный текст поста 1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));
    }

    @Test
    void testDeletePost() throws Exception {
        mockMvc.perform(post("/posts/delete/1")
                        .param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));
    }

    @Test
    void testIncrementPostLikes() throws Exception {
        mockMvc.perform(post("/posts/incrementLikes/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));
    }


}
