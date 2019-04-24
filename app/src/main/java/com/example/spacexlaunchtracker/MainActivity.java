package com.example.spacexlaunchtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
            updateText('l');
        });
        final Button next = findViewById(R.id.nextLaunch);
        next.setOnClickListener(v -> {
            Log.d(TAG, "Next Launch button clicked");
            updateText('n');
        });
        final Button random = findViewById(R.id.randomLaunch);
        random.setOnClickListener(v -> {
            Log.d(TAG, "Random Launch button clicked");
            updateText('r');
        });
        //Can you write a function that takes care of the "launchFinder" button and the Flight Mission field
        final Button flightNo = findViewById(R.id.launchFinder);
        random.setOnClickListener(v -> {
            Log.d(TAG, "Flight number search initiated");
            updateText();
        });
        //These function handle the buttons
    }
    public void updateText(final char inputCase) {
        //this function handles random, next, and latest launch via characters which represent each case
        double randomNumber = Math.random();
        String caption = ("This is a number for testing: " + randomNumber);
        ((TextView) findViewById(R.id.caption)).setText(caption);
        //This is for testing code is for testing
    }
    public void updateText(final int input) {
        EditText flight = findViewById(R.id.missionInput);
        String number =  "Flight Number : " + flight.getText().toString();
        ((TextView) findViewById(R.id.flightNumber)).setText(number);
    }
}
