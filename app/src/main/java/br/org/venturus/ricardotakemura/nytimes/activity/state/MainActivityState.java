package br.org.venturus.ricardotakemura.nytimes.activity.state;

import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.model.Article;

/**
 * Classe que representa o estado da MainActivity
 * @author ricardotakemura
 */
public class MainActivityState {

    /**
     * Enumeração do tipo de busca
     * @author ricardotakemura
     */
    public enum FromTypeData {
        MOST_VIEW,
        SEARCH
    }

    /**
     * Minimo de letras que se deve acessar para serviço de buscas
     */
    public static final int MINIMUM_LETTERS = 3;

    /**
     * Delay do teclado
     */
    public static final long DEFAULT_TYPING_MILISECONDS = 1000;

    /**
     * Valor que indica que chegou ao fim do scroll
     */
    public static final int LAST_VERTICAL_DIRECTION = 1;

    /**
     * Valor máximo de páginas
     */
    public static final int MAX_PAGE = 20;

    private int page;
    private boolean loading;
    private String searchText = "";
    private FromTypeData fromTypeData;

    /**
     * Página atual
     * @return int
     */
    public int getPage() {
        return page;
    }

    /**
     * Verifica se é primeira páginas
     * @return boolean
     */
    public boolean isFirstPage() {
        return page == 0;
    }

    /**
     * Verifica se é última página
     * @return boolean
     */
    public boolean isLastPage() {
        return page == MainActivityState.MAX_PAGE;
    }

    /**
     * Volta a primeira página
     */
    public void firstPage() {
        page = 0;
    }

    /**
     * Vai para a próxima página
     */
    public void nextPage() {
        if (page < MainActivityState.MAX_PAGE) {
            page++;
        }
    }

    /**
     * Verifica se o serviço web ainda esta carregando
     * @return boolean
     */
    public boolean isLoading() {
        return loading;
    }

    /**
     * Guarda se o serviço web ainda esta carregando
     * @param loading loading (boolean)
     */
    public void setLoading(final boolean loading) {
        this.loading = loading;
    }

    /**
     * Obtem o que foi digitado para a busca
     * @return String
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Guarda o que foi digitado para a busca
     * @param searchText texto (String)
     */
    public void setSearchText(final String searchText) {
        this.searchText = searchText;
    }

    /**
     * Obtem qual foi o tipo de busca
     * @return FromTypeData
     */
    public FromTypeData getFromTypeData() {
        return fromTypeData;
    }

    /**
     * Guarda qual foi o tipo de busca
     * @param fromTypeData FromTypeData
     */
    public void setFromTypeData(final FromTypeData fromTypeData) {
        this.fromTypeData = fromTypeData;
    }
}
