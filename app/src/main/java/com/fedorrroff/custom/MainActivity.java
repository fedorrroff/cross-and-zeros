package com.fedorrroff.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBarCustom progressBarCustom;
        TextView textView;

        RatingView ratingView = findViewById(R.id.rating_view);

        textView = findViewById(R.id.tv_rating);
        progressBarCustom = findViewById(R.id.custom_progressBar);
        ratingView.setProgressBarCustom(progressBarCustom);
        ratingView.setTextView(textView);

        Thread thread = new Thread(() -> {
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ratingView.setProgress(10);
                ratingView.setRating();
            } while (progressBarCustom.getProgress() < 90);
        });

        thread.start();
    }
}
