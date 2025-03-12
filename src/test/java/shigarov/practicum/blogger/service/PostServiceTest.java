package shigarov.practicum.blogger.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.repository.PostRepository;

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
        final List<Post> content = List.of(postOne, postTwo);
        when(postRepository.findAll()).thenReturn(content);

        // Вызов метода
        List<Post> posts = postService.findAll();

        // Проверка
        assertNotNull(posts);
        assertEquals(2, posts.size());

        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testFindAllPostsByPage() {
        // Подготовка данных
        final Pageable pageable = PageRequest.of(0, 10);
        final List<Post> content = List.of(postOne, postTwo);
        final Page<Post> page = new PageImpl<>(content, pageable, 2);

        when(postRepository.findAll(pageable)).thenReturn(page);

        // Вызов метода
        Page<Post> postsPage = postService.findAll(pageable);

        // Проверка
        assertNotNull(postsPage);
        assertEquals(2, postsPage.getContent().size());
        verify(postRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindAllPostsByTag() {
        // Подготовка данных
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> page = new PageImpl<>(List.of(postOne), pageable, 2);
        Long tagId = tagTwo.getId();
        when(postRepository.findAllByTag(pageable, tagId)).thenReturn(page);

        // Вызов метода
        Page<Post> resultPage = postService.findAllByTag(pageable, tagId);

        // Проверка
        assertNotNull(resultPage);
        assertEquals(1, resultPage.getContent().size());
        verify(postRepository, times(1)).findAllByTag(pageable, tagId);
    }

    @Test
    void testFindPostById() {
        // Подготовка данных
        when(postRepository.findById(1L)).thenReturn(Optional.of(postOne));

        // Вызов метода
        Optional<Post> resultOptional = postRepository.findById(1L);

        // Проверка
        assertTrue(resultOptional.isPresent());
        assertEquals(postOne, resultOptional.get());
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testAddPost() {
        // Подготовка данных
        when(postRepository.add(postOne)).thenReturn(postOne);

        // Вызов метода
        final Post addedPost = postService.add(postOne);
        final long postId = addedPost.getId();
        assertEquals(postOne.getId(), postId);

        // Проверка
        verify(postRepository, times(1)).add(postOne);
    }

    @Test
    void testUpdatePost() {
        // Вызов метода
        postService.update(postOne);

        // Проверка
        verify(postRepository, times(1)).update(postOne);
    }

    @Test
    void testIncrementLikes() {
        // Вызов метода
        postService.incrementLikes(1L);

        // Проверка
        verify(postRepository, times(1)).incrementLikes(1L);
    }

    @Test
    void testDeletePost() {
        // Вызов метода
        postService.deleteById(1L);

        // Проверка
        verify(postRepository, times(1)).deleteById(1L);
    }

}