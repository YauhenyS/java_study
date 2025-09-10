package smulko.yauheni.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smulko.yauheni.entity.Comment;
import smulko.yauheni.entity.Post;
import smulko.yauheni.entity.User;
import smulko.yauheni.repository.CommentRepository;
import smulko.yauheni.repository.PostRepository;
import smulko.yauheni.repository.UserRepository;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long postId, Long userId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(content)
                .build();

        return commentRepository.save(comment);
    }

    public Iterable<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
