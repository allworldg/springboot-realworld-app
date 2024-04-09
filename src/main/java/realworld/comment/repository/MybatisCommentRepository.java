package realworld.comment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import realworld.comment.Comment;
import realworld.comment.CommentDto;

import java.util.List;
import java.util.Optional;

@Repository
public class MybatisCommentRepository implements CommentRepository {
    @Autowired
    private CommentMapper mapper;

    @Override
    public List<CommentDto> getCommentDtosBySlug(String slug, Long userId) {
        return mapper.getCommentDtosBySlug(slug, userId);
    }

    @Override
    public void addComment(Comment comment) {
        mapper.insert(comment);
    }

    @Override
    public Optional<Comment> getCommentById(Long commentId) {
        Comment comment = mapper.selectById(commentId);
        return Optional.ofNullable(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        mapper.deleteById(commentId);
    }
}
