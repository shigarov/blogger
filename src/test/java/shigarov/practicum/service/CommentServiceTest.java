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
    void testAddComment() {
//        String commentText = "Комментарий 1";
//        Long postId = 1L;
//
//        commentService.addComment(comment.getText(), postId);
//
//        verify(commentRepository, times(1)).addComment(commentText, postId);
    }

    @Test
    void testUpdateComment() {
//        // Arrange
//        Long commentId = 1L;
//        String updatedCommentText = "Updated comment";
//
//        // Act
//        commentService.updateComment(commentId, updatedCommentText);
//
//        // Assert
//        verify(commentRepository, times(1)).updateComment(commentId, updatedCommentText);
    }
}
