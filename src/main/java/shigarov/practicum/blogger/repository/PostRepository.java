package shigarov.practicum.blogger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import shigarov.practicum.blogger.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    List<Post> findAll();

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByTag(Pageable pageable, Long tagId);

    Optional<Post> findById(long id);

    long add(Post post);

    void update(Post post);

    void deleteById(long postId);

    void incrementLikes(long postId);

    void deleteAll();

}