package realworld.article.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import realworld.article.ArticlesDTO;
import realworld.article.ArticlesParam;
import realworld.article.service.ArticleService;
import realworld.user.LoginUser;

import java.util.List;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public ArticlesDTO getArticles(
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "favorited", required = false) String favorited,
            @RequestParam(name = "limit", defaultValue = "20") int limit,
            @RequestParam(name = "offset", defaultValue = "1") int offset,
            @AuthenticationPrincipal LoginUser user
    ) {
        ArticlesParam articlesParam = new ArticlesParam(tag, author, favorited, limit, offset);
        ArticlesDTO articlesDTO = articleService.getArticlesDtoList(articlesParam, user);
        return articlesDTO;
    }

}
