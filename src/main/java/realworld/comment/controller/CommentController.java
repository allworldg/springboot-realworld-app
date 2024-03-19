package realworld.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import realworld.comment.CommentsVo;
import realworld.comment.service.CommentService;
import realworld.user.LoginUser;

@RestController
@RequestMapping("/articles")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("{slug}/comments")
    public CommentsVo getArticleComments(@PathVariable("slug") String slug,
                                         @AuthenticationPrincipal LoginUser user) {
        return new CommentsVo(commentService.getCommentDtos(slug, user));
    }
}
