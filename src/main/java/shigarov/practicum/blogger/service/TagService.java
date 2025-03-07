package shigarov.practicum.blogger.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.repository.TagRepository;

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

    public void addTag(@NonNull String name) {
        tagRepository.addTag(name);
    }

}
