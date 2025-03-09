package shigarov.practicum.blogger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.repository.PostRepository;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> findAllByTag(Pageable pageable, Long tagId) {
        return postRepository.findAllByTag(pageable, tagId);
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void add(Post post) {
        postRepository.add(post);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void incrementLikes(Long postId) {
        postRepository.incrementLikes(postId);
    }

    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

}