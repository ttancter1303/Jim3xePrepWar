package jim3xe.web.jim3xeprepwar.controller;

import jim3xe.web.jim3xeprepwar.dto.TagDTO;
import jim3xe.web.jim3xeprepwar.model.Tag;
import jim3xe.web.jim3xeprepwar.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping

    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody TagDTO tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        Tag createdTag = tagService.addTag(tag);
        return ResponseEntity.ok(createdTag);
    }
}
