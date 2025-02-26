package shigarov.practicum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shigarov.practicum.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByPageAndTag(Pageable pageable, String tag);

    Optional<Post> findById(long id);

    List<Post> findAllByTag(String tag);

    void save(Post post);
}