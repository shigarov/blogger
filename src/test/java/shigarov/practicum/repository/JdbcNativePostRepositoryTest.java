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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {DataSourceConfiguration.class, JdbcNativePostRepository.class})
@TestPropertySource(locations = "classpath:test-application.properties")
public class JdbcNativePostRepositoryTest {
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
    void findAll_shouldReturnAllPostsForPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> page = postRepository.findAllPosts(pageable);

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
        Page<Post> page = postRepository.findAllPostsByTag(pageable, 1L);

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
    void findById_shouldReturnPostById() {
        Optional<Post> postOptional = postRepository.findPostById(1L);

        Post post = postOptional.orElse(null);

        assertNotNull(post);

        // (1, 'Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 10),
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
    void createPost_shouldAddNewPostToDatabase() {
        Post post = new Post();
        post.setId(11L);
        post.setTitle("Тестовый пост");
        post.setImage("image1.png");
        post.setText("Текст тестового поста");
        post.setLikes(0);

        //post.addTag(new Tag(1L, "технологии"));
        //post.addTag(new Tag(2L, "блог"));

        Tag savedTag1 = postRepository.findTagById(1L).orElse(null);
        assertNotNull(savedTag1);
        post.addTag(savedTag1);

        Tag savedTag2 = postRepository.findTagById(2L).orElse(null);
        assertNotNull(savedTag2);
        post.addTag(savedTag2);

        postRepository.createPost(post);

        Post savedPost = postRepository.findPostById(11L).orElse(null);

        assertNotNull(savedPost);
        assertEquals("Тестовый пост", savedPost.getTitle());
        assertEquals("image1.png", savedPost.getImage());
        assertEquals("Текст тестового поста", savedPost.getText());

        List<Tag> tags = savedPost.getTags();
        assertNotNull(tags);
        assertEquals(2, tags.size());
        Tag tag = tags.getFirst();
        assertEquals(1L, tag.getId());
    }

    @Test
    void updatePost_shouldUpdatePost() {
        Post post = postRepository.findPostById(1L).orElse(null);
        assertNotNull(post);
        assertEquals(1L, post.getId());

        post.setTitle("Новый Пост 1");
        post.setText("Новый текст для Поста 1");

        postRepository.updatePost(post);

        Post updatedPost = postRepository.findPostById(1L).orElse(null);
        assertNotNull(updatedPost);
        assertEquals(1L, updatedPost.getId());
        assertEquals("Новый Пост 1", updatedPost.getTitle());
        assertEquals("Новый текст для Поста 1", updatedPost.getText());
    }

    @Test
    void deletePost_shouldRemovePostFromDatabase() {
        Post post = postRepository.findPostById(1L).orElse(null);
        assertNotNull(post);
        assertEquals(1L, post.getId());

        postRepository.deletePost(post);

        assertThrows(
                NoSuchElementException.class,
                () -> postRepository.findPostById(1L)
        );
    }

    @Test
    void incrementLikes_shouldIncrementLikesOfPost() {
        Post post = postRepository.findPostById(1L).orElse(null);
        assertNotNull(post);
        assertEquals(1L, post.getId());
        assertEquals(10, post.getLikes());

        postRepository.incrementLikes(post);

        Post updatedPost = postRepository.findPostById(1L).orElse(null);
        assertNotNull(updatedPost);
        assertEquals(1L, updatedPost.getId());
        assertEquals(11, updatedPost.getLikes());
    }

    @Test
    void findAllTags_shouldReturnAllTags() {
        List<Tag> tags = postRepository.findAllTags();

        assertNotNull(tags);
        assertEquals(6, tags.size());

        // (1, 'технологии')
        Tag tag = tags.getFirst();
        assertEquals(1L, tag.getId());
        assertEquals("технологии", tag.getName());
    }

    @Test
    void createTag_shouldAddNewTagToDatabase() {
        Tag tag = new Tag();
        tag.setId(7L);
        tag.setName("Тестовый тег");

        postRepository.createTag(tag);

        Tag savedTag = postRepository.findTagById(7L).orElse(null);

        assertNotNull(savedTag);
        assertEquals("Тестовый тег", savedTag.getName());
    }

    @Test
    void createComment_shouldAddNewCommentToDatabase() {
        Comment comment = new Comment();
        comment.setId(22L);
        comment.setText("Тестовый комментарий");

        Post savedPost = postRepository.findPostById(1L).orElse(null);
        assertNotNull(savedPost);

        comment.setPost(savedPost);

        postRepository.createComment(comment);

        Comment savedComment = postRepository.findCommentById(22L).orElse(null);

        assertNotNull(savedComment);
        assertEquals(22L, savedComment.getId());
        assertEquals("Тестовый комментарий", savedComment.getText());
    }

    @Test
    void updateComment_shouldUpdateCommentInDatabase() {
        Comment savedComment = postRepository.findCommentById(1L).orElse(null);

        assertNotNull(savedComment);
        assertEquals(1L, savedComment.getId());
        assertEquals("Отличный пост!", savedComment.getText());
        assertEquals(1L, savedComment.getPost().getId());

        savedComment.setText("Абсолютно новый комментарий!");

        postRepository.updateComment(savedComment);

        Comment updatedComment = postRepository.findCommentById(1L).orElse(null);
        assertEquals(1L, updatedComment.getId());
        assertEquals("Абсолютно новый комментарий!", updatedComment.getText());
        assertEquals(1L, updatedComment.getPost().getId());
    }
}
