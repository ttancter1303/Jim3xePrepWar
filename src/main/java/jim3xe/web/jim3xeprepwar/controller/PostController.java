package jim3xe.web.jim3xeprepwar.controller;

import jim3xe.web.jim3xeprepwar.dto.PostDTO;
import jim3xe.web.jim3xeprepwar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @GetMapping("/all")
    public List<PostDTO> getAllPosts() {
        return postService.getAllPostDTOs();
    }

    // Các phương thức khác của Controller
}
