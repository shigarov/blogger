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
import org.springframework.data.domain.Pageable;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;
import shigarov.practicum.repository.PostRepository;
import shigarov.practicum.repository.TagRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Подключение Mockito
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService = new PostService(postRepository);

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService = new TagService(tagRepository);

//    @Nested
//    class FindTests {
//
//        private Post post;
//        private Tag tag1, tag2;
//
//        @BeforeEach
//        void setUp() {
//            // Инициализация тестового объекта Post
//            post = new Post();
//            post.setId(1L);
//            post.setTitle("Test Post");
//            post.setImage("test.jpg");
//            post.setText("This is a test post.");
//
//            tag1 = new Tag();
//            tag1.setId(1L);
//            tag1.setName("Tag1");
//            post.addTag(tag1);
//            tagService.addTag(tag1);
//
//            tag2 = new Tag();
//            tag2.setId(2L);
//            tag2.setName("Tag2");
//            post.addTag(tag2);
//            tagService.addTag(tag2);
//
//            postService.addPost(post);
//        }
//
//        @Test
//        void testFindAll() {
//            // Подготовка данных
//            Pageable pageable = mock(Pageable.class);
//            Page<Post> page = mock(Page.class);
//            when(postRepository.findAllPosts(pageable)).thenReturn(page);
//
//            // Вызов метода
//            Page<Post> result = postService.findAll(pageable);
//
//            // Проверка
//            assertNotNull(result);
//            verify(postRepository, times(1)).findAllPosts(pageable);
//        }
//
//        @Test
//        void testFindAllByTag() {
//            // Подготовка данных
//            Pageable pageable = mock(Pageable.class);
//            Page<Post> page = mock(Page.class);
//            when(postRepository.findAllPostsByTag(pageable, 1L)).thenReturn(page);
//
//            // Вызов метода
//            Page<Post> result = postService.findAllByTag(pageable, 1L);
//
//            // Проверка
//            assertNotNull(result);
//            verify(postRepository, times(1)).findAllPostsByTag(pageable, 1L);
//        }
//
//        @Test
//        void testFindById() {
//            // Подготовка данных
//            when(postRepository.findPostById(1L)).thenReturn(Optional.of(post));
//
//            // Вызов метода
//            Optional<Post> result = postService.findById(1L);
//
//            // Проверка
//            assertTrue(result.isPresent());
//            assertEquals(post, result.get());
//            verify(postRepository, times(1)).findPostById(1L);
//        }
//    }


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
}
