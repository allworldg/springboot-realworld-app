package realworld.comment.repository;

import realworld.comment.CommentDto;

import java.util.List;

public interface CommentRepository {
    List<CommentDto> getCommentDtosBySlug(String slug, Long userId);
}
