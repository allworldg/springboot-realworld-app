package realworld.article.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import realworld.article.ArticleDTO;
import realworld.article.ArticlesParam;

import java.util.List;

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
}
