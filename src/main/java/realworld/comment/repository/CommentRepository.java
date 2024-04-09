package realworld.comment.repository;

import realworld.comment.Comment;
import realworld.comment.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<CommentDto> getCommentDtosBySlug(String slug, Long userId);

    void addComment(Comment comment);

    Optional<Comment> getCommentById(Long commentId);

    void deleteComment(Long commentId);
}
