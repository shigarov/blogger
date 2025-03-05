package shigarov.practicum.service;

import org.springframework.stereotype.Service;
import shigarov.practicum.repository.CommentRepository;
import shigarov.practicum.repository.PostRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(String commentText, Long postId) {
        commentRepository.addComment(commentText, postId);
    }

    public void updateComment(Long commentId, String commentText) {
        commentRepository.updateComment(commentId, commentText);
    }
}
