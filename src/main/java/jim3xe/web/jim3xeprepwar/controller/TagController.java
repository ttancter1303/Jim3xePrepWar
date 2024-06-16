package jim3xe.web.jim3xeprepwar.controller;

import jim3xe.web.jim3xeprepwar.dto.TagDTO;
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

    @GetMapping("/{id}")
    public TagDTO getTagById(@PathVariable int id) {
        return tagService.getTagById(id);
    }

    @GetMapping("/all")
    public List<TagDTO> getAllTags() {
        return tagService.getAllTags();
    }
    @PostMapping("/create")
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        return ResponseEntity.ok(tagService.createTag(tagDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable int id, @RequestBody TagDTO tagDTO) {
        return ResponseEntity.ok(tagService.updateTag(id, tagDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable int id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
