package jim3xe.web.jim3xeprepwar.controller;


import jim3xe.web.jim3xeprepwar.dto.TagDTO;
import jim3xe.web.jim3xeprepwar.model.Tag;
import jim3xe.web.jim3xeprepwar.repository.TagRepository;
import jim3xe.web.jim3xeprepwar.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @GetMapping("/get/{id}")
    public TagDTO getTagById(@PathVariable int id) {
        return tagService.getTagById(id);
    }
}
