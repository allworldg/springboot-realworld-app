package realworld.article.repository;

import realworld.article.Article;
import realworld.article.ArticleDTO;
import realworld.article.ArticleParam;
import realworld.article.ArticlesParam;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    List<ArticleDTO> getArticleDtoList(ArticlesParam param, Long userId);

    List<Long> getArticleIds(ArticlesParam param);


    Optional<Article> getArticleByTitle(String title);

    Article createArticle(ArticleParam articleParam, Long userId);
//    ArticleDTO getArticleDto(ArticlesParam param, Long userId);
}
