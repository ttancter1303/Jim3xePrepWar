package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.PostDTO;

import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.model.Tag;
import jim3xe.web.jim3xeprepwar.model.User;
import jim3xe.web.jim3xeprepwar.repository.PostRepository;
import jim3xe.web.jim3xeprepwar.repository.TagRepository;
import jim3xe.web.jim3xeprepwar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    public PostDTO createPost(PostDTO postDTO) {
        Post post = convertToEntity(postDTO);

        String title = StringUtils.cleanPath(postDTO.getTitle());
        String slug = removeAccents(title)
                .replaceAll("[^a-zA-Z0-9-]", "-")
                .toLowerCase();
        post.setSlug(slug);

        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToDTO(post);
    }
    private String removeAccents(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    public PostDTO updatePost(int id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setThumbnail(postDTO.getThumbnail());
        post.setTitle(postDTO.getTitle());
        post.setSlug(postDTO.getSlug());
        post.setBody(postDTO.getBody());
        post.setStatus(postDTO.getStatus());

        User user = userRepository.findById(postDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user);

        List<Tag> tags = tagRepository.findAllById(postDTO.getTagIds());
        post.setListTag(tags);

        Post updatedPost = postRepository.save(post);
        return convertToDTO(updatedPost);
    }

    public void deletePost(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setThumbnail(postDTO.getThumbnail());
        post.setTitle(postDTO.getTitle());
        post.setSlug(postDTO.getSlug());
        post.setBody(postDTO.getBody());
        post.setStatus(postDTO.getStatus());

        User user = userRepository.findById(postDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user);

        List<Tag> tags = tagRepository.findAllById(postDTO.getTagIds());
        post.setListTag(tags);

        return post;
    }

    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setThumbnail(post.getThumbnail());
        postDTO.setTitle(post.getTitle());
        postDTO.setSlug(post.getSlug());
        postDTO.setBody(post.getBody());
        postDTO.setStatus(post.getStatus());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUserId(post.getUser().getId());

        if (post.getListTag() != null) {
            postDTO.setTagIds(post.getListTag().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList()));
        } else {
            postDTO.setTagIds(new ArrayList<>());
        }

        return postDTO;
    }

}
