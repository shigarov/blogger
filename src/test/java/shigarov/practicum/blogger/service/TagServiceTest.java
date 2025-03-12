package shigarov.practicum.blogger.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.repository.TagRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TagServiceTest {
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    private Tag tagOne, tagTwo;

    @BeforeEach
    void setUp() {
        // Подготовка тестовых объектов
        tagOne = new Tag();
        tagOne.setId(1L);
        tagOne.setName("Тег1");

        tagTwo = new Tag();
        tagTwo.setId(2L);
        tagTwo.setName("Тег2");
    }

    @Test
    void testFindAllTags() {
        // Подготовка данных
        when(tagRepository.findAll()).thenReturn(List.of(tagOne, tagTwo));

        // Вызов метода
        List<Tag> result = tagService.findAll();

        // Проверка
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(tagOne, result.getFirst());
        verify(tagRepository, times(1)).findAll();
    }

    @Test
    void testFindTagById() {
        // Подготовка данных
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tagOne));

        // Вызов метода
        Optional<Tag> result = tagService.findById(1L);

        // Проверка
        assertTrue(result.isPresent());
        assertEquals(tagOne, result.get());
        verify(tagRepository, times(1)).findById(1L);
    }

    @Test
    void testAddTag() {
        // Подготовка данных
        when(tagService.add(any(Tag.class))).thenReturn(any(Tag.class));

        // Вызов метода
        tagService.add(tagOne);

        // Проверка
        verify(tagRepository, times(1)).add(any(Tag.class));
    }
}
