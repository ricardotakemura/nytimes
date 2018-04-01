package br.org.venturus.ricardotakemura.nytimes.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import br.org.venturus.ricardotakemura.nytimes.R;

public class LuigiProgressBar extends FrameLayout {

    private ImageView luigiImage;
    private AnimationDrawable animationDrawable;
    private ObjectAnimator animation;
    private long ANIMATION_DURATION = 3000;
    private float IMAGE_DEFAULT_HEIGHT = 256.0f;
    private float IMAGE_DEFAULT_WIDTH = 170.0f;
    private int DEFAULT_HEIGHT = 72;

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

    public void start() {
        luigiImage = new ImageView(getContext());
        animationDrawable = (AnimationDrawable) getContext().getDrawable(R.drawable.sprite_loading_animation);
        luigiImage.setImageDrawable(animationDrawable);
        animationDrawable.start();
        final int width = (int) (IMAGE_DEFAULT_WIDTH * (float) DEFAULT_HEIGHT / IMAGE_DEFAULT_HEIGHT);
        luigiImage.setLayoutParams(new FrameLayout.LayoutParams(width, DEFAULT_HEIGHT, Gravity.CENTER_VERTICAL));
        luigiImage.setX(Resources.getSystem().getDisplayMetrics().widthPixels);
        luigiImage.setY(0);
        luigiImage.setVisibility(VISIBLE);
        addView(luigiImage);
        animation = ObjectAnimator.ofFloat(luigiImage, "x", - IMAGE_DEFAULT_WIDTH);
        animation.setDuration(ANIMATION_DURATION);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.start();
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
        setVisibility(GONE);
        animationDrawable = null;
        luigiImage = null;
        animation = null;
    }
}
