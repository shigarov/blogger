package shigarov.practicum.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import shigarov.practicum.configuration.TestDataSourceConfiguration;
import shigarov.practicum.configuration.TestWebConfiguration;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;
import shigarov.practicum.repository.PostRepository;
import shigarov.practicum.repository.TagRepository;

import java.util.Optional;

@SpringJUnitConfig(classes = {TestDataSourceConfiguration.class})
@TestPropertySource(locations = "classpath:test-application.properties")
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService = new PostService(postRepository);

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService = new TagService(tagRepository);

    private Post post;
    private Tag tag;

    @BeforeEach
    void setUp() {
        // Инициализация тестовых объектов
        post = new Post();
        post.setId(1L);
        post.setTitle("Test Post");
        post.setImage("test.jpg");
        post.setText("This is a test post.");
//        post.setTagIds(new Long[]{1L, 2L});
//
//        tag = new Tag();
//        tag.setId(1L);
//        tag.setName("Test Tag");
//
//        post.addTag(tag);
    }

    // Тесты для PostService

//    @Test
//    void testFindAllPosts() {
//        // Подготовка данных
//        postRepository.addPost(post.getTitle(), null, post.getText(), null);
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Post> page = mock(Page.class);
//        when(postRepository.findAllPosts(pageable)).thenReturn(page);
//
//        // Вызов метода
//        Page<Post> result = postService.findAll(pageable);
//
//        // Проверка
//        assertNotNull(result);
//        verify(postRepository, times(1)).findAllPosts(pageable);
//    }
//
//    @Test
//    void testFindPostById() {
//        // Подготовка данных
//        when(postRepository.findPostById(1L)).thenReturn(Optional.of(post));
//
//        // Вызов метода
//        Optional<Post> result = postService.findById(1L);
//
//        // Проверка
//        assertTrue(result.isPresent());
//        assertEquals(post, result.get());
//        verify(postRepository, times(1)).findPostById(1L);
//    }
//
//    @Test
//    void testAddPost() {
//        // Вызов метода
//        postService.addPost(post);
//
//        // Проверка
//        verify(postRepository, times(1)).addPost(
//                post.getTitle(),
//                post.getImage(),
//                post.getText(),
//                post.getTagIds()
//        );
//    }
//
//    @Test
//    void testUpdatePost() {
//        // Вызов метода
//        postService.updatePost(post);
//
//        // Проверка
//        verify(postRepository, times(1)).updatePost(
//                post.getId(),
//                post.getTitle(),
//                post.getImage(),
//                post.getText(),
//                post.getTagIds()
//        );
//    }
//
//    @Test
//    void testIncrementLikes() {
//        // Вызов метода
//        postService.incrementLikes(1L);
//
//        // Проверка
//        verify(postRepository, times(1)).incrementPostLikes(1L);
//    }
//
//    @Test
//    void testDeletePost() {
//        // Вызов метода
//        postService.deletePost(1L);
//
//        // Проверка
//        verify(postRepository, times(1)).deletePost(1L);
//    }

    // Тесты для TagService

//    @Test
//    void testFindTagById() {
//        // Подготовка данных
//        when(tagRepository.findTagById(1L)).thenReturn(Optional.of(tag));
//
//        // Вызов метода
//        Optional<Tag> result = tagService.findTagById(1L);
//
//        // Проверка
//        assertTrue(result.isPresent());
//        assertEquals(tag, result.get());
//        verify(tagRepository, times(1)).findTagById(1L);
//    }
//
//    @Test
//    void testFindAllTags() {
//        // Подготовка данных
//        when(tagRepository.findAllTags()).thenReturn(List.of(tag));
//
//        // Вызов метода
//        List<Tag> result = tagService.findAllTags();
//
//        // Проверка
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(tag, result.get(0));
//        verify(tagRepository, times(1)).findAllTags();
//    }
//
//    @Test
//    void testAddTag() {
//        // Вызов метода
//        tagService.addTag(tag);
//
//        // Проверка
//        verify(tagRepository, times(1)).addTag(
//                tag.getId(),
//                tag.getName()
//        );
//    }
}
