package realworld.comment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import realworld.comment.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import realworld.comment.CommentDto;

import java.util.List;

/**
 * @author allworldg
 * @description 针对表【comment】的数据库操作Mapper
 * @createDate 2024-03-17 23:38:47
 * @Entity realworld.comment.Comment
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDto> getCommentDtosBySlug(@Param("slug") String slug,
                                          @Param("loginId") Long userId);
}




