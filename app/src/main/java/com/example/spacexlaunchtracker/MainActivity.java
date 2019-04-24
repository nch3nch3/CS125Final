package com.example.spacexlaunchtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        final Button findMission = findViewById(R.id.launchFinder);
        findMission.setOnClickListener(v -> {
            Log.d(TAG, "Random Launch button clicked");
            EditText textHere = findViewById(R.id.missionInput);
            String numberAsString = textHere.getText().toString();
            try {
                int number = Integer.parseInt(numberAsString);
                updateText(number);
            } catch (Exception e) {
                String caption = ("Input is invalid");
                ((TextView) findViewById(R.id.caption)).setText(caption);
            }
        });
        //These function handle the buttons

        //Can you write a function that takes care of the "launchFinder" button and the Flight Mission field
    }
    public void updateText(final char inputCase) {
        //this function handles random, next, and latest launch via characters which represent each case
        double randomNumber = Math.random();
        String caption = ("This is a number for testing: " + randomNumber);
        ((TextView) findViewById(R.id.caption)).setText(caption);
        //This is for testing code is for testing
    }
    public void updateText(final int input) {
        //This method takes the flight number from the plainText field.
        String caption = ("This is a your number: " + input);
        ((TextView) findViewById(R.id.caption)).setText(caption);
    }
}
