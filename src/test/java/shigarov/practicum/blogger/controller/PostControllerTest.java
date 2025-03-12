package shigarov.practicum.blogger.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.service.PostService;
import shigarov.practicum.blogger.service.TagService;
import shigarov.practicum.blogger.storage.StorageService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@WebMvcTest(PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    @MockitoBean
    private TagService tagService;

    @MockitoBean
    private StorageService storageService;

    private Post postOne;
    private Post postTwo;

    private Tag tagOne;
    private Tag tagTwo;

    @BeforeEach
    public void setUp() {
        postOne = new Post(1L, "Заголовок 1", null, "Текст 1");
        postTwo = new Post(2L, "Заголовок 2", null, "Текст 2");

        tagOne = new Tag(1L, "Тег 1");
        tagTwo = new Tag(2L, "Тег 2");

        postOne.addTag(tagOne);
        postTwo.addTag(tagTwo);
    }

    @Test
    void testGetPosts() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        List<Post> content = List.of(postOne, postTwo);
        Page<Post> postsPage = new PageImpl<>(content, pageable, 2);

        when(postService.findAll(pageable)).thenReturn(postsPage);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(xpath("//table/tbody/tr").nodeCount(2))
                .andExpect(xpath("//table/tbody/tr[1]/td[2]").string("Заголовок 1"));

        verify(postService, times(1)).findAll(pageable);
    }

    @Test
    void testGetPostsByTag() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        List<Post> content = List.of(postOne);
        Page<Post> postsPage = new PageImpl<>(content, pageable, 1);

        when(postService.findAllByTag(pageable, 1L)).thenReturn(postsPage);

        mockMvc.perform(get("/posts").param("tagId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(xpath("//table/tbody/tr").nodeCount(1))
                .andExpect(xpath("//table/tbody/tr[1]/td[2]").string("Заголовок 1"));

        verify(postService, times(1)).findAllByTag(pageable, 1L);
    }

    @Test
    void testGetPost() throws Exception {
        when(postService.findById(1L)).thenReturn(Optional.of(postOne));

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"));

        verify(postService, times(1)).findById(1L);
    }

    @Test
    void testAddPost() throws Exception {
        Post postThree = new Post(3L, "Заголовок 3", null, "Текст 3");

        when(postService.add(any(Post.class))).thenReturn(postThree);

        mockMvc.perform(post("/posts/add")
                        .param("title", "Заголовок 3")
                        .param("text", "Текст 3")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));

        verify(postService, times(1)).add(any(Post.class));
    }

    @Test
    void testUpdatePost() throws Exception {
        when(postService.findById(1L)).thenReturn(Optional.of(postOne));
        doNothing().when(postService).update(postOne);

        mockMvc.perform(post("/posts/update/1")
                        .param("title", "Обновленный заголовок 1")
                        .param("text", "Обновленный текст 1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        verify(postService, times(1)).update(postOne);
    }

    @Test
    void testDeletePost() throws Exception {
        doNothing().when(postService).deleteById(1L);

        mockMvc.perform(post("/posts/delete/1")
                        .param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));

        verify(postService, times(1)).deleteById(1L);
    }

    @Test
    void testIncrementPostLikes() throws Exception {
        doNothing().when(postService).incrementLikes(1L);

        mockMvc.perform(post("/posts/incrementLikes/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        verify(postService, times(1)).incrementLikes(1L);
    }

}
