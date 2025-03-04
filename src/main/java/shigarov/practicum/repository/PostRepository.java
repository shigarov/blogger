package shigarov.practicum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;

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


//    Optional<Comment> findCommentById(long id);
//
//    void addComment(@NonNull String text, long postId);
//
//    void updateComment(long id, @NonNull String text);
}