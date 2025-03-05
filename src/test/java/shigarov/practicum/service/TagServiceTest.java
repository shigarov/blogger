package shigarov.practicum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import shigarov.practicum.model.Tag;
import shigarov.practicum.repository.TagRepository;

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
        MockitoAnnotations.openMocks(this);

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
        when(tagRepository.findAllTags()).thenReturn(List.of(tagOne, tagTwo));

        // Вызов метода
        List<Tag> result = tagService.findAllTags();

        // Проверка
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(tagOne, result.getFirst());
        verify(tagRepository, times(1)).findAllTags();
    }

    @Test
    void testFindTagById() {
        // Подготовка данных
        when(tagRepository.findTagById(1L)).thenReturn(Optional.of(tagOne));

        // Вызов метода
        Optional<Tag> result = tagService.findTagById(1L);

        // Проверка
        assertTrue(result.isPresent());
        assertEquals(tagOne, result.get());
        verify(tagRepository, times(1)).findTagById(1L);
    }

    @Test
    void testAddTag() {
        String tagName = "Тег1";
        // Вызов метода
        tagService.addTag(tagName);

        // Проверка
        verify(tagRepository, times(1)).addTag(tagName);
    }
}
