package shigarov.practicum.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void create(Post post) {
        postRepository.createPost(post);
    }

    public List<Tag> findAllTags() {
        return postRepository.findAllTags();
    }
}