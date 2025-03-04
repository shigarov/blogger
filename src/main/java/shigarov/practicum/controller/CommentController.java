package shigarov.practicum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shigarov.practicum.service.CommentService;

@Controller
@RequestMapping("/posts")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Добавление нового комментария
    @PostMapping("/addComment")
    public String addComment(
            @RequestParam(name = "commentText") String text,
            @RequestParam(name = "postId") Long postId
    ) {
        commentService.addComment(text, postId);
        return "redirect:/posts/" + postId;
    }

    // Начало редактирования комментария
    @GetMapping("/editComment")
    public String editComment(
            @RequestParam(name = "editingCommentId") Long editingCommentId,
            @RequestParam(name = "postId") Long postId
    ) {
        return "redirect:/posts/" + postId + "?editingCommentId=" + editingCommentId;
    }

    @PostMapping("/updateComment")
    public String updateComment(
            @RequestParam(name = "commentText") String text,
            @RequestParam(name = "commentId") Long id,
            @RequestParam(name = "postId") Long postId
    ) {
        commentService.updateComment(id, text);
        return "redirect:/posts/" + postId;
    }

}
