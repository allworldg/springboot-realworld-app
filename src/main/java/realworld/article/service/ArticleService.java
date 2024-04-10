package realworld.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realworld.article.*;
import realworld.article.repository.ArticleRepository;
import realworld.exception.ResourceNotFoundException;
import realworld.tag.repository.TagRepository;
import realworld.user.LoginUser;
import realworld.user.Profile;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    public ArticlesDTO getArticlesDtoList(ArticlesParam param, LoginUser loginUser) {
        Long userId = Optional.ofNullable(loginUser).map(LoginUser::getId).orElseGet(() -> null);
        return articleRepository.getArticleDtoList(param, userId);
    }

    public ArticleDTO createArticle(ArticleParam articleParam, LoginUser user) {
        Long userId = user.getId();
        Article article = articleRepository.createArticle(articleParam, userId);
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
        tagRepository.createTag(articleParam.getTagList(), article.getId());
        return articleDTO;
    }

    public ArticleDTO getArticleDtoBySlug(String slug, LoginUser user) {
        Long userId =
                Optional.ofNullable(user).map(LoginUser::getId).orElseGet(() -> null);
        ArticleDTO articleDTO = articleRepository.getArticleDtoBySlug(slug, userId).orElseThrow(
                ResourceNotFoundException::new);
        return articleDTO;

    }

    public ArticlesDTO getFeedArticles(ArticlesParam articlesParam, LoginUser loginUser) {
        return articleRepository.getFeedArticle(articlesParam, loginUser.getId());
    }
}
