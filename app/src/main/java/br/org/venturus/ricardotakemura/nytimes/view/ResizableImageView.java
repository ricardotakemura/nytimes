package br.org.venturus.ricardotakemura.nytimes.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class ResizableImageView extends AppCompatImageView {

    public ResizableImageView(final Context context) {
        super(context);
    }

    public ResizableImageView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizableImageView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

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
