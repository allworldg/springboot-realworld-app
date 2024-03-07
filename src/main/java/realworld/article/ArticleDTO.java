package realworld.article;

import org.springframework.data.annotation.Transient;
import realworld.user.Profile;

import java.util.Date;
import java.util.List;

public class ArticleDTO {


    public ArticleDTO() {
    }

    public ArticleDTO(String slug, String title, String description, String body,
                      List<String> tagList,
                      Date createdAt, Profile author) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.author = author;
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
                '}';
    }

    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private Date createdAt;
    private Profile author;


}
