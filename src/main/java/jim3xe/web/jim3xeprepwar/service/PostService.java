package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.PostDTO;
import jim3xe.web.jim3xeprepwar.dto.TagIdDTO;
import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return null;
        }
        List<TagIdDTO> tagIds = post.getListTag().stream()
                .map(tag -> new TagIdDTO(tag.getId()))
                .collect(Collectors.toList());

        return new PostDTO(post.getId(), post.getUser().getId(), post.getThumbnail(), post.getTitle(), post.getSlug(), post.getBody(), post.getStatus(), post.getCreatedAt(), tagIds);
    }
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> {
            List<TagIdDTO> tagIds = post.getListTag().stream()
                    .map(tag -> new TagIdDTO(tag.getId()))
                    .collect(Collectors.toList());
            return new PostDTO(post.getId(), post.getUser().getId(), post.getThumbnail(), post.getTitle(), post.getSlug(), post.getBody(), post.getStatus(), post.getCreatedAt(), tagIds);
        }).collect(Collectors.toList());
    }
}
