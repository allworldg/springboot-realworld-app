package realworld.tag.repository;


import java.util.List;

public interface TagRepository {
    List<String> getTagList();

    void createTag(List<String> tagList, Long id);
}
