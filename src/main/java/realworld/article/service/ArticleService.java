package realworld.article.service;

import com.github.slugify.Slugify;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realworld.article.*;
import realworld.article.repository.ArticleRepository;
import realworld.exception.NoAuthenticationException;
import realworld.exception.ResourceNotFoundException;
import realworld.exception.TitleAlreadyExistException;
import realworld.tag.repository.TagRepository;
import realworld.user.LoginUser;
import realworld.user.Profile;

import java.util.Date;
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

    public ArticleDTO addFavorite(String slug, LoginUser user) {
        Long userId = user.getId();
        ArticleDTO articleDto = articleRepository.getArticleDtoBySlug(slug, userId)
                                                 .orElseThrow(ResourceNotFoundException::new);
        Long articleId = articleDto.getId();
        boolean favorited = articleRepository.isAlreadyFavorited(articleId, userId);
        if (!favorited) {
            articleRepository.addFavorite(articleDto.getId(), user.getId());
        }
        return articleRepository.getArticleDtoBySlug(slug, userId)
                                .orElseThrow(ResourceNotFoundException::new);
    }

    public ArticleDTO deleteFavorite(String slug, LoginUser user) {
        Long userId = user.getId();
        ArticleDTO article = articleRepository.getArticleDtoBySlug(slug, userId)
                                              .orElseThrow(ResourceNotFoundException::new);
        Long articleId = article.getId();
        articleRepository.deleteFavorite(articleId, user.getId());
        return articleRepository.getArticleDtoBySlug(slug, userId)
                                .orElseThrow(ResourceNotFoundException::new);
    }

    public String updateArticle(String slug, ArticleParam param, LoginUser user) {
        Long userId = user.getId();
        ArticleDTO articleDTO = articleRepository.getArticleDtoBySlug(slug, userId)
                                                 .orElseThrow(ResourceNotFoundException::new);
        if (!articleDTO.getAuthor().getId().equals(userId)) {
            throw new NoAuthenticationException();
        }
        Slugify slugify = Slugify.builder().build();
        String newSlug = slugify.slugify(param.getTitle());
        Article article =
                new Article(articleDTO.getId(), param.getTitle(), newSlug,
                        param.getDescription(), param.getBody(), articleDTO.getCreatedAt(),
                        new Date(), userId);
        articleRepository.updateArticle(article);
        tagRepository.deleteTags(article.getId());
        tagRepository.createTag(param.getTagList(), article.getId());
        return newSlug;
    }

    public void checkTitleExist(String oldTitle, String title) {
        if (StringUtils.isEmpty(oldTitle) || StringUtils.equals(oldTitle, title)) {
            return;
        }
        articleRepository.getArticleByTitle(title).ifPresent(e -> {
            throw new TitleAlreadyExistException();
        });
    }
}
