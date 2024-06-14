package jim3xe.web.jim3xeprepwar.controller;

import jim3xe.web.jim3xeprepwar.dto.PostDTO;
import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.model.User;
import jim3xe.web.jim3xeprepwar.repository.UserRepository;
import jim3xe.web.jim3xeprepwar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody PostDTO postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setSlug(postDto.getSlug());
        post.setBody(postDto.getBody());
        post.setStatus(postDto.getStatus());
        Optional<User> userOptional = userRepository.findById(postDto.getId());
        User user = userOptional.get();
        post.setUser(user);
        Post createdPost = postService.addPost(post);
        return ResponseEntity.ok(createdPost);
    }
}
