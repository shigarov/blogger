package shigarov.practicum.blogger.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.repository.TagRepository;

import java.util.ArrayList;
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

    public List<Tag> findAllByIds(@NonNull List<Long> ids) {
        if (ids.isEmpty()) return null;

        final List<Tag> tags = new ArrayList<>(ids.size());

        // Получаем выбранные теги по их ID и добавляем их в пост
        for (Long id : ids) {
            Optional<Tag> tagOptional = findById(id);
            if (tagOptional.isPresent()) {
                tags.add(tagOptional.get());
            } else {
                new RuntimeException("Тег не найден");
            }
        }

        return tags;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag save(@NonNull final Tag tag) {
        return tagRepository.save(tag);
    }

}
