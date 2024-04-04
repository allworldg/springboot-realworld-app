package realworld.comment;

public class CommentVo {
    private CommentDto comment;

    public CommentVo() {
    }

    public CommentVo(CommentDto comment) {
        this.comment = comment;
    }

    public CommentDto getComment() {
        return comment;
    }

    public void setComment(CommentDto comment) {
        this.comment = comment;
    }
}
