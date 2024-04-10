package realworld.article;

import java.util.List;


public class ArticlesDTO {

    public ArticlesDTO(List<ArticleDTO> articles, long articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }

    public ArticlesDTO() {
    }

    private List<ArticleDTO> articles;
    private long articlesCount;

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    public long getArticlesCount() {
        return this.articlesCount;
    }

    public void setArticlesCount(long articlesCount) {
        this.articlesCount = articlesCount;
    }
}
