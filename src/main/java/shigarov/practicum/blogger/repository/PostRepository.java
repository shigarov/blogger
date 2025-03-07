package shigarov.practicum.blogger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import shigarov.practicum.blogger.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    List<Post> findAllPosts();

    Page<Post> findAllPosts(Pageable pageable);

    Page<Post> findAllPostsByTag(Pageable pageable, Long tagId);

    Optional<Post> findPostById(long id);

    void addPost (
            @NonNull String title,
            @Nullable String image,
            @NonNull String text,
            @Nullable List<Long> tagIds
    );

    void updatePost (
            long postId,
            @NonNull String title,
            @Nullable String image,
            @NonNull String text,
            @Nullable List<Long> tagIds
    );

    void deletePost(long postId);

    void incrementPostLikes(long postId);

}