package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.PostDTO;
import jim3xe.web.jim3xeprepwar.dto.TagIdDTO;
import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUserId(post.getUser().getId());
        postDTO.setThumbnail(post.getThumbnail());
        postDTO.setTitle(post.getTitle());
        postDTO.setSlug(post.getSlug());
        postDTO.setBody(post.getBody());
        postDTO.setStatus(post.getStatus());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setTagIds(post.getListTag().stream()
                .map(tag -> tag.getId())
                .collect(Collectors.toList()));
        return postDTO;
    }

    public List<PostDTO> getAllPostDTOs() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(int id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return convertToDTO(post);
        } else {
            throw new RuntimeException("Post not found with id: " + id);
        }
    }
}
