package realworld.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import realworld.article.Article;
import realworld.article.ArticleDTO;
import realworld.article.repository.ArticleRepository;
import realworld.comment.Comment;
import realworld.comment.CommentDto;
import realworld.comment.repository.CommentRepository;
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

    public CommentDto addComment(String slug, LoginUser user, String commentStr) {
        Comment comment = new Comment();
        comment.setBody(commentStr);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        comment.setUserId(user.getId());
        ArticleDTO articleDTO = articleRepository.getArticleBySlug(slug, user.getId());
        Long articleId = Optional.ofNullable(articleDTO).map(ArticleDTO::getId)
                                 .orElseThrow(() -> new ResourceNotFoundException());
        comment.setArticleId(articleId);
        commentRepository.addComment(comment);
        CommentDto commentDto = new CommentDto(comment, articleDTO.getAuthor());
        return commentDto;
    }
}




