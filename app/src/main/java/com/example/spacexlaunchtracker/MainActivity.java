package com.example.spacexlaunchtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.spacexlaunchtracker.Tasks;

import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "App.main:";
    protected RequestQueue requestQueue;
    private Tasks task = new Tasks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
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
    }
    public void updateText(final char inputCase) {
        String caption = "If you are seeing this, something went very wrong!";
        if (inputCase == 'l') {
            String url = "https://api.spacexdata.com/v3/launches/latest";
            startAPIcall(url);
            caption = task.stringToReturn();
        }
        if (inputCase == 'n') {
            String url = "https://api.spacexdata.com/v3/launches/next";
            startAPIcall(url);
            caption = task.stringToReturn();
        }
        ((TextView) findViewById(R.id.caption)).setText(caption);
    }
    public void updateText(final int input) {
        //This method takes the flight number from the plainText field.
        String caption = ("Flight Number Provided :  " + input);
        ((TextView) findViewById(R.id.caption)).setText(caption);
    }
    public void startAPIcall(String url) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    response -> {
                        Log.d(TAG, response.toString());
                        task.jsonParser(response.toString());
                    }, error -> Log.w(TAG, error.toString()));
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            System.out.println(e.toString());
            Log.w(TAG, "Error calling API");
            System.out.println("Something went wrong");
        }
    }
}
