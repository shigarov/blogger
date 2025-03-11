package shigarov.practicum.blogger.service;

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

    public Optional<Tag> findById(long id) {
        return tagRepository.findById(id);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public long add(Tag tag) {
        return tagRepository.add(tag);
    }

}
