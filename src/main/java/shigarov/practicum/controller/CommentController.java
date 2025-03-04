package shigarov.practicum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shigarov.practicum.service.CommentService;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Добавление нового комментария
    @PostMapping("/posts/{postId}/comments/add")
    public String addComment(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(name = "commentText") String commentText
    ) {
        commentService.addComment(commentText, postId);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/posts/{postId}/comments/edit/{commentId}")
    public String editComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId
    ) {
        return "redirect:/posts/" + postId + "?editingCommentId=" + commentId;
    }

    @PostMapping("/posts/{postId}/comments/update/{commentId}")
    public String updateComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "commentId") Long commentId,
            @RequestParam(name = "commentText") String commentText//,
    ) {
        commentService.updateComment(commentId, commentText);
        return "redirect:/posts/" + postId;
    }

}
