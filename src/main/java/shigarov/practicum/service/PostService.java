package shigarov.practicum.service;

import org.springframework.stereotype.Service;
import shigarov.practicum.model.Post;
import shigarov.practicum.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}