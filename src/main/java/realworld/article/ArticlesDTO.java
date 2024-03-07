package realworld.article;

import java.util.List;

public class ArticlesDTO {


    public ArticlesDTO(List<ArticleDTO> articles, int articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }
    public ArticlesDTO(){}



    private List<ArticleDTO> articles;
    private int articlesCount;
}
