package shigarov.practicum.blogger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.repository.PostRepository;

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

    public Page<Post> findAll(@NonNull final Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> findAllByTag(@NonNull final Pageable pageable, long tagId) {
        return postRepository.findAllByTag(pageable, tagId);
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public Post save(@NonNull final Post post) {
        return postRepository.save(post);
    }

    public void incrementLikes(long postId) {
        postRepository.incrementLikes(postId);
    }

    public void deleteById(long postId) {
        postRepository.deleteById(postId);
    }

}