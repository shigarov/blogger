package shigarov.practicum.blogger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.service.CommentService;
import shigarov.practicum.blogger.service.PostService;

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
        Optional<Post> postOptional = postService.findById(postId);
        if (postOptional.isPresent()) {
            Comment comment = new Comment();
            comment.setText(commentText);
            comment.setPost(postOptional.get());

            commentService.save(comment);
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
        Optional<Comment> commentOptional = commentService.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(commentText);

            commentService.save(comment);
        }

        return "redirect:/posts/" + postId;
    }

}
