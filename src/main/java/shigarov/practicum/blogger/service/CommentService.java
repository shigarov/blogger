package shigarov.practicum.blogger.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.repository.CommentRepository;

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
