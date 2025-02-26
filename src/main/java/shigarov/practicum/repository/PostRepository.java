package shigarov.practicum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shigarov.practicum.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByTag(Pageable pageable, Long tagId);

    Optional<Post> findById(long id);

    void save(Post post);
}