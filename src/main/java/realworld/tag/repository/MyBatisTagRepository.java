package realworld.tag.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import realworld.tag.Tag;

import java.util.Arrays;
import java.util.List;
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
}
