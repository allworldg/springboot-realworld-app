package realworld.article;

import java.util.List;

public class ArticlesDTO {


    public ArticlesDTO(List<ArticleDTO> articles, int articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }

    public ArticlesDTO() {
    }

    private List<ArticleDTO> articles;
    private int articlesCount;

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }
}
