package shigarov.practicum.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import shigarov.practicum.configuration.DataSourceConfiguration;
import shigarov.practicum.model.Post;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {DataSourceConfiguration.class, JdbcNativePostRepository.class})
@TestPropertySource(locations = "classpath:test-application.properties")
public class PostRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PostRepository postRepository;

//    @Autowired
//    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
        // Очистка базы данных
        jdbcTemplate.execute("DELETE FROM posts_tags");
        jdbcTemplate.execute("DELETE FROM tags");
        jdbcTemplate.execute("DELETE FROM comments");
        jdbcTemplate.execute("DELETE FROM posts");

//        // Добавление тестовых данных в таблицу тегов
//        final String sqlCreateTags = """
//                INSERT INTO tags (id, name) VALUES
//                (1, 'технологии'),
//                (2, 'блог'),
//                (3, 'программирование'),
//                (4, 'жизнь'),
//                (5, 'тест'),
//                (6, 'котики');
//                """;
//        jdbcTemplate.execute(sqlCreateTags);
//
//        // Добавление тестовых данных в таблицу постов
//        final String sqlCreatePosts = """
//                INSERT INTO posts (id, title, image, text, likes) VALUES
//                (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
//                (2, 'Пост 2', 'image2.png', 'Текст поста 2, разбитый на абзацы.', 5),
//                (3, 'Пост 3', 'image3.png', 'Текст поста 3, разбитый на абзацы.', 15),
//                (4, 'Пост 4', NULL, 'Текст поста 4, разбитый на абзацы.', 20),
//                (5, 'Пост 5', NULL, 'Текст поста 5, разбитый на абзацы.', 8),
//                (6, 'Пост 6', NULL, 'Текст поста 6, разбитый на абзацы.', 12),
//                (7, 'Пост 7', NULL, 'Текст поста 7, разбитый на абзацы.', 7),
//                (8, 'Пост 8', NULL, 'Текст поста 8, разбитый на абзацы.', 30),
//                (9, 'Пост 9', NULL, 'Текст поста 9, разбитый на абзацы.', 25),
//                (10, 'Пост 10', NULL, 'Текст поста 10, разбитый на абзацы.', 18);
//                """;
//        jdbcTemplate.execute(sqlCreatePosts);
//
//        // Добавление тестовых данных в таблицу комментариев
//        final String sqlCreatePostsTags = """
//                INSERT INTO posts_tags (post_id, tag_id) VALUES
//                (1, 1),
//                (1, 3),
//                (2, 3),
//                (2, 4),
//                (2, 6),
//                (4, 1),
//                (4, 2),
//                (5, 3),
//                (5, 5),
//                (5, 6),
//                (6, 4),
//                (7, 3),
//                (7, 5),
//                (9, 1),
//                (10, 1),
//                (10, 2);
//                """;
//        jdbcTemplate.execute(sqlCreatePostsTags);
//
//        // Добавление тестовых данных в таблицу комментариев
//        final String sqlCreateComments = """
//                INSERT INTO comments (id, text, post_id) VALUES
//                (1, 'Отличный пост!', 1),
//                (2, 'Спасибо за информацию.', 1),
//                (3, 'Интересно, но можно подробнее?', 2),
//                (4, 'Полезный материал.', 2),
//                (5, 'Согласен с автором.', 2),
//                (6, 'Первый комментарий к посту 3.', 3),
//                (7, 'Классный пост!', 4),
//                (8, 'Много нового узнал.', 4),
//                (9, 'Спасибо за статью.', 5),
//                (10, 'Интересная точка зрения.', 5),
//                (11, 'Жду продолжения.', 5),
//                (12, 'Отлично написано!', 6),
//                (13, 'Полезно для начинающих.', 7),
//                (14, 'Спасибо за советы.', 7),
//                (15, 'Интересный материал.', 8),
//                (16, 'Много полезной информации.', 8),
//                (17, 'Рекомендую к прочтению.', 8),
//                (18, 'Отличный пост!', 9),
//                (19, 'Спасибо за труд.', 9),
//                (20, 'Очень познавательно.', 10),
//                (21, 'Спасибо за подробности.', 10);
//                """;
//
//        jdbcTemplate.execute(sqlCreateComments);
    }

    @Test
    void findAll_shouldReturnAllPostsForPageable() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Post> page = postRepository.findAllPosts(pageable);
//
//        List<Post> posts = page.getContent();
//        assertNotNull(posts);
//        assertEquals(10, posts.size());
//
//        // (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
//        Post post = posts.getFirst();
//        assertEquals(1L, post.getId());
//        assertEquals("Пост 1", post.getTitle());
//
//        List<Comment> comments = post.getComments();
//        assertNotNull(comments);
//        assertEquals(2, comments.size());
//        Comment comment = comments.getFirst();
//        assertEquals(1L, comment.getId());
//
//        List<Tag> tags = post.getTags();
//        assertNotNull(tags);
//        assertEquals(2, tags.size());
//        Tag tag = tags.getFirst();
//        assertEquals(1L, tag.getId());
    }

    @Test
    void findAllByTags_shouldReturnAllPostsByTagsForPageable() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Post> page = postRepository.findAllPostsByTag(pageable, 1L);
