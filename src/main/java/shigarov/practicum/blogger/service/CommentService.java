package shigarov.practicum.blogger.service;

import org.springframework.stereotype.Service;

import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.repository.CommentRepository;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    public Comment add(Comment comment) {
        return commentRepository.add(comment);
    }

    public void update(Comment comment) {
        commentRepository.update(comment);
    }
}
