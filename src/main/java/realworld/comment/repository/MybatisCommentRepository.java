package realworld.comment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import realworld.comment.CommentDto;

import java.util.List;

@Repository
public class MybatisCommentRepository implements CommentRepository{
    @Autowired
    private CommentMapper mapper;
    @Override
    public List<CommentDto> getCommentDtosBySlug(String slug, Long userId) {
        return mapper.getCommentDtosBySlug(slug,userId);
    }
}
