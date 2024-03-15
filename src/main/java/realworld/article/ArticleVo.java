package realworld.article;

import io.lettuce.core.GetExArgs;

public class ArticleVo {
    private ArticleDTO article;

    public ArticleVo(){}
    public ArticleVo(ArticleDTO article) {
        this.article = article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    public ArticleDTO getArticle() {
        return article;
    }
}
