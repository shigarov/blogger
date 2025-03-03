package shigarov.practicum.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;
import shigarov.practicum.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAllPosts(pageable);
    }

    public Page<Post> findAllByTag(Pageable pageable, Long tagId) {
        return postRepository.findAllPostsByTag(pageable, tagId);
    }

    public Optional<Post> findById(long id) {
        return postRepository.findPostById(id);
    }

    public void addPost(Post post) {
        postRepository.addPost(
                post.getTitle(),
                post.getImage(),
                post.getText(),
                post.getTagIds()
        );
    }

    public void updatePost(Post post) {
        postRepository.updatePost(
                post.getId(),
                post.getTitle(),
                post.getImage(),
                post.getText(),
                post.getTagIds()
        );
    }

    public Optional<Tag> findTagById(long id) {
        return postRepository.findTagById(id);
    }

    public List<Tag> findAllTags() {
        return postRepository.findAllTags();
    }

    public void addComment(String commentText, Long postId) {
        postRepository.addComment(commentText, postId);
    }

    public void updateComment(Long commentId, String commentText) {
        postRepository.updateComment(commentId, commentText);
    }

}