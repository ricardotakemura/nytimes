package br.org.venturus.ricardotakemura.nytimes.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import br.org.venturus.ricardotakemura.nytimes.R;
import br.org.venturus.ricardotakemura.nytimes.util.DpiUtil;

public class LuigiProgressBar extends FrameLayout {

    private final long ANIMATION_DURATION = 3000;
    private final int DEFAULT_HEIGHT = 32;
    private final int DEFAULT_WIDTH = 21;
    private final int DEFAULT_MARGIN = 16;
    private final int DEFAULT_TEXT_SIZE = 16;
    private final String X_PROPERTY_NAME = "x";

    private ImageView luigiImage;
    private TextView loadingTextView;
    private AnimationDrawable animationDrawable;
    private ObjectAnimator animation;

    public LuigiProgressBar(Context context) {
        super(context);
    }

    public LuigiProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LuigiProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LuigiProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void createLoadingTextView() {
        loadingTextView = new TextView(getContext());
        loadingTextView.setText(R.string.loading_message);
        loadingTextView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL));
        loadingTextView.setX(DEFAULT_MARGIN);
        loadingTextView.setY(0);
        loadingTextView.setTextSize(DEFAULT_TEXT_SIZE);
        loadingTextView.setTextColor(Color.WHITE);
        loadingTextView.setVisibility(VISIBLE);
        addView(loadingTextView);
    }

    private void createLuigiImageViewWithAnimation() {
        final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        animationDrawable = (AnimationDrawable) getContext().getDrawable(R.drawable.sprite_loading_animation);
        luigiImage = new ImageView(getContext());
        luigiImage.setImageDrawable(animationDrawable);
        animationDrawable.start();
        luigiImage.setLayoutParams(new FrameLayout.LayoutParams(DpiUtil.toPixels(DEFAULT_WIDTH),
                DpiUtil.toPixels(DEFAULT_HEIGHT), Gravity.CENTER_VERTICAL));
        luigiImage.setX(metrics.widthPixels);
        luigiImage.setY(0);
        luigiImage.setAdjustViewBounds(true);
        luigiImage.setScaleType(ImageView.ScaleType.FIT_XY);
        luigiImage.setVisibility(VISIBLE);
        addView(luigiImage);
        animation = ObjectAnimator.ofFloat(luigiImage, X_PROPERTY_NAME, -DpiUtil.toPixels(DEFAULT_WIDTH));
        animation.setDuration(ANIMATION_DURATION);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.start();
    }

    public void start() {
        createLoadingTextView();
        createLuigiImageViewWithAnimation();
        setVisibility(VISIBLE);
    }

    public void stop() {
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        if (animation != null) {
            animation.cancel();
        }
        if (luigiImage != null) {
            luigiImage.setVisibility(GONE);
            removeView(luigiImage);
        }
        if (loadingTextView != null) {
            loadingTextView.setVisibility(GONE);
            removeView(loadingTextView);
        }
        setVisibility(GONE);
        animationDrawable = null;
        luigiImage = null;
        animation = null;
        loadingTextView = null;
    }
}
