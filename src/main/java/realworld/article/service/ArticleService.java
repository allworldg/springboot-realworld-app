package realworld.article.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import realworld.article.*;
import realworld.article.repository.ArticleMapper;
import realworld.article.repository.ArticleRepository;
import realworld.tag.repository.TagRepository;
import realworld.user.LoginUser;
import realworld.user.Profile;
import realworld.user.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;

    @Autowired
    private TagRepository tagRepository;

    public ArticlesDTO getArticlesDtoList(ArticlesParam param, LoginUser loginUser) {
        Long userId = Optional.ofNullable(loginUser).map(LoginUser::getId).orElseGet(() -> null);
        List<ArticleDTO> list = repository.getArticleDtoList(param, userId);
        ArticlesDTO articlesDTO = new ArticlesDTO(list, list.size());
        return articlesDTO;
    }

    //    public ArticleDTO getArticle(ArticleParam param, LoginUser user) {
//        Long userId = Optional.ofNullable(user).map(LoginUser::getId).orElseGet(() -> null);
//        return repository.getArticleDto(param, userId);
//    }
//
    public ArticleDTO createArticle(ArticleParam articleParam, LoginUser user) {
        Long userId = user.getId();
        Article article = repository.createArticle(articleParam, userId);
        ArticleDTO articleDTO = new ArticleDTO(article);
        articleDTO.setTagList(articleParam.getTagList());
        articleDTO.setFavorited(false);
        articleDTO.setFavoritesCount(0);
        Profile profile = new Profile();
        profile.setBio(user.getBio());
        profile.setFollowing(false);
        profile.setImage(user.getImage());
        profile.setUsername(user.getUsername());
        articleDTO.setAuthor(profile);
        tagRepository.createTag(articleParam.getTagList(),article.getId());
        return articleDTO;
    }
}
