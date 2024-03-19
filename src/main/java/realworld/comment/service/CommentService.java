package realworld.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realworld.comment.CommentDto;
import realworld.comment.repository.CommentRepository;
import realworld.user.LoginUser;

import java.util.List;
import java.util.Optional;

/**
 * @author allworldg
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2024-03-17 23:38:47
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDto> getCommentDtos(String slug, LoginUser user) {
        Long userId = Optional.ofNullable(user).map(u -> u.getId()).orElseGet(() -> null);

        return commentRepository.getCommentDtosBySlug(slug, userId);
    }
}




