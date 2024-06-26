package realworld.tag.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import realworld.tag.Tag;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    void addArticleTagRelation(@Param("tagId") Long id, @Param("articleId") Long articleId);

    void deleteTagArticleRelations(@Param("articleId") Long articleId);
}