//
//        List<Post> posts = page.getContent();
//        assertNotNull(posts);
//        assertEquals(4, posts.size());
//
//        // (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
//        Post post = posts.getFirst();
//        assertEquals(1L, post.getId());
//        assertEquals("Пост 1", post.getTitle());
//
//        List<Comment> comments = post.getComments();
//        assertNotNull(comments);
//        assertEquals(2, comments.size());
//        Comment comment = comments.getFirst();
//        assertEquals(1L, comment.getId());
//
//        List<Tag> tags = post.getTags();
//        assertNotNull(tags);
//        assertEquals(2, tags.size());
//        Tag tag = tags.getFirst();
//        assertEquals(1L, tag.getId());
    }

    @Test
    void findById_shouldReturnPostById() {
//        Optional<Post> postOptional = postRepository.findPostById(1L);
//
//        Post post = postOptional.orElse(null);
//
//        assertNotNull(post);
//
//        // (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
//        assertEquals(1L, post.getId());
//        assertEquals("Пост 1", post.getTitle());
//
//        List<Comment> comments = post.getComments();
//        assertNotNull(comments);
//        assertEquals(2, comments.size());
//        Comment comment = comments.getFirst();
//        assertEquals(1L, comment.getId());
//
//        List<Tag> tags = post.getTags();
//        assertNotNull(tags);
//        assertEquals(2, tags.size());
//        Tag tag = tags.getFirst();
//        assertEquals(1L, tag.getId());
    }

