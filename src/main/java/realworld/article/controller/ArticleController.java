package realworld.article.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import realworld.article.*;
import realworld.article.service.ArticleService;
import realworld.user.LoginUser;


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

    @PostMapping("/articles")
    public ArticleVo createArticle(@RequestBody @Valid ArticleParam articleParam,
                                   @AuthenticationPrincipal LoginUser user) {
        ArticleDTO articleDTO = articleService.createArticle(articleParam, user);
        return new ArticleVo(articleDTO);
    }


}
