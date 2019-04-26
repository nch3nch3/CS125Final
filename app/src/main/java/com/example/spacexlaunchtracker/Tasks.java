package com.example.spacexlaunchtracker;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;


public class Tasks {
    /** Default logging tag for messages from the main activity. */
    private String nameOfMission;
    private String timeOfMission;
    private String rocketUsed;
    private String launchSite;
    private int flightNumber;

    public String stringToReturn() {
        return "Flight Number: " + flightNumber + "\n"
                + "Mission Name: " + nameOfMission + "\n"
                + "Launch Date UTC: " + timeOfMission + "\n"
                + "Rocket Used: " + rocketUsed + "\n"
                + "Launch Site: " + launchSite;
    }

    public void jsonParser(String json) {
        nameOfMission = missionName(json);
        timeOfMission = launchDate(json);
        rocketUsed = rocketData(json);
        launchSite = launchSite(json);
        flightNumber = flightNumber(json);
    }
    private String missionName(String json) {
        try {
            JsonParser parser = new JsonParser();
            return parser.parse(json)
                    .getAsJsonObject()
                    .get("mission_name")
                    .getAsString();
        } catch (Exception e) {
            return "Mission name not found";
        }
    }
    private String launchDate(String json) {
        try {
            JsonParser parser = new JsonParser();
            return parser.parse(json)
                    .getAsJsonObject()
                    .get("launch_date_utc")
                    .getAsString();
        } catch (Exception e) {
            return "Date not found";
        }
    }
    private String rocketData(String json) {
        try {
            JsonParser parser = new JsonParser();
            return parser.parse(json)
                    .getAsJsonObject()
                    .getAsJsonObject("rocket")
                    .get("rocket_name")
                    .getAsString();
        } catch (Exception e) {
            return "Rocket info not found";
        }
    }
    private String launchSite(String json) {
        try {
            JsonParser parser = new JsonParser();
            return parser.parse(json)
                    .getAsJsonObject()
                    .getAsJsonObject("launch_site")
                    .get("site_name")
                    .getAsString();
        } catch (Exception e) {
            return "Launch site not found";
        }
    }
    private int flightNumber(String json) {
        try {
            JsonParser parser = new JsonParser();
            return parser.parse(json)
                    .getAsJsonObject()
                    .get("flight_number")
                    .getAsInt();
        } catch (Exception e) {
            return -1;
        }
    }
}
