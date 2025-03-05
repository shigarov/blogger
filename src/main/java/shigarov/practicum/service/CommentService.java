package shigarov.practicum.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.repository.CommentRepository;
import shigarov.practicum.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> findCommentById(long commentId) {
        return commentRepository.findCommentById(commentId);
    }

    public void addComment(@NonNull String commentText, @NonNull Post post) {
        commentRepository.addComment(commentText, post.getId());
    }

    public void updateComment(@NonNull Comment comment, @NonNull String newCommentText) {
        commentRepository.updateComment(comment.getId(), newCommentText);
    }
}
