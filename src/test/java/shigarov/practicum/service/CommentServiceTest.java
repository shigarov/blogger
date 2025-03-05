package shigarov.practicum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;
import shigarov.practicum.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private Post post;
    private Comment comment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Подготовка тестовых объектов
        post = new Post();
        post.setId(1L);
        post.setTitle("Пост 1");
        post.setText("Текст поста 1");

        comment = new Comment();
        comment.setId(1L);
        comment.setText("Комментарий 1");
        comment.setPost(post);
    }

    @Test
    void testFindTagById() {
        // Подготовка данных
        when(commentRepository.findCommentById(1L)).thenReturn(Optional.of(comment));

        // Вызов метода
        Optional<Comment> result = commentService.findCommentById(1L);

        // Проверка
        assertTrue(result.isPresent());
        assertEquals(comment, result.get());
        verify(commentRepository, times(1)).findCommentById(1L);
    }

    @Test
    void testAddComment() {
        // Подготовка данных
        String commentText = "Комментарий 2";

        // Вызов метода
        commentService.addComment(commentText, post);

        // Проверка
        verify(commentRepository, times(1)).addComment(commentText, post.getId());
    }

    @Test
    void testUpdateComment() {
        // Подготовка данных
        String newCommentText = "Новый комментарий 1";

        // Вызов метода
        commentService.updateComment(comment, newCommentText);

        // Проверка
        verify(commentRepository, times(1)).updateComment(comment.getId(), newCommentText);
    }
}
