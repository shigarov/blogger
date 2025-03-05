package shigarov.practicum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.service.CommentService;
import shigarov.practicum.service.PostService;

import java.util.Optional;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    public CommentController(
            CommentService commentService,
            PostService postService
    ) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // Добавление нового комментария
    @PostMapping("/posts/{postId}/comments/add")
    public String addComment(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(name = "commentText") String commentText
    ) {
        //commentService.addComment(commentText, postId);
        Optional<Post> postOptional = postService.findById(postId);
        if (postOptional.isPresent()) {
            commentService.addComment(commentText, postOptional.get());
        }

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
        //commentService.updateComment(commentId, commentText);

        Optional<Comment> commentOptional = commentService.findCommentById(commentId);
        if (commentOptional.isPresent()) {
            commentService.updateComment(commentOptional.get(), commentText);
        }
        return "redirect:/posts/" + postId;
    }

}
