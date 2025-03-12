package shigarov.practicum.blogger.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.repository.CommentRepository;

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
        //MockitoAnnotations.openMocks(this);

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
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // Вызов метода
        Optional<Comment> result = commentService.findById(1L);

        // Проверка
        assertTrue(result.isPresent());
        assertEquals(comment, result.get());
        verify(commentRepository, times(1)).findById(1L);
    }

    @Test
    void testAddComment() {
        // Подготовка данных
        when(commentService.add(comment)).thenReturn(comment);

        // Вызов метода
        final Comment addedComment = commentService.add(comment);
        final long commentId = addedComment.getId();

        // Проверка
        assertEquals(comment.getId(), commentId);
        verify(commentRepository, times(1)).add(comment);
    }

    @Test
    void testUpdateComment() {
        // Вызов метода
        commentService.update(comment);

        // Проверка
        verify(commentRepository, times(1)).update(comment);
    }
}
