package realworld.article.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
}
