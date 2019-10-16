package com.fedorrroff.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBarCustom progressBarCustom;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_up);
        RatingView ratingView = findViewById(R.id.rating_view);

        textView = findViewById(R.id.tv_rating);
        progressBarCustom = findViewById(R.id.custom_progressBar);
        ratingView.setProgressBarCustom(progressBarCustom);

        textView.setText(String.valueOf(progressBarCustom.getProgress()));

        Thread thread = new Thread(() -> {
            while (progressBarCustom.getProgress() < 99) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBarCustom.post(() -> progressBarCustom.setProgress(progressBarCustom.getProgress() + 10));
                textView.post(() ->  textView.setText(String.valueOf(progressBarCustom.getProgress())));
            }
        });

        thread.start();
    }
}
