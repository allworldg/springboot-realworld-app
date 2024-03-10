package realworld.tag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import realworld.tag.TagsDto;
import realworld.tag.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public TagsDto getTags() {
        return new TagsDto(tagRepository.getTagList());
    }
}
