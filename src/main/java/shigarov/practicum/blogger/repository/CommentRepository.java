package shigarov.practicum.blogger.repository;

import shigarov.practicum.blogger.model.Comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

    void add(Comment comment);

    void update(Comment comment);
}
