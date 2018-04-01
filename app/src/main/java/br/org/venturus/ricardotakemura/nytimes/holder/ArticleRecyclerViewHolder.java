package br.org.venturus.ricardotakemura.nytimes.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.org.venturus.ricardotakemura.nytimes.R;
import br.org.venturus.ricardotakemura.nytimes.view.ResizableImageView;

/**
 * Classe que associa a visão aos dados
 */
public class ArticleRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView abstractTextView;
    private TextView publishedDateTextView;
    private ResizableImageView pictureImageView;

    /**
     * Construtor da classe
     * @param itemView View
     */
    public ArticleRecyclerViewHolder(final View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title_text_view);
        abstractTextView = itemView.findViewById(R.id.abstract_text_view);
        publishedDateTextView = itemView.findViewById(R.id.published_date_text_view);
        pictureImageView = itemView.findViewById(R.id.picture_image_view);
    }

    /**
     * title text view
     * @return TextView
     */
    public TextView getTitleTextView() {
        return titleTextView;
    }

    /**
     * abstract text view
     * @return TextView
     */
    public TextView getAbstractTextView() {
        return abstractTextView;
    }

    /**
     * published date text view
     * @return TextView
     */
    public TextView getPublishedDateTextView() {
        return publishedDateTextView;
    }

    /**
     * picture image view
     * @return ResizableImageView
     */
    public ResizableImageView getPictureImageView() {
        return pictureImageView;
    }

}
