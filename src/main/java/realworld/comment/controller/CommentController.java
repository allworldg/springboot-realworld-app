package realworld.comment.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import realworld.comment.CommentVo;
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

    @PostMapping("{slug}/comments")
    public CommentVo createComment(@PathVariable("slug") String slug,
                                   @AuthenticationPrincipal LoginUser user,
                                   @Valid @RequestBody CommentParam param) {
        return new CommentVo(commentService.addComment(slug, user, param.getBody()));
    }

    @DeleteMapping("{slug}/comments/{id}")
    public void deleteComment(@PathVariable("slug") String slug, @PathVariable("id") Long id,
                              @AuthenticationPrincipal LoginUser user) {
        commentService.deleteComment(slug, id, user);
    }


}

@JsonTypeName("comment")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
class CommentParam {
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public CommentParam(String body) {
        this.body = body;
    }

    public CommentParam() {
    }

    @NotBlank(message = "can not be empty")
    private String body;
}
