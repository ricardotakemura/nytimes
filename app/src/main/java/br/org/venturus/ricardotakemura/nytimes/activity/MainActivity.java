package br.org.venturus.ricardotakemura.nytimes.activity;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.R;
import br.org.venturus.ricardotakemura.nytimes.activity.state.MainActivityState;
import br.org.venturus.ricardotakemura.nytimes.adapter.ArticleRecyclerViewAdapter;
import br.org.venturus.ricardotakemura.nytimes.asynctask.ArticleAsyncTask;
import br.org.venturus.ricardotakemura.nytimes.asynctask.params.ArticleAsyncTaskParams;
import br.org.venturus.ricardotakemura.nytimes.exception.BusinessException;
import br.org.venturus.ricardotakemura.nytimes.model.Article;
import br.org.venturus.ricardotakemura.nytimes.util.Scheduler;
import br.org.venturus.ricardotakemura.nytimes.view.LuigiProgressBar;

/**
 * Atividade (tela) principal
 * @author ricardotakemura
 */
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        ArticleAsyncTask.ArticleAsyncTaskDelegate, View.OnScrollChangeListener {

    private RecyclerView articlesRecyclerView;
    private LuigiProgressBar articlesProgressBar;
    private MainActivityState state;
    private Scheduler scheduler;
    private LinearLayoutManager linearLayoutManager;

    /**
     * @see AppCompatActivity#onCreate(Bundle, PersistableBundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state = new MainActivityState();
        scheduler = new Scheduler();
        linearLayoutManager = new LinearLayoutManager(this);
        articlesRecyclerView = findViewById(R.id.articles_recycler_view);
        articlesProgressBar = findViewById(R.id.articles_progressbar);
        setupRecyclerView();
        if (!state.isLoading()) {
            loadMostViews();
        }
    }

    private void setupRecyclerView() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        articlesRecyclerView.setLayoutManager(linearLayoutManager);
        articlesRecyclerView.setOnScrollChangeListener(this);
    }

    private void loadMostViews() {
        state.setLoading(true);
        state.setFromTypeData(MainActivityState.FromTypeData.MOST_VIEW);
        state.firstPage();
        articlesProgressBar.start();
        ArticleAsyncTaskParams params = new ArticleAsyncTaskParams(ArticleAsyncTaskParams.ArticleMethod.MOST_VIEWS);
        final ArticleAsyncTask articleAsyncTask = new ArticleAsyncTask(this);
        articleAsyncTask.execute(params);
    }

    private void loadSearch(String text) {
        state.setLoading(true);
        state.setFromTypeData(MainActivityState.FromTypeData.SEARCH);
        articlesProgressBar.start();
        ArticleAsyncTaskParams params = new ArticleAsyncTaskParams(ArticleAsyncTaskParams.ArticleMethod.SEARCH,
                state.getPage(), text);
        final ArticleAsyncTask articleAsyncTask = new ArticleAsyncTask(this);
        articleAsyncTask.execute(params);
    }


    /**
     * @see AppCompatActivity#onCreateOptionsMenu(Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * @see SearchView.OnQueryTextListener#onQueryTextSubmit(String)
     */
    @Override
    public boolean onQueryTextSubmit(final String query) {
        return false;
    }

    /**
     * @see SearchView.OnQueryTextListener#onQueryTextChange(String)
     */
    @Override
    public boolean onQueryTextChange(final String newText) {
        final String text = newText.trim();
        if (text.length() >= MainActivityState.MINIMUM_LETTERS &&
                !state.getSearchText().equalsIgnoreCase(text)) {
            state.setSearchText(text);
            scheduler.schedule(new Date(), MainActivityState.DEFAULT_TYPING_MILISECONDS, new Scheduler.SchedulerDelegate() {
                @Override
                public void execute() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (state.getSearchText().length() >= MainActivityState.MINIMUM_LETTERS && !state.isLoading()) {
                                state.firstPage();
                                loadSearch(state.getSearchText());
                            }
                        }
                    });
                }
            });
        }
        return false;
    }

    /**
     * @see View.OnScrollChangeListener#onScrollChange(View, int, int, int, int)
     */
    @Override
    public void onScrollChange(View view,
                               int scrollX,
                               int scrollY,
                               int oldScrollX,
                               int oldScrollY) {
        RecyclerView recyclerView = (RecyclerView) view;
        if (!recyclerView.canScrollVertically(MainActivityState.LAST_VERTICAL_DIRECTION) &&
                MainActivityState.FromTypeData.SEARCH.equals(state.getFromTypeData())
                && !state.isLoading() && !state.isLastPage()) {
            state.nextPage();
            loadSearch(state.getSearchText());
        }
    }

    /**
     * @see ArticleAsyncTask.ArticleAsyncTaskDelegate#finished(List, BusinessException)
     */
    @Override
    public void finished(final List<Article> articles, final BusinessException e) {
        if (e == null) {
            ArticleRecyclerViewAdapter articleAdapter = (ArticleRecyclerViewAdapter) articlesRecyclerView.getAdapter();
            if (articleAdapter == null || state.isFirstPage()) {
                articleAdapter = new ArticleRecyclerViewAdapter(articles, this);
                articlesRecyclerView.setAdapter(articleAdapter);
            } else if (!state.isFirstPage()) {
                final int position = articleAdapter.getItemCount();
                articleAdapter.getArticles().addAll(articles);
                articlesRecyclerView.swapAdapter(articleAdapter, false);
                articlesRecyclerView.setVerticalScrollbarPosition(position);
            }
        } else {
            Toast.makeText(this, e.getResourceStringId(), Toast.LENGTH_LONG).show();
        }
        articlesProgressBar.stop();
        state.setLoading(false);
    }
}
