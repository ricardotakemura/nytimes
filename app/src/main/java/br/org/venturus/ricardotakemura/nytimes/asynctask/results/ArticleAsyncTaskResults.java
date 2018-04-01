package br.org.venturus.ricardotakemura.nytimes.asynctask.results;

import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.BusinessException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;

public class ArticleAsyncTaskResults {

    private List<Article> articles;
    private BusinessException error;

    public ArticleAsyncTaskResults(List<Article> articles, BusinessException error) {
        this.articles = articles;
        this.error = error;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public BusinessException getError() {
        return error;
    }

}
