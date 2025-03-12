package shigarov.practicum.blogger.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.service.CommentService;
import shigarov.practicum.blogger.service.PostService;
import shigarov.practicum.blogger.storage.StorageService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    @MockitoBean
    private CommentService commentService;

    @MockitoBean
    private StorageService storageService;

    private Post postOne;
    private Comment commentOne;

    @BeforeEach
    public void setUp() {
        postOne = new Post(1L, "Заголовок 1", null, "Текст 1");
        commentOne = new Comment(1L, "Комментарий 1", postOne);
    }

    @Test
    void testAddComment() throws Exception {
        when(postService.findById(1L)).thenReturn(Optional.of(postOne));
        when(commentService.add(any(Comment.class))).thenReturn(any(Comment.class));

        mockMvc.perform(post("/posts/1/comments/add")
                        .param("commentText", "Комментарий 2")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        verify(commentService, times(1)).add(any(Comment.class));
    }

    @Test
    void testEditComment() throws Exception {
        mockMvc.perform(get("/posts/1/comments/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1?editingCommentId=1"));
    }

    @Test
    void testUpdateComment() throws Exception {
        when(commentService.findById(1L)).thenReturn(Optional.of(commentOne));
        doNothing().when(commentService).update(commentOne);

        mockMvc.perform(post("/posts/1/comments/update/1")
                        .param("commentText", "Обновленный комментарий 1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));

        verify(commentService, times(1)).update(commentOne);
    }
}