//    @Test
//    void createPost_shouldAddNewPostToDatabase() {
//        Post post = new Post();
//        post.setId(11L);
//        post.setTitle("Тестовый пост");
//        post.setImage("image1.png");
//        post.setText("Текст тестового поста");
//        post.setLikes(0);
//
//        //post.addTag(new Tag(1L, "технологии"));
//        //post.addTag(new Tag(2L, "блог"));
//
//        Tag savedTag1 = postRepository.findTagById(1L).orElse(null);
//        assertNotNull(savedTag1);
//        post.addTag(savedTag1);
//
//        Tag savedTag2 = postRepository.findTagById(2L).orElse(null);
//        assertNotNull(savedTag2);
//        post.addTag(savedTag2);
//
//        postRepository.createPost(post);
//
//        Post savedPost = postRepository.findPostById(11L).orElse(null);
//
//        assertNotNull(savedPost);
//        assertEquals("Тестовый пост", savedPost.getTitle());
//        assertEquals("image1.png", savedPost.getImage());
//        assertEquals("Текст тестового поста", savedPost.getText());
//
//        List<Tag> tags = savedPost.getTags();
//        assertNotNull(tags);
//        assertEquals(2, tags.size());
//        Tag tag = tags.getFirst();
//        assertEquals(1L, tag.getId());
//    }

    @Test
    void updatePost_shouldUpdatePost() {
//        Post post = postRepository.findPostById(1L).orElse(null);
//        assertNotNull(post);
//        assertEquals(1L, post.getId());
//
//        post.setTitle("Новый Пост 1");
//        post.setText("Новый текст для Поста 1");
//
//        postRepository.updatePost(post);
//
//        Post updatedPost = postRepository.findPostById(1L).orElse(null);
//        assertNotNull(updatedPost);
//        assertEquals(1L, updatedPost.getId());
//        assertEquals("Новый Пост 1", updatedPost.getTitle());
//        assertEquals("Новый текст для Поста 1", updatedPost.getText());
    }

    @Test
    void deletePost_shouldRemovePostFromDatabase() {
//        Post post = postRepository.findPostById(1L).orElse(null);
//        assertNotNull(post);
//        assertEquals(1L, post.getId());
//
//        postRepository.deletePost(post);
//
//        assertThrows(
//                NoSuchElementException.class,
//                () -> postRepository.findPostById(1L)
//        );
    }

    @Test
    void incrementLikes_shouldIncrementLikesOfPost() {
//        Post post = postRepository.findPostById(1L).orElse(null);
//        assertNotNull(post);
//        assertEquals(1L, post.getId());
//        assertEquals(10, post.getLikes());
//
//        postRepository.incrementLikes(post);
//
//        Post updatedPost = postRepository.findPostById(1L).orElse(null);
//        assertNotNull(updatedPost);
//        assertEquals(1L, updatedPost.getId());
//        assertEquals(11, updatedPost.getLikes());
    }

    @Test
    void findAllTags_shouldReturnAllTags() {
//        List<Tag> tags = postRepository.findAllTags();
//
//        assertNotNull(tags);
//        assertEquals(6, tags.size());
//
//        // (1, 'технологии')
//        Tag tag = tags.getFirst();
//        assertEquals(1L, tag.getId());
//        assertEquals("технологии", tag.getName());
    }

    @Test
    void createTag_shouldAddNewTagToDatabase() {
//        Tag tag = new Tag();
//        tag.setId(7L);
//        tag.setName("Тестовый тег");
//
//        postRepository.createTag(tag);
//
//        Tag savedTag = postRepository.findTagById(7L).orElse(null);
//
//        assertNotNull(savedTag);
//        assertEquals("Тестовый тег", savedTag.getName());
    }

    @Test
    void createComment_shouldAddNewCommentToDatabase() {
//        Comment comment = new Comment();
//        comment.setId(22L);
//        comment.setText("Тестовый комментарий");
//
//        Post savedPost = postRepository.findPostById(1L).orElse(null);
//        assertNotNull(savedPost);
//
//        comment.setPost(savedPost);
//
//        postRepository.createComment(comment);
//
//        Comment savedComment = postRepository.findCommentById(22L).orElse(null);
//
//        assertNotNull(savedComment);
//        assertEquals(22L, savedComment.getId());
//        assertEquals("Тестовый комментарий", savedComment.getText());
    }

    @Test
    void updateComment_shouldUpdateCommentInDatabase() {
//        Comment savedComment = postRepository.findCommentById(1L).orElse(null);
//
//        assertNotNull(savedComment);
//        assertEquals(1L, savedComment.getId());
//        assertEquals("Отличный пост!", savedComment.getText());
//        assertEquals(1L, savedComment.getPost().getId());
//
//        savedComment.setText("Абсолютно новый комментарий!");
//
//        postRepository.updateComment(savedComment);
//
//        Comment updatedComment = postRepository.findCommentById(1L).orElse(null);
//        assertEquals(1L, updatedComment.getId());
//        assertEquals("Абсолютно новый комментарий!", updatedComment.getText());
//        assertEquals(1L, updatedComment.getPost().getId());
    }

    @Nested
    class TestFindsPosts {
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

    @Test
    void testAddPostWithTags() {
        // Данные для теста
        String title = "Новый пост";
        String image = "https://example.com/image.jpg";
        String text = "Это текст нового поста";
        List<String> tags = Arrays.asList("технологии", "программирование");

        // Вызов тестируемого метода
        postRepository.addPostWithTags(title, image, text, tags);

        // Проверка, что пост был добавлен
        Long postId = jdbcTemplate.queryForObject(
                "SELECT id FROM posts WHERE title = ?", Long.class, title);
        assertNotNull(postId, "Пост должен быть добавлен в базу данных");

        // Проверка, что теги были добавлены
        for (String tag : tags) {
            Long tagId = jdbcTemplate.queryForObject(
                    "SELECT id FROM tags WHERE name = ?", Long.class, tag);
            assertNotNull(tagId, "Тег '" + tag + "' должен быть добавлен в базу данных");

            // Проверка, что связь между постом и тегом была добавлена
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts_tags WHERE post_id = ? AND tag_id = ?",
                    Integer.class, postId, tagId);
            assertEquals(1, count, "Связь между постом и тегом '" + tag + "' должна быть добавлена");
        }

        // Проверка содержимого поста
        Map<String, Object> post = jdbcTemplate.queryForMap(
                "SELECT * FROM posts WHERE id = ?", postId);
        assertEquals(title, post.get("title"), "Название поста должно совпадать");
        assertEquals(image, post.get("image"), "Ссылка на картинку должна совпадать");
        assertEquals(text, post.get("text"), "Текст поста должен совпадать");
        assertEquals(0, post.get("likes"), "Счётчик лайков должен быть 0 по умолчанию");
    }

    @Test
    void testAddPostWithExistingTags() {
        // Добавляем тег заранее
        jdbcTemplate.update("INSERT INTO tags (name) VALUES (?)", "технологии");

        // Данные для теста
        String title = "Пост с существующим тегом";
        String image = "https://example.com/image.jpg";
        String text = "Это текст поста";
        List<String> tags = Arrays.asList("технологии", "новый тег");

        // Вызов тестируемого метода
        postRepository.addPostWithTags(title, image, text, tags);

        // Проверка, что пост был добавлен
        Long postId = jdbcTemplate.queryForObject(
                "SELECT id FROM posts WHERE title = ?", Long.class, title);
        assertNotNull(postId, "Пост должен быть добавлен в базу данных");

        // Проверка, что существующий тег не был дублирован
        Integer tagCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM tags WHERE name = ?", Integer.class, "технологии");
        assertEquals(1, tagCount, "Тег 'технологии' не должен быть дублирован");

        // Проверка, что новый тег был добавлен
        Long newTagId = jdbcTemplate.queryForObject(
                "SELECT id FROM tags WHERE name = ?", Long.class, "новый тег");
        assertNotNull(newTagId, "Тег 'новый тег' должен быть добавлен в базу данных");

        // Проверка связей
        Integer relationCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM posts_tags WHERE post_id = ?", Integer.class, postId);
        assertEquals(2, relationCount, "Должно быть две связи между постом и тегами");
    }

    @Test
    void testUpdatePostWithTags() {
        // Добавляем тестовый пост и теги
        jdbcTemplate.update(
                "INSERT INTO posts (id, title, image, text) VALUES (?, ?, ?, ?)",
                1L, "Старый пост", "https://example.com/old-image.jpg", "Старый текст"
        );
        jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", 1L, "старый тег");
        jdbcTemplate.update("INSERT INTO posts_tags (post_id, tag_id) VALUES (1, 1)");

        // Данные для обновления
        long postId = 1; // ID существующего поста
        String title = "Обновлённый пост";
        String image = "https://example.com/new-image.jpg";
        String text = "Это обновлённый текст поста";
        List<String> tags = Arrays.asList("обновление", "программирование");

        // Вызов тестируемого метода
        postRepository.updatePost(postId, title, image, text, tags);

        // Проверка, что пост был обновлён
        Map<String, Object> post = jdbcTemplate.queryForMap(
                "SELECT * FROM posts WHERE id = ?", postId);
        assertEquals(title, post.get("title"), "Название поста должно быть обновлено");
        assertEquals(image, post.get("image"), "Ссылка на картинку должна быть обновлена");
        assertEquals(text, post.get("text"), "Текст поста должен быть обновлён");

        // Проверка, что старые связи удалены
        Integer oldTagRelationCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM posts_tags WHERE post_id = ? AND tag_id = 1",
                Integer.class, postId);
        assertEquals(0, oldTagRelationCount, "Старая связь с тегом 'старый тег' должна быть удалена");

        // Проверка, что новые теги и связи добавлены
        for (String tag : tags) {
            Long tagId = jdbcTemplate.queryForObject(
                    "SELECT id FROM tags WHERE name = ?", Long.class, tag);
            assertNotNull(tagId, "Тег '" + tag + "' должен быть добавлен в базу данных");

            // Проверка, что связь между постом и тегом была добавлена
            Integer count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM posts_tags WHERE post_id = ? AND tag_id = ?",
                    Integer.class, postId, tagId);
            assertEquals(1, count, "Связь между постом и тегом '" + tag + "' должна быть добавлена");
        }
    }

    @Test
    void testUpdatePostWithExistingTags() {
        // Добавляем тестовый пост и теги
        jdbcTemplate.update(
                "INSERT INTO posts (id, title, image, text) VALUES (?, ?, ?, ?)",
                1L, "Старый пост", "https://example.com/old-image.jpg", "Старый текст"
        );
        jdbcTemplate.update("INSERT INTO tags (id, name) VALUES (?, ?)", 1L, "старый тег");
        jdbcTemplate.update("INSERT INTO posts_tags (post_id, tag_id) VALUES (1, 1)");

        // Добавляем тег заранее
        jdbcTemplate.update("INSERT INTO tags (name) VALUES ('программирование')");

        // Данные для обновления
        long postId = 1; // ID существующего поста
        String title = "Обновлённый пост";
        String image = "https://example.com/new-image.jpg";
        String text = "Это обновлённый текст поста";
        List<String> tags = Arrays.asList("программирование", "новый тег");

        // Вызов тестируемого метода
        postRepository.updatePost(postId, title, image, text, tags);

        // Проверка, что пост был обновлён
        Map<String, Object> post = jdbcTemplate.queryForMap(
                "SELECT * FROM posts WHERE id = ?", postId);
        assertEquals(title, post.get("title"), "Название поста должно быть обновлено");
        assertEquals(image, post.get("image"), "Ссылка на картинку должна быть обновлена");
        assertEquals(text, post.get("text"), "Текст поста должен быть обновлён");

        // Проверка, что существующий тег не был дублирован
        Integer tagCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM tags WHERE name = ?", Integer.class, "программирование");
        assertEquals(1, tagCount, "Тег 'программирование' не должен быть дублирован");

        // Проверка, что новый тег был добавлен
        Long newTagId = jdbcTemplate.queryForObject(
                "SELECT id FROM tags WHERE name = ?", Long.class, "новый тег");
        assertNotNull(newTagId, "Тег 'новый тег' должен быть добавлен в базу данных");

        // Проверка связей
        Integer relationCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM posts_tags WHERE post_id = ?", Integer.class, postId);
        assertEquals(2, relationCount, "Должно быть две связи между постом и тегами");
    }

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
