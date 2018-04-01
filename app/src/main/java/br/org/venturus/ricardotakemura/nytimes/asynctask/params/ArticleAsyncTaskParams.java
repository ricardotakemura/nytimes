package br.org.venturus.ricardotakemura.nytimes.asynctask.params;

public class ArticleAsyncTaskParams {

    public enum ArticleMethod {
        SEARCH,
        MOST_VIEWS
    }

    private ArticleMethod method;
    private int page;
    private String word;

    public ArticleAsyncTaskParams(final ArticleMethod method) {
        this.method = method;
        this.page = 0;
        this.word = null;
    }

    public ArticleAsyncTaskParams(final ArticleMethod method, final int page, final String word) {
        this.method = method;
        this.page = page;
        this.word = word;
    }

    public ArticleMethod getMethod() {
        return method;
    }

    public int getPage() {
        return page;
    }

    public String getWord() {
        return word;
    }
}
