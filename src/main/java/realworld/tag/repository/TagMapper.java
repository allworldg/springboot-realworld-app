package realworld.tag.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import realworld.tag.Tag;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
