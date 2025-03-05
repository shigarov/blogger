package shigarov.practicum.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;
import shigarov.practicum.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post postOne, postTwo;
    private Tag tagOne, tagTwo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Подготовка тестовых объектов
        postOne = new Post();
        postOne.setId(1L);
        postOne.setTitle("Пост 1");
        postOne.setText("Текст поста 1");

        postTwo = new Post();
        postTwo.setId(2L);
        postTwo.setTitle("Пост 2");
        postTwo.setText("Текст поста 2");

        tagOne = new Tag();
        tagOne.setId(1L);
        tagOne.setName("Тег1");

        tagTwo = new Tag();
        tagTwo.setId(2L);
        tagTwo.setName("Тег2");

        postOne.addTag(tagOne);
        postOne.addTag(tagTwo);
        postTwo.addTag(tagOne);
    }

    @Test
    void testFindAllPosts() {
        // Подготовка данных
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> page = new PageImpl<>(List.of(postOne, postTwo), pageable, 2);
        when(postRepository.findAllPosts(pageable)).thenReturn(page);

        // Вызов метода
        Page<Post> resultPage = postService.findAll(pageable);

        // Проверка
        assertNotNull(resultPage);
        assertEquals(2, resultPage.getContent().size());
        verify(postRepository, times(1)).findAllPosts(pageable);
    }

    @Test
    void testFindAllPostsByTag() {
        // Подготовка данных
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> page = new PageImpl<>(List.of(postOne), pageable, 2);
        Long tagId = tagTwo.getId();
        when(postRepository.findAllPostsByTag(pageable, tagId)).thenReturn(page);

        // Вызов метода
        Page<Post> resultPage = postRepository.findAllPostsByTag(pageable, tagId);

        // Проверка
        assertNotNull(resultPage);
        assertEquals(1, resultPage.getContent().size());
        verify(postRepository, times(1)).findAllPostsByTag(pageable, tagId);
    }

    @Test
    void testFindPostById() {
        // Подготовка данных
        when(postRepository.findPostById(1L)).thenReturn(Optional.of(postOne));

        // Вызов метода
        Optional<Post> resultOptional = postRepository.findPostById(1L);

        // Проверка
        assertTrue(resultOptional.isPresent());
        assertEquals(postOne, resultOptional.get());
        verify(postRepository, times(1)).findPostById(1L);
    }

    @Test
    void testAddPost() {
        // Вызов метода
        postService.addPost(postOne);

        // Проверка
        verify(postRepository, times(1)).addPost(
                postOne.getTitle(),
                postOne.getImage(),
                postOne.getText(),
                postOne.getTagIds()
        );
    }

    @Test
    void testUpdatePost() {
        // Вызов метода
        postService.updatePost(postOne);

        // Проверка
        verify(postRepository, times(1)).updatePost(
                postOne.getId(),
                postOne.getTitle(),
                postOne.getImage(),
                postOne.getText(),
                postOne.getTagIds()
        );
    }

    @Test
    void testIncrementLikes() {
        // Вызов метода
        postService.incrementLikes(1L);

        // Проверка
        verify(postRepository, times(1)).incrementPostLikes(1L);
    }

    @Test
    void testDeletePost() {
        // Вызов метода
        postService.deletePost(1L);

        // Проверка
        verify(postRepository, times(1)).deletePost(1L);
    }

}
