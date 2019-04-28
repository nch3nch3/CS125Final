package com.example.spacexlaunchtracker;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
        firstApiCall();
        //API is called on creation to ensure an accurate range.
        final Button latest = findViewById(R.id.latestLaunch);
        latest.setOnClickListener(v -> {
            Log.d(TAG, "latest launch button clicked");
            String url = "https://api.spacexdata.com/v3/launches/latest";
            startAPIcall(url);
        });
        final Button next = findViewById(R.id.nextLaunch);
        next.setOnClickListener(v -> {
            Log.d(TAG, "Next Launch button clicked");
            String url = "https://api.spacexdata.com/v3/launches/next";
            startAPIcall(url);
        });
        final Button random = findViewById(R.id.randomLaunch);
        random.setOnClickListener(v -> {
            Log.d(TAG, "Random Launch button clicked");
            String url = "https://api.spacexdata.com/v3/launches/" + task.randomMissionPicker();
            startAPIcall(url);
        });
        final Button findMission = findViewById(R.id.launchFinder);
        findMission.setOnClickListener(v -> {
            Log.d(TAG, "Random Launch button clicked");
            EditText textHere = findViewById(R.id.missionInput);
            String numberAsString = textHere.getText().toString();
            try {
                int number = Integer.parseInt(numberAsString);
                String url = "https://api.spacexdata.com/v3/launches/" + number;
                startAPIcall(url);
            } catch (Exception e) {
                String caption = ("Input is invalid");
                ((TextView) findViewById(R.id.caption)).setText(caption);
            }
        });
        //These function handle the buttons
    }

    /**This function updates the text.
     */
    public void updateText() {
        String caption;
        caption = task.stringToReturn();
        System.out.println(caption);
        ((TextView) findViewById(R.id.caption)).setText(caption);
    }

    /**This function handles the API call and call the function to update text.
     * @param url is the URL that is being called
     */
    public void startAPIcall(String url) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    response -> {
                        Log.d(TAG, response.toString());
                        task.jsonParser(response.toString());
                        updateText();
                        updateImage();
                    }, error -> {
                        Log.w(TAG, error.toString());
                        String caption = ("Input is invalid");
                        ((TextView) findViewById(R.id.caption)).setText(caption);
                    });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            System.out.println(e.toString());
            Log.w(TAG, "Error calling API");
            System.out.println("Something went wrong");
        }
    }

    /**This function is for the initial set-up to make sure when finding a random launch,
     * the range of possible launches is up to date.
     */
    public void firstApiCall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.spacexdata.com/v3/launches/latest",
                    null,
                    response -> {
                        Log.d(TAG, response.toString());
                        task.setUpMaxFlightNum(response.toString());
                    }, error -> {
                Log.w(TAG, error.toString());
                String caption = ("Something went wrong");
                ((TextView) findViewById(R.id.caption)).setText(caption);
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            System.out.println(e.toString());
            Log.w(TAG, "Error calling API");
            System.out.println("Something went wrong");
        }
    }
    public void updateImage() {
        if (task.isFalconHeavy()) {
            findViewById(R.id.FalconHeavy).setVisibility(View.VISIBLE);
        }
    }
}
