package realworld.article.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import realworld.article.Article;
import realworld.article.ArticleDTO;
import realworld.article.ArticleParam;
import realworld.article.ArticlesParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MyBatisArticleRepository implements ArticleRepository {
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Long> getArticleIds(ArticlesParam param) {
        Page<ArticleDTO> page = new Page<>(param.getOffset(), param.getLimit());
        return articleMapper.getArticleIds(page, param);
    }

    @Override
    public List<ArticleDTO> getArticleDtoList(ArticlesParam param, Long userId) {
        List<Long> articleIds = getArticleIds(param);
        List<ArticleDTO> articleDTOList = articleMapper.getArticleDtoList(articleIds, userId);
        return articleDTOList;
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

    //    @Override
//    public ArticleDTO getArticleDto() {
//        return null;
//    }
}
