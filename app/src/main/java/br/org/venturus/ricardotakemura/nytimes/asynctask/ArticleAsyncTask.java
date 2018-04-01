package br.org.venturus.ricardotakemura.nytimes.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.R;
import br.org.venturus.ricardotakemura.nytimes.asynctask.params.ArticleAsyncTaskParams;
import br.org.venturus.ricardotakemura.nytimes.asynctask.results.ArticleAsyncTaskResults;
import br.org.venturus.ricardotakemura.nytimes.business.ArticleBusiness;
import br.org.venturus.ricardotakemura.nytimes.business.impl.ArticleJsonBusiness;
import br.org.venturus.ricardotakemura.nytimes.exception.BusinessException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;

/**
 * AsyncTask para serviços de artigos
 * @author ricardotakemura
 */
public class ArticleAsyncTask extends AsyncTask<ArticleAsyncTaskParams, Void, ArticleAsyncTaskResults> {

    /**
     * Interface para delegar a finalização da busca e manipulação dos dados
     */
    public interface ArticleAsyncTaskDelegate {
        /**
         * Quando a busca terminar, este método é executado
         * @param articles Lista de Articles (List)
         * @param e BusinessException
         */
        void finished(List<Article> articles, BusinessException e);
    }

    private ArticleAsyncTaskDelegate delegate;
    private ArticleBusiness business;

    /**
     * Construtor da classe
     * @param delegate ArticleAsyncTaskDelegate
     */
    public ArticleAsyncTask(final ArticleAsyncTaskDelegate delegate) {
        super();
        this.delegate = delegate;
        business = new ArticleJsonBusiness();
    }

    /**
     * @see AsyncTask#onPostExecute(Object)
     */
    @Override
    protected void onPostExecute(ArticleAsyncTaskResults results) {
        if (delegate != null) {
            delegate.finished(results.getArticles(), results.getError());
        }
    }

    /**
     * @see AsyncTask#doInBackground(Object[])
     */
    @Override
    protected ArticleAsyncTaskResults doInBackground(ArticleAsyncTaskParams... parameters) {
        try {
            if (parameters != null && parameters.length == 1) {
                ArticleAsyncTaskParams params = parameters[0];
                if (ArticleAsyncTaskParams.ArticleMethod.MOST_VIEWS.equals(params.getMethod())) {
                    return new ArticleAsyncTaskResults(business.getMostViews(), null);
                } else if (ArticleAsyncTaskParams.ArticleMethod.SEARCH.equals(params.getMethod())) {
                    return new ArticleAsyncTaskResults(business.search(params.getWord(), params.getPage()), null);
                }
            }
        } catch (BusinessException e) {
            return new ArticleAsyncTaskResults(null, e);
        }
        return new ArticleAsyncTaskResults(null, new BusinessException(R.string.generic_error));
    }
}
