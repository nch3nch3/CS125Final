package com.example.spacexlaunchtracker;

import com.google.gson.JsonParser;

import java.util.Random;


public class Tasks {
    /** Default logging tag for messages from the main activity. */
    private String nameOfMission;
    private String timeOfMission;
    private String rocketUsed;
    private String launchSite;
    private int flightNumber;
    private int latestFlightNumber;
    private boolean recovery;

    /**This function is called to pick an random integer.
     * @return the chosen integer.
     */
    public int randomMissionPicker() {
        Random numberPicker = new Random();
        return numberPicker.nextInt(latestFlightNumber) + 1;
        //range from zero to latest.
    }

    /**This function sets up the string that will be displayed.
     * @return the string containing the flight information.
     */
    public String stringToReturn() {
        return "Flight Number: " + flightNumber + "\n"
                + "Mission Name: " + nameOfMission + "\n"
                + "Launch Date UTC: " + timeOfMission + "\n"
                + "Rocket Used: " + rocketUsed + "\n"
                + "Launch Site: " + launchSite + "\n"
                + "Recovered: " + recovery;
    }

    /**This takes parsed information from it's helpers and stores them.
     * @param json the json string to be parsed.
     */
    public void jsonParser(String json) {
        nameOfMission = missionName(json);
        timeOfMission = launchDate(json);
        rocketUsed = rocketData(json);
        launchSite = launchSite(json);
        flightNumber = flightNumber(json);
        recovery = recovery(json);
    }
    //These later functions are the helper functions that actually parse the json.
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

    /**Called on creation to ensure the range of launches is correct.
     * @param json
     */
    public void setUpMaxFlightNum(String json) {
        latestFlightNumber = flightNumber(json);
    }
    public boolean recovery(String json) {
        try {
            JsonParser parser = new JsonParser();
            return parser.parse(json)
                    .getAsJsonObject()
                    .getAsJsonObject()
                    .getAsJsonObject("recovery")
                    .get("recovered")
                    .getAsBoolean();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Boolean to check which rocket was used
     * @param json input provided by API
     * @return boolean value
     */
    public boolean isFalconNine(String json) {
        if (rocketUsed == null) {
            return false;
        } else if (rocketUsed.equals("Falcon 9")) {
            return true;
        }
        return false;
    }
    /**
     * Boolean to check which rocket was used
     * @return boolean value
     */
    public boolean isFalconHeavy() {
        if (rocketUsed == null) {
            return false;
        } else if (rocketUsed.equals("Falcon Heavy")) {
            return true;
        }
        return false;
    }
    public boolean isFalcon9() {
        if (rocketUsed == null) {
            return false;
        } else if (rocketUsed.equals("Falcon 9")) {
            return true;
        }
        return false;
    }

}
