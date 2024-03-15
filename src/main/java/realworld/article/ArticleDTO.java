package realworld.article;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.annotation.Transient;
import realworld.user.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ArticleDTO {

    public ArticleDTO() {
    }

    public ArticleDTO(String slug, String title, String description, String body,
                      List<String> tagList,
                      Date createdAt, Profile author, boolean favorited, int favoritesCount) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.author = author;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
    }

    public ArticleDTO(Article article) {
        this.slug = article.getSlug();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.body = article.getBody();
        this.createdAt = article.getCreatedAt();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", tagList=" + tagList +
                ", createdAt=" + createdAt +
                ", author=" + author +
                ", favorited=" + favorited +
                ", favoriteCount" + favoritesCount +
                '}';
    }

    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private Date createdAt;
    private Profile author;
    private boolean favorited;

    private int favoritesCount;


}
