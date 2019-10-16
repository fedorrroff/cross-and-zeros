package com.fedorrroff.custom;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RatingView extends FrameLayout {

    private ProgressBarCustom progressBarCustom;
    private TextView textView;

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

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void upProgress(int adding) {
        progressBarCustom.post(() -> {
            progressBarCustom.setProgress(progressBarCustom.getProgress() + adding);
            if (progressBarCustom.getProgress() >= 50) {
                progressBarCustom.setColorForeground(Color.GREEN);
            }
        });
    }

    public void setRating() {
        textView.post(() -> textView.setText(String.valueOf(progressBarCustom.getProgress())));
    }

    @Override
    public Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putFloat("stuff", this.progressBarCustom.getProgress()); // ... save stuff
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.progressBarCustom.setProgress(bundle.getFloat("stuff"));
            state = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(state);
    }
}
