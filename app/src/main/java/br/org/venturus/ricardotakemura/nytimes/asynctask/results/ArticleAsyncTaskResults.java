package br.org.venturus.ricardotakemura.nytimes.asynctask.results;

import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.BusinessException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;

/**
 * Classe que representa o resultado da ArticleAsyncTask
 * @author ricardotakemura
 */
public class ArticleAsyncTaskResults {

    private List<Article> articles;
    private BusinessException error;

    /**
     * Construtor da classe
     * @param articles Lista de Article (List)
     * @param error BusinessException
     */
    public ArticleAsyncTaskResults(List<Article> articles, BusinessException error) {
        this.articles = articles;
        this.error = error;
    }

    /**
     * articles
     * @return List
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * error
     * @return BusinessException
     */
    public BusinessException getError() {
        return error;
    }

}
