package jim3xe.web.jim3xeprepwar.service;

import jim3xe.web.jim3xeprepwar.model.Tag;
import jim3xe.web.jim3xeprepwar.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }
}
