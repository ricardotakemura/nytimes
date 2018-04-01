package br.org.venturus.ricardotakemura.nytimes.business.impl;

import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.org.venturus.ricardotakemura.nytimes.R;
import br.org.venturus.ricardotakemura.nytimes.business.ArticleBusiness;
import br.org.venturus.ricardotakemura.nytimes.exception.BusinessException;
import br.org.venturus.ricardotakemura.nytimes.exception.JsonException;
import br.org.venturus.ricardotakemura.nytimes.exception.ServerException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;
import br.org.venturus.ricardotakemura.nytimes.parse.ArticleMostViewJsonParser;
import br.org.venturus.ricardotakemura.nytimes.parse.ArticleSearchJsonParser;
import br.org.venturus.ricardotakemura.nytimes.provider.JsonDataProvider;

/**
 * Implementação da interface ArticleBusiness (json)
 */
public class ArticleJsonBusiness implements ArticleBusiness {

    private final String MOST_POPULAR_URL = "/mostpopular/v2/mostviewed/{0}/{1}.json";
    private final String SECTION_VALUE = "all-sections";
    private final Integer DAYS_VALUE = 30;
    private final String SEARCH_URL = "/search/v2/articlesearch.json";
    private final String PAGE_PARAM = "page";
    private final String QUERY_PARAM = "q";
    private final String LUCENE_PARAM = "fq";
    private final String SORT_PARAM = "sort";
    private final String FIELDS_PARAM = "fl";
    private final String NEWEST_VALUE = "newest";
    private final String ARTICLE_VALUE = "document_type:article";
    private final String FIELDS_VALUE = "abstract,headline,pub_date,multimedia,document_type,snippet";

    /**
     * @see ArticleBusiness#getMostViews()
     */
    @Override
    public List<Article> getMostViews() throws BusinessException {
        final String path = MessageFormat.format(MOST_POPULAR_URL, SECTION_VALUE, DAYS_VALUE);
        try {
            return JsonDataProvider.getList(new ArticleMostViewJsonParser(), path);
        } catch (MalformedURLException e) {
            throw new BusinessException(R.string.generic_error,e);
        } catch (JsonException e) {
            throw new BusinessException(R.string.invalid_data_error, e);
        } catch (ServerException e) {
            throw new BusinessException(R.string.connection_error, e);
        }
    }

    /**
     * @see ArticleBusiness#search(String, int)
     */
    @Override
    public List<Article> search(final String search, final int page) throws BusinessException {
        try {
            final Map<String, String> params = new HashMap<>();
            params.put(PAGE_PARAM, String.valueOf(page));
            params.put(QUERY_PARAM, search);
            params.put(LUCENE_PARAM, ARTICLE_VALUE);
            params.put(SORT_PARAM, NEWEST_VALUE);
            params.put(FIELDS_PARAM, FIELDS_VALUE);
            return JsonDataProvider.getList(new ArticleSearchJsonParser(), SEARCH_URL, params);
        } catch (MalformedURLException e) {
            throw new BusinessException(R.string.generic_error,e);
        } catch (JsonException e) {
            throw new BusinessException(R.string.invalid_data_error, e);
        } catch (ServerException e) {
            throw new BusinessException(R.string.connection_error, e);
        }
    }
}
