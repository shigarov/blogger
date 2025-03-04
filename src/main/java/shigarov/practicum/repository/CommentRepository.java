package shigarov.practicum.repository;

import org.springframework.lang.NonNull;
import shigarov.practicum.model.Comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findCommentById(long id);

    void addComment(@NonNull String text, long postId);

    void updateComment(long id, @NonNull String text);
}
