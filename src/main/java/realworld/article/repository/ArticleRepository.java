package realworld.article.repository;

import realworld.article.*;

import java.util.Optional;

public interface ArticleRepository {

    ArticlesDTO getArticleDtoList(ArticlesParam param, Long userId);

    Optional<Article> getArticleByTitle(String title);

    Article createArticle(ArticleParam articleParam, Long userId);

    Optional<ArticleDTO> getArticleDtoBySlug(String slug, Long userId);

    Optional<Article> getArticleBySlug(String slug);

    ArticlesDTO getFeedArticle(ArticlesParam articlesParam, Long id);

    void addFavorite(Long articleId, Long userId);

    boolean isAlreadyFavorited(Long articleId, Long userId);

    void deleteFavorite(Long articleId, Long userId);
}
