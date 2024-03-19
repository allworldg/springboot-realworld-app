package realworld.comment;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import realworld.user.Profile;

import java.util.Date;

public class CommentDto {

    private Long id;

    /**
     *
     */
    private Profile author;
    /**
     *
     */
    private String body;

    /**
     *
     */
    private Date createdAt;

    /**
     *
     */
    private Date updatedAt;


    @JsonIgnore
    private static final long serialVersionUID = 1L;

    public CommentDto() {
    }


    public CommentDto(Long id, Profile author, String body, Date createdAt, Date updatedAt) {
        this.id = id;
        this.author = author;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
