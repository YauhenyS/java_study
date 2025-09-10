package smulko.yauheni.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smulko.yauheni.entity.Comment;
import smulko.yauheni.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment addComment(
            @RequestParam Long postId,
            @RequestParam Long userId,
            @RequestParam @NotBlank String content) {
        return commentService.addComment(postId, userId, content);
    }

    @GetMapping("/post/{postId}")
    public Iterable<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}
