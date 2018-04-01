package br.org.venturus.ricardotakemura.nytimes.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Classe de ImageView que redimensiona uma imagem
 * @author ricardotakemura
 */
public class ResizableImageView extends AppCompatImageView {

    /**
     * Construtor da classe
     * @param context Context
     */
    public ResizableImageView(final Context context) {
        super(context);
    }

    /**
     * Construtor da classe
     * @param context Context
     * @param attrs AttributeSet
     */
    public ResizableImageView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Construtor da classe
     * @param context Context
     * @param attrs AttributeSet
     * @param defStyleAttr styleAttr (int)
     */
    public ResizableImageView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Redimensiona a imagem
     * @param width largura (int)
     * @param height altura (int)
     * @param margin margem (int)
     */
    public void resize(final int width, final int height, final int margin) {
        final int newHeight = (int) ((double) height *
                (double)(Resources.getSystem().getDisplayMetrics().widthPixels - margin) / (double) width);
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams.height != newHeight) {
            layoutParams.height = newHeight;
            requestLayout();
        }
    }
}
