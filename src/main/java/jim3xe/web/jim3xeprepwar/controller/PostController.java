package jim3xe.web.jim3xeprepwar.controller;

import jim3xe.web.jim3xeprepwar.dto.PostDTO;
import jim3xe.web.jim3xeprepwar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @GetMapping("/all")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }
    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.createPost(postDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable int id, @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.updatePost(id, postDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
