package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.dto.PostIdDTO;
import jim3xe.web.jim3xeprepwar.dto.TagDTO;
import jim3xe.web.jim3xeprepwar.model.Tag;
import jim3xe.web.jim3xeprepwar.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;


    public TagDTO convertToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        tagDTO.setCreatedAt(tag.getCreatedAt());
        tagDTO.setPostIds(tag.getPosts().stream()
                .map(post -> post.getId())
                .collect(Collectors.toList()));
        return tagDTO;
    }

    public List<TagDTO> getAllTagDTOs() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public TagDTO getTagById(int id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent()) {
            Tag tag = tagOptional.get();
            return convertToDTO(tag);
        } else {
            throw new RuntimeException("Tag not found with id: " + id);
        }
    }
}
