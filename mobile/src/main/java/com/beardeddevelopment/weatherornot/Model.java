package com.beardeddevelopment.weatherornot;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Author: chris
 * Date: 4/27/2016
 * Time: 3:09 PM
 * Project: bitplease
 */
class Model {
    private final int DAILYFORECASTLENGTH = 10;
    private final String ERROR100 = "ERROR: 100"; // Error code if conditions information is not found in json
    private final String ERROR101 = "ERROR: 101"; // Error code if Daily Forecast information is not found in json
    private final String ERROR102 = "ERROR: 102"; // Error code if Hourly Forecast information is not found in json
    private JsonObject jse;
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = Model.class.getSimpleName();

    Model() throws MalformedURLException {
        String APIKEY = "ca5d16f47e75bf44";
        URL url = new URL("http://api.wunderground.com/api/" + APIKEY + "/conditions/forecast10day/hourly10day/q/autoip.json");
        JsonParser j = new JsonParser();
        jse = j.parse(makeHttpRequest(url)).getAsJsonObject();
    }

    private String makeHttpRequest(URL url) {
        String jsonResponse = "";
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Checks for conditions information in json
     *
     * @return 1 if conditions information is found, 0 if not
     */
    private int checkForConditions() {
        return jse.getAsJsonObject("response").getAsJsonObject("features").get("conditions").getAsInt();
    }

    /**
     * Checks for daily forecast information in json
     *
     * @return 1 if daily forecast information is found, 0 if not
     */
    private int checkForDailyForecast() {
        return jse.getAsJsonObject("response").getAsJsonObject("features").get("forecast10day").getAsInt();
    }

    /**
     * Checks for hourly forecast information in json
     *
     * @return 1 if hourly forecast information is found, 0 if not
     */
    private int checkForHourlyForecast() {
        return jse.getAsJsonObject("response").getAsJsonObject("features").get("hourly10day").getAsInt();
    }

    /**
     *
     * @return Current conditions as a string
     */
    String getConditionsWeather() {
        if (checkForConditions() == 1) {
            return jse.getAsJsonObject("current_observation").get("weather").getAsString();
        } else {
            return ERROR100;
        }
    }

    /**
     *
     * @return Current Location as a string
     */
    String getDisplayLocationFull() {
        if (checkForConditions() == 1) {
            return jse.getAsJsonObject("current_observation").getAsJsonObject("display_location").get("full").getAsString();
        } else {
            return ERROR100;
        }
    }

    /**
     *
     * @return Current temperature in Deg F format: 000°F
     */
    String getConditionsTempF() {
        if (checkForConditions() == 1) {
            return jse.getAsJsonObject("current_observation").get("temp_f").getAsDouble() + "\u00b0F";
        } else {
            return ERROR100;
        }
    }

    /**
     *
     * @return Current temperature in Deg C format: 000°C
     */
    String getConditionsTempC() {
        if (checkForConditions() == 1) {
            return jse.getAsJsonObject("current_observation").get("temp_f").getAsDouble() + "\u00b0C";
        } else {
            return ERROR100;
        }
    }

    /**
     *
     * @return
     */
    String getConditionsTempString() {
        if (checkForConditions() == 1) {
            return jse.getAsJsonObject("current_observation").get("temperature_string").getAsString();
        } else {
            return ERROR100;
        }
    }

    /**
     *
     * @return
     */
    String[] getDailyForecastWeekdayShort() {
        if (checkForDailyForecast() == 1) {
            String[] result = new String[DAILYFORECASTLENGTH];
            for (int i = 0; i < DAILYFORECASTLENGTH; i++) {
                result[i] = jse.getAsJsonObject("forecast").getAsJsonObject("simpleforecast").getAsJsonArray("forecastday").get(i).getAsJsonObject().getAsJsonObject("date").get("weekday_short").getAsString();
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    String[] getDailyForecastHighTempF() {
        if (checkForDailyForecast() == 1) {
            String[] result = new String[DAILYFORECASTLENGTH];
            for (int i = 0; i < DAILYFORECASTLENGTH; i++) {
                result[i] = jse.getAsJsonObject("forecast").getAsJsonObject("simpleforecast").getAsJsonArray("forecastday").get(i).getAsJsonObject().getAsJsonObject("high").get("fahrenheit").getAsString() + "\u00b0F";
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    String[] getDailyForecastLowTempF() {
        if (checkForDailyForecast() == 1) {
            String[] result = new String[DAILYFORECASTLENGTH];
            for (int i = 0; i < DAILYFORECASTLENGTH; i++) {
                result[i] = jse.getAsJsonObject("forecast").getAsJsonObject("simpleforecast").getAsJsonArray("forecastday").get(i).getAsJsonObject().getAsJsonObject("low").get("fahrenheit").getAsString() + "\u00b0F";
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    String[] getDailyForecastHighTempC() {
        if (checkForDailyForecast() == 1) {
            String[] result = new String[DAILYFORECASTLENGTH];
            for (int i = 0; i < DAILYFORECASTLENGTH; i++) {
                result[i] = jse.getAsJsonObject("forecast").getAsJsonObject("simpleforecast").getAsJsonArray("forecastday").get(i).getAsJsonObject().getAsJsonObject("high").get("celsius").getAsString() + "\u00b0C";
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    String[] getDailyForecastLowTempC() {
        if (checkForDailyForecast() == 1) {
            String[] result = new String[DAILYFORECASTLENGTH];
            for (int i = 0; i < DAILYFORECASTLENGTH; i++) {
                result[i] = jse.getAsJsonObject("forecast").getAsJsonObject("simpleforecast").getAsJsonArray("forecastday").get(i).getAsJsonObject().getAsJsonObject("low").get("celsius").getAsString() + "\u00b0C";
            }
            return result;
        } else {
            return null;
        }
    }

    String[] getDailyForecastPOP() {
        if (checkForDailyForecast() == 1) {
            String[] result = new String[DAILYFORECASTLENGTH];
            for (int i = 0; i < DAILYFORECASTLENGTH; i++) {
                result[i] = jse.getAsJsonObject("forecast").getAsJsonObject("simpleforecast").getAsJsonArray("forecastday").get(i).getAsJsonObject().get("pop").getAsString() + "%";
            }
            return result;
        } else {
            return null;
        }
    }
}