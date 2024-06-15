package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.PostIdDTO;
import jim3xe.web.jim3xeprepwar.dto.TagDTO;
import jim3xe.web.jim3xeprepwar.model.Tag;
import jim3xe.web.jim3xeprepwar.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public TagDTO getTagById(int id) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag == null) {
            return null;
        }

        List<PostIdDTO> postIds = tag.getPosts().stream()
                .map(post -> new PostIdDTO(post.getId()))
                .collect(Collectors.toList());

        return new TagDTO(tag.getId(), tag.getName(), tag.getCreatedAt(), postIds);
    }
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(tag -> {
            List<PostIdDTO> postIds = tag.getPosts().stream()
                    .map(post -> new PostIdDTO(post.getId()))
                    .collect(Collectors.toList());
            return new TagDTO(tag.getId(), tag.getName(), tag.getCreatedAt(), postIds);
        }).collect(Collectors.toList());
    }
}
