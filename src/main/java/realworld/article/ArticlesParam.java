package realworld.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import realworld.common.HttpCommon;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticlesParam {

    public ArticlesParam() {
    }

    public ArticlesParam(String tag, String author, String favorited, int offset, int limit) {
        this.tag = tag;
        this.author = author;
        this.favorited = favorited;
        this.offset = offset;
        this.limit = limit;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFavorited() {
        return favorited;
    }

    public void setFavorited(String favorited) {
        this.favorited = favorited;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private String tag;
    private String author;
    private String favorited;

    @Min(value = 0, message = HttpCommon.LIMIT_MIN_ZERO)
    private int offset;
    @Min(value = 0, message = HttpCommon.LIMIT_MIN_ZERO)
    private int limit;

}
