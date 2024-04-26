package realworld.tag.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import realworld.tag.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MyBatisTagRepository implements TagRepository {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<String> getTagList() {
        List<Tag> tags = tagMapper.selectList(new QueryWrapper<>());
        return tags.stream().map(Tag::getName).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createTag(List<String> tagList, Long articleId) {
        for (String tagName : tagList) {
            try {
                QueryWrapper<Tag> wrapper = new QueryWrapper<>();
                wrapper.eq("name", tagName);
                Tag tag = new Tag(tagName);
                Tag tagResult = Optional.ofNullable(tagMapper.selectOne(wrapper)).orElseGet(() -> {
                    tagMapper.insert(tag);
                    return tag;
                });
                tagMapper.addArticleTagRelation(tagResult.getId(), articleId);
            } catch (DuplicateKeyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void deleteTags(Long articleId) {
        tagMapper.deleteTagArticleRelations(articleId);
    }


}
