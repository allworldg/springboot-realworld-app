package realworld.article.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import realworld.article.Article;
import realworld.article.ArticleDTO;
import realworld.article.ArticlesParam;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    List<ArticleDTO> getArticleDtoList(@Param("ids") List<Long> ids,
                                       @Param("loginId") Long loginId);

    List<Long> getArticleIds(IPage<ArticleDTO> page, @Param("param") ArticlesParam param);

    ArticleDTO getArticleBySlug(@Param("slug") String slug, @Param("loginId") Long loginId);

    List<Long> getFeedArticleIds(@Param("page") Page<ArticleDTO> page, @Param("id") Long id);

    void addFavorite(@Param("articleId") Long articleId, @Param("userId") Long userId);

    boolean isAlreadyFavorited(@Param("articleId") Long articleId, @Param("userId") Long userId);
}
