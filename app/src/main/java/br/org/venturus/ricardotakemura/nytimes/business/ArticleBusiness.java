package br.org.venturus.ricardotakemura.nytimes.business;

import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.exception.BusinessException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;

/**
 * Interface de negócio (Artigos)
 */
public interface ArticleBusiness {

    /**
     * Obtem a lista de artigos mais vistos
     * @return Lista de Article
     * @throws BusinessException Exceção de negócio
     */
    List<Article> getMostViews() throws BusinessException;

    /**
     * Obtem a lista de artigos de uma busca
     * @param search palavra ou palavras a serem buscadas (String)
     * @param page pagina (de 0 até 20) (int)
     * @return Lista de Article
     * @throws BusinessException Exceção de negócio
     */
    List<Article> search(final String search, final int page) throws BusinessException;
}
