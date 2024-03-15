package realworld.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import realworld.article.annotation.UniqueTitle;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName("article")
public class ArticleParam {

    public ArticleParam() {
    }

    public ArticleParam(String body, String description, List<String> tagList, String title) {
        this.body = body;
        this.description = description;
        this.tagList = tagList;
        this.title = title;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ArticleParam{" +
                "body='" + body + '\'' +
                ", description='" + description + '\'' +
                ", tagList=" + tagList +
                ", title='" + title + '\'' +
                '}';
    }


    @NotBlank
    private String body;
    @NotBlank
    private String description;
    private List<String> tagList;
    @NotBlank
    @UniqueTitle
    private String title;
}
