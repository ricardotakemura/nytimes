package br.org.venturus.ricardotakemura.nytimes.activity.state;

import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.model.Article;

public class MainActivityState {

    public enum FromTypeData {
        MOST_VIEW,
        SEARCH
    }

    public static final int MINIMUM_LETTERS = 3;
    public static final long DEFAULT_TYPING_MILISECONDS = 1000;
    public static final int LAST_VERTICAL_DIRECTION = 1;
    public static final int MAX_PAGE = 20;

    private int page;
    private boolean loading;
    private String searchText = "";
    private FromTypeData fromTypeData;

    public int getPage() {
        return page;
    }

    public boolean isFirstPage() {
        return page == 0;
    }

    public boolean isLastPage() {
        return page == MainActivityState.MAX_PAGE;
    }

    public void firstPage() {
        page = 0;
    }

    public void nextPage() {
        if (page < MainActivityState.MAX_PAGE) {
            page++;
        }
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(final boolean loading) {
        this.loading = loading;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(final String searchText) {
        this.searchText = searchText;
    }

    public FromTypeData getFromTypeData() {
        return fromTypeData;
    }

    public void setFromTypeData(final FromTypeData fromTypeData) {
        this.fromTypeData = fromTypeData;
    }
}
