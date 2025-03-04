package shigarov.practicum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shigarov.practicum.service.CommentService;

@Controller
@RequestMapping("/posts")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Добавление нового комментария
    @PostMapping("/{postId}/comments/add")
    public String addComment(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(name = "commentText") String text
    ) {
        commentService.addComment(text, postId);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/{postId}/comments/{commentId}/edit")
    public String editComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId
    ) {
        return "redirect:/posts/" + postId + "?editingCommentId=" + commentId;
    }

    @PostMapping("/{postId}/comments/{commentId}/update")
    public String updateComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId,
            @RequestParam(name = "commentText") String text//,
    ) {
        commentService.updateComment(commentId, text);
        return "redirect:/posts/" + postId;
    }

}
