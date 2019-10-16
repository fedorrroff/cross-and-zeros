package com.fedorrroff.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

public class RatingView extends FrameLayout {

    private ProgressBarCustom progressBarCustom;

    public RatingView(Context context) {
        super(context);
        init();
    }

    public RatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Initialize view
     */
    private void init() {
        inflate(getContext(), R.layout.rating_view, this);
    }

    public void setProgressBarCustom(ProgressBarCustom progressBarCustom) {
        this.progressBarCustom = progressBarCustom;
    }
}
