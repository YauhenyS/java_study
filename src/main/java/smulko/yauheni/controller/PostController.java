package smulko.yauheni.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smulko.yauheni.service.PostService;
import smulko.yauheni.entity.Post;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(
            @RequestParam Long userId,
            @RequestParam @NotBlank String title,
            @RequestParam @NotBlank String content,
            @RequestParam Set<String> tags) {
        return postService.createPost(userId, title, content, tags);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestParam @NotBlank String title,
            @RequestParam @NotBlank String content,
            @RequestParam Set<String> tags) {
        return postService.updatePost(id, userId, title, content, tags);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id, @RequestParam Long userId) {
        postService.deletePost(id, userId);
    }

    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam @NotBlank String q) {
        return postService.searchPosts(q);
    }

    @GetMapping("/tag/{tagName}")
    public List<Post> getPostsByTag(@PathVariable String tagName) {
        return postService.getPostsByTag(tagName);
    }
}
