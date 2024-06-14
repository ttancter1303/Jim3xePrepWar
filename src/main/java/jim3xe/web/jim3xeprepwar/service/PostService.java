package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }
}
