package shigarov.practicum.blogger.repository;

import org.springframework.lang.NonNull;
import shigarov.practicum.blogger.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    List<Tag> findAllTags();

    Optional<Tag> findTagById(long id);

    void addTag(@NonNull String tag);
}
