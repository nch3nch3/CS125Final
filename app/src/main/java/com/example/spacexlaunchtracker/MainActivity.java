package com.example.spacexlaunchtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "App.main:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button latest = findViewById(R.id.latestLaunch);
        latest.setOnClickListener(v -> {
            Log.d(TAG, "latest launch button clicked");
            updateText();
        });
        final Button next = findViewById(R.id.nextLaunch);
        next.setOnClickListener(v -> {
            Log.d(TAG, "Next Launch button clicked");
            updateText();
        });
        final Button random = findViewById(R.id.randomLaunch);
        random.setOnClickListener(v -> {
            Log.d(TAG, "Random Launch button clicked");
            updateText();
        });
    }
    public void updateText() {
        double randomNumber = Math.random();
        String caption = ("This is a number for testing: " + randomNumber);
        ((TextView) findViewById(R.id.caption)).setText(caption);
    }
    public void updateText(final int input) {

    }
}
