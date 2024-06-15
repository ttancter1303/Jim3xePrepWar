package jim3xe.web.jim3xeprepwar.controller;

import jim3xe.web.jim3xeprepwar.dto.PostDTO;
import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.model.User;
import jim3xe.web.jim3xeprepwar.repository.PostRepository;
import jim3xe.web.jim3xeprepwar.repository.UserRepository;
import jim3xe.web.jim3xeprepwar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/get/{id}")
    public PostDTO getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }
    @GetMapping("/getAll")
    public List<PostDTO> getAllPost(){
        List<PostDTO> posts = postService.getAllPosts();
        return posts;
    }

}
