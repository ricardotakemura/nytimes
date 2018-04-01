package br.org.venturus.ricardotakemura.nytimes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

import br.org.venturus.ricardotakemura.nytimes.R;
import br.org.venturus.ricardotakemura.nytimes.holder.ArticleRecyclerViewHolder;
import br.org.venturus.ricardotakemura.nytimes.model.Article;
import br.org.venturus.ricardotakemura.nytimes.model.Picture;
import br.org.venturus.ricardotakemura.nytimes.util.DateUtil;
import br.org.venturus.ricardotakemura.nytimes.view.ResizableImageView;

/**
 * Adaptador de dados para RecyclerView
 * @author ricardotakemura
 */
public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Article> articles;
    private Context context;
    private Picasso picasso;

    private final int MARGIN_DEFAULT = 32;
    private final int ONE_COUNT = 1;

    /**
     * Construtor da classe
     * @param articles Lista de Articles
     * @param context Context
     */
    public ArticleRecyclerViewAdapter(final List<Article> articles, final Context context) {
        this.articles = articles;
        this.context = context;
        this.picasso = Picasso.with(context);
    }

    /**
     * articles
     * @return Lista de Articles (List)
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * @see RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View cardView;
        if (articles == null || articles.isEmpty()) {
            cardView = LayoutInflater.from(context).inflate(R.layout.view_card_article_empty, parent, false);
        } else {
            cardView = LayoutInflater.from(context).inflate(R.layout.view_card_article, parent, false);
        }
        return new ArticleRecyclerViewHolder(cardView);
    }

    /**
     * @see RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (articles != null && !articles.isEmpty()) {
            final Article article = articles.get(position);
            final ArticleRecyclerViewHolder articleHolder = (ArticleRecyclerViewHolder) holder;
            articleHolder.getTitleTextView().setText(article.getTitle());
            articleHolder.getAbstractTextView().setText(article.getAbstractText());
            articleHolder.getPublishedDateTextView().setText(DateUtil.stringFromDate(article.getPublishedDate()));
            final Picture picture = article.getPicture();
            final ResizableImageView imageView = articleHolder.getPictureImageView();
            if (picture != null) {
                final URL url = picture.getUrl();
                if (url != null) {
                    picasso.load(url.toString())
                            .placeholder(R.drawable.gradient_background_animation)
                            .fit().into(imageView);
                    imageView.resize(picture.getWidth(), picture.getHeight(), MARGIN_DEFAULT);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * @see RecyclerView.Adapter#getItemCount()
     */
    @Override
    public int getItemCount() {
        if (articles == null || articles.isEmpty()) {
            return ONE_COUNT;
        }
        return articles.size();
    }
}
