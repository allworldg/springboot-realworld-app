package realworld.article.repository;

import realworld.article.Article;
import realworld.article.ArticleDTO;
import realworld.article.ArticlesParam;

import java.util.List;

public interface ArticleRepository {
    List<ArticleDTO> getArticleDtoList(ArticlesParam param, Long userId);

    List<Long> getArticleIds(ArticlesParam param);
}
