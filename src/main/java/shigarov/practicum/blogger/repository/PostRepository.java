package shigarov.practicum.blogger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.lang.NonNull;
import shigarov.practicum.blogger.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    List<Post> findAll();

    Page<Post> findAll(@NonNull final Pageable pageable);

    Page<Post> findAllByTag(@NonNull final Pageable pageable, long tagId);

    Optional<Post> findById(long id);

    Post save(@NonNull final Post post);

    void deleteById(long postId);

    void incrementLikes(long postId);

    void deleteAll();

}