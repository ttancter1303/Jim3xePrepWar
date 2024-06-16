package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.TagDTO;
import jim3xe.web.jim3xeprepwar.model.Post;
import jim3xe.web.jim3xeprepwar.model.Tag;
import jim3xe.web.jim3xeprepwar.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = convertToEntity(tagDTO);
        tag.setCreatedAt(LocalDateTime.now());

        tag.setPosts(null);

        Tag savedTag = tagRepository.save(tag);
        return convertToDTO(savedTag);
    }

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TagDTO getTagById(int id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
        return convertToDTO(tag);
    }

    public TagDTO updateTag(int id, TagDTO tagDTO) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setName(tagDTO.getName());
        Tag updatedTag = tagRepository.save(tag);
        return convertToDTO(updatedTag);
    }

    public void deleteTag(int id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
        tagRepository.delete(tag);
    }

    private Tag convertToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        return tag;
    }

    private TagDTO convertToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        tagDTO.setCreatedAt(tag.getCreatedAt());
        if (tag.getPosts() != null) {
            tagDTO.setPostIds(tag.getPosts().stream()
                    .map(Post::getId)
                    .collect(Collectors.toList()));
        } else {
            tagDTO.setPostIds(new ArrayList<>());
        }

        return tagDTO;
    }

}
