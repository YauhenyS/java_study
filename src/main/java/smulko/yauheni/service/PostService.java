package smulko.yauheni.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smulko.yauheni.entity.Post;
import smulko.yauheni.entity.Tag;
import smulko.yauheni.entity.User;
import smulko.yauheni.repository.PostRepository;
import smulko.yauheni.repository.TagRepository;
import smulko.yauheni.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public Post createPost(Long userId, String title, String content, Set<String> tagNames) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<Tag> tags = tagNames.stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder().name(tagName).build())))
                .collect(Collectors.toSet());

        Post post = Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .tags(tags)
                .build();

        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public List<Post> searchPosts(String query) {
        return postRepository.searchByContent(query.replace(" ", " & "));
    }

    public List<Post> getPostsByTag(String tagName) {
        return postRepository.findByTag(tagName);
    }

    public Post updatePost(Long id, Long userId, String title, String content, Set<String> tagNames) {
        Post post = getPostById(id);
        if (!post.getUser().getId().equals(userId)) {
            throw new SecurityException("You can only edit your own posts");
        }

        post.setTitle(title);
        post.setContent(content);

        Set<Tag> tags = tagNames.stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(Tag.builder().name(tagName).build())))
                .collect(Collectors.toSet());

        post.getTags().clear();
        post.getTags().addAll(tags);

        return postRepository.save(post);
    }

    public void deletePost(Long id, Long userId) {
        Post post = getPostById(id);
        if (!post.getUser().getId().equals(userId)) {
            throw new SecurityException("You can only delete your own posts");
        }
        postRepository.delete(post);
    }
}
