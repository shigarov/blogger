package shigarov.practicum.blogger.repository;

import org.springframework.lang.NonNull;
import shigarov.practicum.blogger.model.Comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

    //Comment add(Comment comment);

    //void update(Comment comment);

    Comment save(@NonNull final Comment comment);
}
