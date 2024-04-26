package realworld.article.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import realworld.article.*;
import realworld.article.service.ArticleService;
import realworld.user.LoginUser;


@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping()
    public ArticlesDTO getArticles(
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "favorited", required = false) String favorited,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @AuthenticationPrincipal LoginUser user
    ) {
        ArticlesParam articlesParam = new ArticlesParam(tag, author, favorited, offset, limit);
        ArticlesDTO articlesDTO = articleService.getArticlesDtoList(articlesParam, user);
        return articlesDTO;
    }

    @PostMapping()
    public ArticleVo createArticle(@RequestBody @Valid ArticleParam articleParam,
                                   @AuthenticationPrincipal LoginUser user) {
        articleService.checkTitleExist(null, articleParam.getTitle());
        ArticleDTO articleDTO = articleService.createArticle(articleParam, user);
        return new ArticleVo(articleDTO);
    }

    @GetMapping("/feed")
    public ArticlesDTO getFeedArticles(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "1") int offset,
            @AuthenticationPrincipal LoginUser loginUser) {
        ArticlesParam articlesParam = new ArticlesParam(null, null, null, offset, limit);
        return articleService.getFeedArticles(articlesParam, loginUser);

    }

    @GetMapping("/{slug}")
    public ArticleVo getArticle(@PathVariable String slug,
                                @AuthenticationPrincipal LoginUser user) {
        ArticleDTO articleDTO = articleService.getArticleDtoBySlug(slug, user);
        return new ArticleVo(articleDTO);
    }

    @PostMapping("/{slug}/favorite")
    public ArticleVo addfavorite(@PathVariable String slug,
                                 @AuthenticationPrincipal LoginUser user) {
        return new ArticleVo(articleService.addFavorite(slug, user));
    }

    @PutMapping("/{slug}")
    public ArticleVo updateArticle(@PathVariable String slug,
                                   @RequestBody @Valid ArticleParam param,
                                   @AuthenticationPrincipal LoginUser user) {
        ArticleDTO articleDto = articleService.getArticleDtoBySlug(slug, null);
        articleService.checkTitleExist(articleDto.getTitle(),param.getTitle());
        String newSlug = articleService.updateArticle(slug, param, user);
        return new ArticleVo(articleService.getArticleDtoBySlug(newSlug, user));
    }

    @DeleteMapping("/{slug}/favorite")
    public ArticleVo deleteFavorite(@PathVariable String slug,
                                    @AuthenticationPrincipal LoginUser user) {
        return new ArticleVo(articleService.deleteFavorite(slug, user));
    }

}
