package shigarov.practicum.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import shigarov.practicum.configuration.DataSourceConfiguration;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {DataSourceConfiguration.class, JdbcNativePostRepository.class})
@TestPropertySource(locations = "classpath:test-application.properties")
public class JdbcNativePostRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        // Очистка базы данных
        jdbcTemplate.execute("DELETE FROM posts_tags");
        jdbcTemplate.execute("DELETE FROM tags");
        jdbcTemplate.execute("DELETE FROM comments");
        jdbcTemplate.execute("DELETE FROM posts");

        // Добавление тестовых данных в таблицу тегов
        final String sqlCreateTags = """
                INSERT INTO tags (id, name) VALUES 
                (1, 'технологии'),
                (2, 'блог'),
                (3, 'программирование'),
                (4, 'жизнь'),
                (5, 'тест'),
                (6, 'котики');
                """;
        jdbcTemplate.execute(sqlCreateTags);

        // Добавление тестовых данных в таблицу постов
        final String sqlCreatePosts = """
                INSERT INTO posts (id, title, image, text, likes) VALUES
                (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
                (2, 'Пост 2', 'image2.png', 'Текст поста 2, разбитый на абзацы.', 5),
                (3, 'Пост 3', 'image3.png', 'Текст поста 3, разбитый на абзацы.', 15),
                (4, 'Пост 4', NULL, 'Текст поста 4, разбитый на абзацы.', 20),
                (5, 'Пост 5', NULL, 'Текст поста 5, разбитый на абзацы.', 8),
                (6, 'Пост 6', NULL, 'Текст поста 6, разбитый на абзацы.', 12),
                (7, 'Пост 7', NULL, 'Текст поста 7, разбитый на абзацы.', 7),
                (8, 'Пост 8', NULL, 'Текст поста 8, разбитый на абзацы.', 30),
                (9, 'Пост 9', NULL, 'Текст поста 9, разбитый на абзацы.', 25),
                (10, 'Пост 10', NULL, 'Текст поста 10, разбитый на абзацы.', 18);
                """;
        jdbcTemplate.execute(sqlCreatePosts);

        // Добавление тестовых данных в таблицу комментариев
        final String sqlCreatePostsTags = """
                INSERT INTO posts_tags (post_id, tag_id) VALUES
                (1, 1),
                (1, 3),
                (2, 3),
                (2, 4),
                (2, 6),
                (4, 1),
                (4, 2),
                (5, 3),
                (5, 5),
                (5, 6),
                (6, 4),
                (7, 3),
                (7, 5),
                (9, 1),
                (10, 1),
                (10, 2);
                """;
        jdbcTemplate.execute(sqlCreatePostsTags);

        // Добавление тестовых данных в таблицу комментариев
        final String sqlCreateComments = """
                INSERT INTO comments (id, text, post_id) VALUES
                (1, 'Отличный пост!', 1),
                (2, 'Спасибо за информацию.', 1),
                (3, 'Интересно, но можно подробнее?', 2),
                (4, 'Полезный материал.', 2),
                (5, 'Согласен с автором.', 2),
                (6, 'Первый комментарий к посту 3.', 3),
                (7, 'Классный пост!', 4),
                (8, 'Много нового узнал.', 4),
                (9, 'Спасибо за статью.', 5),
                (10, 'Интересная точка зрения.', 5),
                (11, 'Жду продолжения.', 5),
                (12, 'Отлично написано!', 6),
                (13, 'Полезно для начинающих.', 7),
                (14, 'Спасибо за советы.', 7),
                (15, 'Интересный материал.', 8),
                (16, 'Много полезной информации.', 8),
                (17, 'Рекомендую к прочтению.', 8),
                (18, 'Отличный пост!', 9),
                (19, 'Спасибо за труд.', 9),
                (20, 'Очень познавательно.', 10),
                (21, 'Спасибо за подробности.', 10);
                """;

        jdbcTemplate.execute(sqlCreateComments);
    }

    @Test
    void save_shouldAddPostToDatabase() {
//        Post post = new Post(4L, "Петр", "Васильев", 25, true);
//
//        postRepository.save(post);
//
//        User savedUser = userRepository.findAll().stream()
//                .filter(createdUsers -> createdUsers.getId().equals(4L))
//                .findFirst()
//                .orElse(null);
//
//        assertNotNull(savedUser);
//        assertEquals("Петр", savedUser.getFirstName());
//        assertEquals("Васильев", savedUser.getLastName());
    }

    @Test
    void findAll_shouldReturnAllPostsForPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> page = postRepository.findAll(pageable);

        List<Post> posts = page.getContent();
        assertNotNull(posts);
        assertEquals(10, posts.size());

        // (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
        Post post = posts.getFirst();
        assertEquals(1L, post.getId());
        assertEquals("Пост 1", post.getTitle());

        List<Comment> comments = post.getComments();
        assertNotNull(comments);
        assertEquals(2, comments.size());
        Comment comment = comments.getFirst();
        assertEquals(1L, comment.getId());

        List<Tag> tags = post.getTags();
        assertNotNull(tags);
        assertEquals(2, tags.size());
        Tag tag = tags.getFirst();
        assertEquals(1L, tag.getId());
    }

    @Test
    void findAllByTags_shouldReturnAllPostsByTagsForPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> page = postRepository.findAllByTag(pageable, 1L);

        List<Post> posts = page.getContent();
        assertNotNull(posts);
        assertEquals(4, posts.size());

        // (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
        Post post = posts.getFirst();
        assertEquals(1L, post.getId());
        assertEquals("Пост 1", post.getTitle());

        List<Comment> comments = post.getComments();
        assertNotNull(comments);
        assertEquals(2, comments.size());
        Comment comment = comments.getFirst();
        assertEquals(1L, comment.getId());

        List<Tag> tags = post.getTags();
        assertNotNull(tags);
        assertEquals(2, tags.size());
        Tag tag = tags.getFirst();
        assertEquals(1L, tag.getId());
    }

    @Test
    void deleteById_shouldRemoveUserFromDatabase() {
//        userRepository.deleteById(1L);
//
//        List<User> users = userRepository.findAll();
//
//        User deletedUser = users.stream()
//                .filter(createdUsers -> createdUsers.getId().equals(1L))
//                .findFirst()
//                .orElse(null);
//        assertNull(deletedUser);
    }
}
