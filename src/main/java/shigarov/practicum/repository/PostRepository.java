package shigarov.practicum.repository;

import shigarov.practicum.model.Post;
import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    void save(Post post);
}