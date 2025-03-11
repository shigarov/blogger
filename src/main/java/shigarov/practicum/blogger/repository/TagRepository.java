package shigarov.practicum.blogger.repository;

import org.springframework.lang.NonNull;
import shigarov.practicum.blogger.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    List<Tag> findAll();

    Optional<Tag> findById(long id);

    long add(Tag tag);

    void deleteAll();
}
