package realworld.comment.repository;

import realworld.comment.Comment;
import realworld.comment.CommentDto;

import java.util.List;

public interface CommentRepository {
    List<CommentDto> getCommentDtosBySlug(String slug, Long userId);

    void addComment(Comment comment);
}
