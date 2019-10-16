package com.fedorrroff.custom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBarCustom progressBarCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView;

        RatingView ratingView = findViewById(R.id.rating_view);

        textView = findViewById(R.id.tv_rating);
        progressBarCustom = findViewById(R.id.custom_progressBar);
        ratingView.setProgressBarCustom(progressBarCustom);
        ratingView.setTextView(textView);

        if (savedInstanceState != null) {
            progressBarCustom.setProgress(savedInstanceState.getFloat("progress"));
            ratingView.setRating();
        } else {
            ratingView.upProgress(0);
        }

        Thread thread = new Thread(() -> {
            while (progressBarCustom.getProgress() < 90) {
                System.out.println(progressBarCustom.getProgress());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ratingView.upProgress(10);
                ratingView.setRating();
            }
        });
        thread.start();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("progress", this.progressBarCustom.getProgress());
    }
}
