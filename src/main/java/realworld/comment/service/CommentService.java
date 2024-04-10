package realworld.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import realworld.article.ArticleDTO;
import realworld.article.repository.ArticleRepository;
import realworld.comment.Comment;
import realworld.comment.CommentDto;
import realworld.comment.repository.CommentRepository;
import realworld.exception.NoAuthenticationException;
import realworld.exception.ResourceNotFoundException;
import realworld.user.LoginUser;

import java.util.Date;
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

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> getCommentDtos(String slug, LoginUser user) {
        Long userId = Optional.ofNullable(user).map(LoginUser::getId).orElseGet(() -> null);
        return commentRepository.getCommentDtosBySlug(slug, userId);
    }

    @Transactional
    public CommentDto addComment(String slug, LoginUser user, String commentStr) {
        Comment comment = new Comment();
        comment.setBody(commentStr);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        comment.setUserId(user.getId());
        ArticleDTO articleDTO = articleRepository.getArticleDtoBySlug(slug, user.getId())
                                                 .orElseThrow(
                                                         ResourceNotFoundException::new);
        Long articleId = articleDTO.getId();
        comment.setArticleId(articleId);
        commentRepository.addComment(comment);
        return new CommentDto(comment, articleDTO.getAuthor());
    }

    @Transactional
    public void deleteComment(String slug, Long commentId, LoginUser user) {
        ArticleDTO articleDTO = articleRepository.getArticleDtoBySlug(slug, user.getId())
                                                 .orElseThrow(
                                                         ResourceNotFoundException::new);
        Comment comment = commentRepository.getCommentById(commentId)
                                           .orElseThrow(ResourceNotFoundException::new);
        if (!user.getId().equals(comment.getUserId()) &&
                !user.getId().equals(articleDTO.getAuthor().getId())) {
            throw new NoAuthenticationException();
        } else {
            commentRepository.deleteComment(commentId);
        }


    }
}




