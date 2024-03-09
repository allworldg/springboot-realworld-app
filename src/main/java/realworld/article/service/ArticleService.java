package realworld.article.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import realworld.article.Article;
import realworld.article.ArticleDTO;
import realworld.article.ArticlesDTO;
import realworld.article.ArticlesParam;
import realworld.article.repository.ArticleMapper;
import realworld.article.repository.ArticleRepository;
import realworld.user.LoginUser;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    public ArticlesDTO getArticlesDtoList(ArticlesParam param, LoginUser loginUser) {
        Long userId = Optional.ofNullable(loginUser).map(LoginUser::getId).orElseGet(() -> null);
        List<ArticleDTO> list = repository.getArticleDtoList(param, userId);
        ArticlesDTO articlesDTO = new ArticlesDTO(list, list.size());
        return articlesDTO;
    }
}
