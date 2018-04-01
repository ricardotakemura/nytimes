package br.org.venturus.ricardotakemura.nytimes.business;

import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.BusinessException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;

public interface ArticleBusiness {

    List<Article> getMostViews() throws BusinessException;

    List<Article> search(final String search, final int page) throws BusinessException;
}
