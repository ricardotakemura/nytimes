package br.org.venturus.ricardotakemura.nytimes.asynctask.params;

/**
 * Classe que representa os parametros da ArticleAsyncTask
 * @author ricardotakemura
 */
public class ArticleAsyncTaskParams {

    /**
     * Enumeração para o tipo de busca
     * @author ricardotakemura
     */
    public enum ArticleMethod {
        SEARCH,
        MOST_VIEWS
    }

    private ArticleMethod method;
    private int page;
    private String word;

    /**
     * Construtor da classe
     * @param method tipo de busca (ArticleMethod)
     */
    public ArticleAsyncTaskParams(final ArticleMethod method) {
        this.method = method;
        this.page = 0;
        this.word = null;
    }

    /**
     * Construtor da classe
     * @param method tipo de busca (ArticleMethod)
     * @param page pagina (int)
     * @param word palavra(s) (string)
     */
    public ArticleAsyncTaskParams(final ArticleMethod method, final int page, final String word) {
        this.method = method;
        this.page = page;
        this.word = word;
    }

    /**
     * method
     * @return ArticleMethod
     */
    public ArticleMethod getMethod() {
        return method;
    }

    /**
     * page
     * @return int
     */
    public int getPage() {
        return page;
    }

    /**
     * word
     * @return String
     */
    public String getWord() {
        return word;
    }
}
