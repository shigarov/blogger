package shigarov.practicum.repository;

import shigarov.practicum.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();

    Optional<Post> findById(long id);

    List<Post> findAllByTag(String tag);

    void save(Post post);
}