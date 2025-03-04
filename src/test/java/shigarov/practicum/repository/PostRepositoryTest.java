package shigarov.practicum.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import shigarov.practicum.configuration.TestDataSourceConfiguration;
import shigarov.practicum.model.Post;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {TestDataSourceConfiguration.class, JdbcNativePostRepository.class})
@TestPropertySource(locations = "classpath:test-application.properties")
public class PostRepositoryTest {
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
    }

    @Nested
    class FindPostsTests {
        @BeforeEach
        void setUp() {
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
        void testFindAllPosts() {
            // Вызов тестируемого метода
            List<Post> posts = postRepository.findAllPosts();

            // Проверяем количество постов
            assertEquals(2, posts.size(), "Должно быть 2 поста");

            // Проверяем первый пост
            Post post1 = posts.get(0);
            assertEquals(1, post1.getId(), "ID первого поста должен быть 1");
            assertEquals("Пост 1", post1.getTitle(), "Название первого поста должно быть 'Пост 1'");
            assertEquals("https://example.com/image1.jpg", post1.getImage(), "Ссылка на картинку первого поста должна быть корректной");
            assertEquals("Текст поста 1", post1.getText(), "Текст первого поста должен быть корректным");
            assertEquals(10, post1.getLikes(), "Количество лайков первого поста должно быть 10");

            // Проверяем теги первого поста
            assertEquals(2, post1.getTags().size(), "У первого поста должно быть 2 тега");
            assertTrue(post1.getTags().stream().anyMatch(tag -> tag.getName().equals("технологии")), "Первый пост должен иметь тег 'технологии'");
            assertTrue(post1.getTags().stream().anyMatch(tag -> tag.getName().equals("программирование")), "Первый пост должен иметь тег 'программирование'");

            // Проверяем комментарии первого поста
            assertEquals(2, post1.getComments().size(), "У первого поста должно быть 2 комментария");
            assertTrue(post1.getComments().stream().anyMatch(comment -> comment.getText().equals("Комментарий 1 к посту 1")), "Первый пост должен иметь комментарий 'Комментарий 1 к посту 1'");
            assertTrue(post1.getComments().stream().anyMatch(comment -> comment.getText().equals("Комментарий 2 к посту 1")), "Первый пост должен иметь комментарий 'Комментарий 2 к посту 1'");

            // Проверяем второй пост
            Post post2 = posts.get(1);
            assertEquals(2, post2.getId(), "ID второго поста должен быть 2");
            assertEquals("Пост 2", post2.getTitle(), "Название второго поста должно быть 'Пост 2'");
            assertEquals("https://example.com/image2.jpg", post2.getImage(), "Ссылка на картинку второго поста должна быть корректной");
            assertEquals("Текст поста 2", post2.getText(), "Текст второго поста должен быть корректным");
            assertEquals(5, post2.getLikes(), "Количество лайков второго поста должно быть 5");

            // Проверяем теги второго поста
            assertEquals(1, post2.getTags().size(), "У второго поста должен быть 1 тег");
            assertTrue(post2.getTags().stream().anyMatch(tag -> tag.getName().equals("технологии")), "Второй пост должен иметь тег 'технологии'");

            // Проверяем комментарии второго поста
            assertEquals(1, post2.getComments().size(), "У второго поста должен быть 1 комментарий");
            assertTrue(post2.getComments().stream().anyMatch(comment -> comment.getText().equals("Комментарий 1 к посту 2")), "Второй пост должен иметь комментарий 'Комментарий 1 к посту 2'");
        }
    }

    @Nested
    class AddPostTests {
        @BeforeEach
        void setUp() {
            // Добавляем тестовые теги
            jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", 1L, "технологии");
            jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", 2L, "программирование");
        }

        @Test
        void testAddPostWithTags() {
            // Данные для нового поста
            String title = "Новый пост";
            String image = "https://example.com/image.jpg";
            String text = "Это текст нового поста";
            List<Long> tagIds = Arrays.asList(1L, 2L); // Список ID тегов

            // Добавление поста
            postRepository.addPost(title, image, text, tagIds);

            // Проверяем, что пост был добавлен
            Long postId = jdbcTemplate.queryForObject(
                    "SELECT id FROM posts WHERE title = ?", Long.class, title);
            assertNotNull(postId, "Пост должен быть добавлен в базу данных");

            // Проверяем, что связи с тегами были добавлены
            for (Long tagId : tagIds) {
                Integer count = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM posts_tags WHERE post_id = ? AND tag_id = ?",
                        Integer.class, postId, tagId);
                assertEquals(1, count, "Связь между постом и тегом с ID " + tagId + " должна быть добавлена");
            }
        }

        @Test
        void testAddPostWithoutTags() {
            // Данные для нового поста
            String title = "Пост без тегов";
            String image = "https://example.com/image.jpg";
            String text = "Это текст поста без тегов";
            List<Long> tagIds = null; // Теги не переданы

            // Добавление поста
            postRepository.addPost(title, image, text, tagIds);

            // Проверяем, что пост был добавлен
            Long postId = jdbcTemplate.queryForObject(
                    "SELECT id FROM posts WHERE title = ?", Long.class, title);
            assertNotNull(postId, "Пост должен быть добавлен в базу данных");

            // Проверяем, что связи с тегами отсутствуют
            Integer tagRelationCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts_tags WHERE post_id = ?", Integer.class, postId);
            assertEquals(0, tagRelationCount, "Связи с тегами должны отсутствовать");
        }

        @Test
        void testAddPostWithInvalidTagIds() {
            // Данные для нового поста
            String title = "Пост с несуществующими тегами";
            String image = "https://example.com/image.jpg";
            String text = "Это текст поста с несуществующими тегами";
            List<Long> tagIds = Arrays.asList(99L, 100L); // Несуществующие ID тегов

            // Добавление поста
            assertThrows(Exception.class, () -> {
                postRepository.addPost(title, image, text, tagIds);
            }, "Должно быть выброшено исключение при попытке связать пост с несуществующими тегами");
        }
    }

    @Nested
    class UpdatePostTests {
        @BeforeEach
        void setUp() {
            // Добавляем тестовые данные
            jdbcTemplate.update(
                    "INSERT INTO posts (id, title, image, text) VALUES (?, ?, ?, ?)",
                    1L, "Старый пост", "https://example.com/old-image.jpg", "Старый текст"
            );
            jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", 1L, "старый тег");
            jdbcTemplate.update("INSERT INTO posts_tags (post_id, tag_id) VALUES (?, ?)", 1L, 1L);

            jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", 2L, "новый тег");
        }

        @Test
        void testUpdatePostWithTags() {
            // Данные для обновления
            long postId = 1; // ID существующего поста
            String title = "Обновлённый пост";
            String image = "https://example.com/new-image.jpg";
            String text = "Это обновлённый текст поста";
            List<Long> tagIds = Arrays.asList(2L); // Список ID тегов

            // Обновление поста
            postRepository.updatePost(postId, title, image, text, tagIds);

            // Проверяем, что пост был обновлён
            Map<String, Object> post = jdbcTemplate.queryForMap(
                    "SELECT * FROM posts WHERE id = ?", postId);
            assertEquals(title, post.get("title"), "Название поста должно быть обновлено");
            assertEquals(image, post.get("image"), "Ссылка на картинку должна быть обновлена");
            assertEquals(text, post.get("text"), "Текст поста должен быть обновлён");

            // Проверяем, что старые связи удалены
            Integer oldTagRelationCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts_tags WHERE post_id = ? AND tag_id = 1",
                    Integer.class, postId);
            assertEquals(0, oldTagRelationCount, "Старая связь с тегом 'старый тег' должна быть удалена");

            // Проверяем, что новые теги и связи добавлены
            for (Long tagId : tagIds) {
                Integer count = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM posts_tags WHERE post_id = ? AND tag_id = ?",
                        Integer.class, postId, tagId);
                assertEquals(1, count, "Связь между постом и тегом с ID " + tagId + " должна быть добавлена");
            }
        }

        @Test
        void testUpdatePostWithoutTags() {
            // Данные для обновления
            long postId = 1; // ID существующего поста
            String title = "Обновлённый пост";
            String image = "https://example.com/new-image.jpg";
            String text = "Это обновлённый текст поста";
            List<Long> tagIds = null; // Теги не переданы

            // Обновление поста
            postRepository.updatePost(postId, title, image, text, tagIds);

            // Проверяем, что пост был обновлён
            Map<String, Object> post = jdbcTemplate.queryForMap(
                    "SELECT * FROM posts WHERE id = ?", postId);
            assertEquals(title, post.get("title"), "Название поста должно быть обновлено");
            assertEquals(image, post.get("image"), "Ссылка на картинку должна быть обновлена");
            assertEquals(text, post.get("text"), "Текст поста должен быть обновлён");

            // Проверяем, что связи с тегами отсутствуют
            Integer tagRelationCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts_tags WHERE post_id = ?", Integer.class, postId);
            assertEquals(0, tagRelationCount, "Связи с тегами должны отсутствовать");
        }

        @Test
        void testUpdatePostWithInvalidTagIds() {
            // Данные для обновления
            long postId = 1; // ID существующего поста
            String title = "Обновлённый пост";
            String image = "https://example.com/new-image.jpg";
            String text = "Это обновлённый текст поста";
            List<Long> tagIds = Arrays.asList(99L, 100L); // Несуществующие ID тегов

            // Обновление поста
            assertThrows(Exception.class, () -> {
                postRepository.updatePost(postId, title, image, text, tagIds);
            }, "Должно быть выброшено исключение при попытке связать пост с несуществующими тегами");
        }
    }

    @Nested
    class DeletePostTests {
        @Test
        void testDeletePostWithRelatedData() {
            // Добавляем тестовые данные
            jdbcTemplate.update(
                    "INSERT INTO posts (id, title, image, text) VALUES (?, ?, ?, ?)",
                    1L, "Тестовый пост", "https://example.com/image.jpg", "Текст поста"
            );
            jdbcTemplate.update(
                    "INSERT INTO comments (id, text, post_id) VALUES (?, ?, ?)",
                    1L, "Тестовый комментарий", 1L
            );
            jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", 1L, "тег");
            jdbcTemplate.update("INSERT INTO posts_tags (post_id, tag_id) VALUES (?, ?)", 1L, 1L);

            // Удаляем пост с ID = 1
            postRepository.deletePost(1L);

            // Проверяем, что пост удалён
            Integer postCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts WHERE id = ?", Integer.class, 1L);
            assertEquals(0, postCount, "Пост должен быть удалён");

            // Проверяем, что комментарии удалены
            Integer commentCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM comments WHERE post_id = ?", Integer.class, 1L);
            assertEquals(0, commentCount, "Комментарии должны быть удалены");

            // Проверяем, что связи с тегами удалены
            Integer tagRelationCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts_tags WHERE post_id = ?", Integer.class, 1L);
            assertEquals(0, tagRelationCount, "Связи с тегами должны быть удалены");
        }

        @Test
        void testDeletePostWithNoRelatedData() {
            // Добавляем пост без комментариев и тегов
            jdbcTemplate.update(
                    "INSERT INTO posts (id, title, image, text) VALUES (?, ?, ?, ?)",
                    2L, "Пост без связей", "https://example.com/image.jpg", "Текст поста"
            );

            // Удаляем пост с ID = 2
            postRepository.deletePost(2L);

            // Проверяем, что пост удалён
            Integer postCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts WHERE id = ?", Integer.class, 2L);
            assertEquals(0, postCount, "Пост должен быть удалён");

            // Проверяем, что комментарии и связи с тегами отсутствуют
            Integer commentCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM comments WHERE post_id = ?", Integer.class, 2L);
            assertEquals(0, commentCount, "Комментарии должны отсутствовать");

            Integer tagRelationCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts_tags WHERE post_id = ?", Integer.class, 2L);
            assertEquals(0, tagRelationCount, "Связи с тегами должны отсутствовать");
        }
    }
    @Test
    void testIncrementPostLikes() {
        // Добавляем тестовый пост
        jdbcTemplate.update(
                "INSERT INTO posts (id, title, image, text, likes) VALUES (?, ?, ?, ?, ?)",
                1L, "Тестовый пост", "https://example.com/image.jpg", "Текст поста", 10
        );

        // Проверяем начальное количество лайков
        Integer initialLikes = jdbcTemplate.queryForObject(
                "SELECT likes FROM posts WHERE id = ?", Integer.class, 1L);
        assertEquals(10, initialLikes, "Начальное количество лайков должно быть 10");

        // Увеличиваем количество лайков
        postRepository.incrementPostLikes(1L);

        // Проверяем, что количество лайков увеличилось на 1
        Integer updatedLikes = jdbcTemplate.queryForObject(
                "SELECT likes FROM posts WHERE id = ?", Integer.class, 1L);
        assertEquals(11, updatedLikes, "Количество лайков должно увеличиться на 1");
    }
}
