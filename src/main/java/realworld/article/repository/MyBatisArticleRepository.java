package realworld.article.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import realworld.article.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MyBatisArticleRepository implements ArticleRepository {
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public ArticlesDTO getArticleDtoList(ArticlesParam param, Long userId) {
        Page<ArticleDTO> page =
                new Page<>(param.getOffset() / param.getLimit() + 1, param.getLimit());
        List<Long> articleIds = articleMapper.getArticleIds(page, param);
        ArticlesDTO articlesDTO = new ArticlesDTO();
        articlesDTO.setArticlesCount(page.getTotal());
        if (articleIds.isEmpty()) {
            articlesDTO.setArticles(new ArrayList<>());
        } else {
            articlesDTO.setArticles(articleMapper.getArticleDtoList(articleIds, userId));
        }
        return articlesDTO;
    }

    @Override
    public Optional<Article> getArticleByTitle(String title) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        Article article = articleMapper.selectOne(wrapper);
        return Optional.ofNullable(article);
    }

    @Override
    @Transactional
    public Article createArticle(ArticleParam articleParam, Long userId) {
        Article article = new Article();
        article.setUserId(userId);
        article.setTitle(articleParam.getTitle());
        article.setBody(articleParam.getBody());
        article.setDescription(articleParam.getDescription());
        final Slugify slugify = Slugify.builder().build();
        String slug = slugify.slugify(article.getTitle());
        article.setSlug(slug);
        Date date = new Date();
        article.setCreatedAt(date);
        article.setUpdatedAt(date);
        articleMapper.insert(article);
        return article;
    }

    @Override
    public Optional<ArticleDTO> getArticleDtoBySlug(String slug, Long userId) {
        return Optional.ofNullable(articleMapper.getArticleBySlug(slug, userId));
    }

    @Override
    public Optional<Article> getArticleBySlug(String slug) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("slug", slug);
        Article article = articleMapper.selectOne(wrapper);
        return Optional.ofNullable(article);
    }

    @Override
    public ArticlesDTO getFeedArticle(ArticlesParam param, Long id) {
        Page<ArticleDTO> page =
                new Page<>(param.getOffset() / param.getLimit() + 1, param.getLimit());
        List<Long> feedArticleIds = articleMapper.getFeedArticleIds(page, id);
        if (feedArticleIds.isEmpty()) {
            return new ArticlesDTO(new ArrayList<>(), page.getTotal());
        } else {
            return new ArticlesDTO(articleMapper.getArticleDtoList(feedArticleIds, id),
                    page.getTotal());
        }
    }

    @Override
    public void addFavorite(Long articleId, Long userId) {
        articleMapper.addFavorite(articleId, userId);
    }

    @Override
    public boolean isAlreadyFavorited(Long articleId, Long userId) {
        return articleMapper.isAlreadyFavorited(articleId,userId);
    }
}
