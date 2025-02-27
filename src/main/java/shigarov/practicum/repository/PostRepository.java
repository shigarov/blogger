package shigarov.practicum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    void createPost(Post post);

    List<Tag> findAllTags();

    Optional<Tag> findTagById(long id);

    void createTag(Tag tag);

    Optional<Comment> findCommentById(long id);

    void createComment(Comment comment);

    void updateComment(Comment comment);
}