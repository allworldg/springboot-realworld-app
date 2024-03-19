package realworld.comment;

import java.util.List;

public class CommentsVo {
    private List<CommentDto> comments;

    public CommentsVo(){}
    public CommentsVo(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
