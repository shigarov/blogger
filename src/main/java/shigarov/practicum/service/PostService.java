package shigarov.practicum.service;

import org.springframework.stereotype.Service;
import shigarov.practicum.model.Post;
import shigarov.practicum.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public List<Post> findByTag(String tag) {
        return postRepository.findByTag(tag);
    }
    public void save(Post post) {
        postRepository.save(post);
    }
}