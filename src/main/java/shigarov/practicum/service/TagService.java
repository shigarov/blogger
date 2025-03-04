package shigarov.practicum.service;

import org.springframework.stereotype.Service;
import shigarov.practicum.model.Tag;
import shigarov.practicum.repository.PostRepository;
import shigarov.practicum.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public Optional<Tag> findTagById(long id) {
        return tagRepository.findTagById(id);
    }

    public List<Tag> findAllTags() {
        return tagRepository.findAllTags();
    }

}
