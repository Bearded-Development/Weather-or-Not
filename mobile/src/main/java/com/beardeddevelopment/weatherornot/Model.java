package com.beardeddevelopment.weatherornot;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author: chris
 * Date: 4/27/2016
 * Time: 3:09 PM
 * Project: bitplease
 */
public class Model {
    private String APIKEY = "f8d4c7ddb0cdb6aa";
    JsonElement jse;

    public Model(String location) {
        if (location.contains(",")) {
            String city = location.substring(0, location.indexOf(','));
            String state = location.substring((location.indexOf(',') + 2));
            city = city.replaceAll(" ", "_");
            getJsonFromCity(city, state);
        } else if (!location.contains(",")) {
            getJsonFromZip(location);
        }
    }

    private void getJsonFromZip(String zip) {
        String json = "";

        try {
            URL url = new URL("http://api.wunderground.com/api/" + APIKEY + "/hourly/conditions/forecast10day/q/" + zip + ".json");
            InputStream is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = rd.readLine()) != null)
            {
                json += line;
            }
            rd.close();
        }
        catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        JsonParser parser = new JsonParser();
        jse = parser.parse(json);
    }

    private void getJsonFromCity(String city, String state) {
        String json = "";

        try {
            URL url = new URL("http://api.wunderground.com/api/" + APIKEY + "/hourly/conditions/forecast10day/q/" + state + "/" + city + ".json");
            System.out.println(url);
            InputStream is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = rd.readLine()) != null)
            {
                json += line;
            }
            rd.close();
        }
        catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        JsonParser parser = new JsonParser();
        jse = parser.parse(json);
    }

    public boolean verifyModel() {
        if (jse.getAsJsonObject().get("response").getAsJsonObject().has("results") || jse.getAsJsonObject().get("response").getAsJsonObject().has("error")) {
           // returns false if error
            return false;
        } else {
            // returns true if OK
            return true;
        }
    }

    /*
     * *********************************************************************************************
     *
     * Methods for accessing the conditions information
     *
     * *********************************************************************************************
     */

    /**
     *
     *
     */
    public String getimageURL() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("image").getAsJsonObject().get("url").getAsString();
    }

    /**
     *
     *
     */
    public String gitimageTitle() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("image").getAsJsonObject().get("title").getAsString();
    }

    /**
     *
     *
     */
    public String gitimageLink() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("image").getAsJsonObject().get("link").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationFull() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("full").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationCity() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("city").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationState() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("state").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationStateName() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("state_name").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationCountry() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("country").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationCountryISO() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("country_iso3166").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationZip() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("zip").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationLatitude() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("latitude").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationLongitude() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("longitude").getAsString();
    }

    /**
     *
     *
     */
    public String getDisplayLocationElevation() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("display_location").getAsJsonObject().get("elevation").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationFull() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("full").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationCity() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("city").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationState() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("state").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationCountry() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("country").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationCountryISO() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("country_iso3166").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationLatitude() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("latitude").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationLongitude() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("longitude").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationLocationElevation() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_location").getAsJsonObject().get("elevation").getAsString();
    }

    /**
     *
     *
     */
    public String getStationID() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("station_id").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationTime() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_time").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationTimeRFC() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_time_rfc822").getAsString();
    }

    /**
     *
     *
     */
    public String getObservationEpoch() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("observation_epoch").getAsString();
    }

    /**
     *
     *
     */
    public String getLocalTimeRFC() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("local_time_rfc822").getAsString();
    }

    /**
     *
     *
     */
    public String getLocalEpoch() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("local_epoch").getAsString();
    }

    /**
     *
     *
     */
    public String getLocalTZShort() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("local_tz_short").getAsString();
    }

    /**
     *
     *
     */
    public String getLocalTZLong() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("local_tz_long").getAsString();
    }

    /**
     *
     *
     */
    public String getLocalTZOffset() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("local_tz_offset").getAsString();
    }

    /**
     *
     *
     */
    public String getWeather() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("weather").getAsString();
    }

    /**
     *
     *
     */
    public String getTemperaturString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("temperature_string").getAsString();
    }

    /**
     *
     *
     */
    public double getTemp_f() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("temp_f").getAsDouble();
    }

    /**
     *
     *
     */
    public double getTemp_c() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("temp_c").getAsDouble();
    }

    /**
     *
     *
     */
    public String getRelativeHumidity() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("relative_humidity").getAsString();
    }

    /**
     *
     *
     */
    public String getWindString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("wind_string").getAsString();
    }

    /**
     *
     *
     */
    public String getWindDirection() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("wind_dir").getAsString();
    }

    /**
     *
     *
     */
    public double getWindDegrees() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("wind_degrees").getAsDouble();
    }

    /**
     *
     *
     */
    public double getWindMPH() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("wind_mph").getAsDouble();
    }

    /**
     *
     *
     */
    public String getWindGustMPH() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("wind_gust_mph").getAsString();
    }

    /**
     *
     *
     */
    public double getWindKPH() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("wind_kph").getAsDouble();
    }

    /**
     *
     *
     */
    public String getWindGustKPH() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("wind_gust_kph").getAsString();
    }

    /**
     *
     *
     */
    public String getPressureMB() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("pressure_mb").getAsString();
    }

    /**
     *
     *
     */
    public String getPressureIn() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("pressure_in").getAsString();
    }

    /**
     *
     *
     */
    public String getPressureTrend() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("pressure_trend").getAsString();
    }

    /**
     *
     *
     */
    public String getDewpointString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("dewpoint_string").getAsString();
    }

    /**
     *
     *
     */
    public double getDewpointF() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("dewpoint_f").getAsDouble();
    }

    /**
     *
     *
     */
    public double getDewpointC() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("dewpoint_c").getAsDouble();
    }

    /**
     *
     *
     */
    public String getHeatIndexString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("heat_index_string").getAsString();
    }

    /**
     *
     *
     */
    public String getHeatIndexF() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("heat_index_f").getAsString();
    }

    /**
     *
     *
     */
    public String getHeatIndexC() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("heat_index_c").getAsString();
    }

    /**
     *
     *
     */
    public String getWindChillString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("windchill_string").getAsString();
    }

    /**
     *
     *
     */
    public String getWindChillF() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("windchill_f").getAsString();
    }

    /**
     *
     *
     */
    public String getWindChillC() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("windchill_c").getAsString();
    }

    /**
     *
     *
     */
    public String getFeelsLikeString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("feelslike_string").getAsString();
    }

    /**
     *
     *
     */
    public String getFeelsLikeF() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("feelslike_f").getAsString();
    }

    /**
     *
     *
     */
    public String getFeelsLikeC() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("feelslike_c").getAsString();
    }

    /**
     *
     *
     */
    public String getVisibilityMi() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("visibility_mi").getAsString();
    }

    /**
     *
     *
     */
    public String getVisibilityKM() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("visibility_km").getAsString();
    }

    /**
     *
     *
     */
    public String getSolarRadiation() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("solarradiation").getAsString();
    }

    /**
     *
     *
     */
    public String getUVIndex() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("UV").getAsString();
    }

    /**
     *
     *
     */
    public String getPrecipitationHrString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("precip_1hr_string").getAsString();
    }

    /**
     *
     *
     */
    public String getPrecipitationHrIn() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("precip_1hr_in").getAsString();
    }

    /**
     *
     *
     */
    public String getPrecipitationHrMetric() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("precip_1hr_metric").getAsString();
    }

    /**
     *
     *
     */
    public String getPrecipitationTodayString() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("precip_today_string").getAsString();
    }

    /**
     *
     *
     */
    public String getPrecipitationTodayIn() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("precip_today_in").getAsString();
    }

    /**
     *
     *
     */
    public String getPrecipitationTodayMetric() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("precip_today_metric").getAsString();
    }

    /**
     *
     *
     */
    public String getIcon() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("icon").getAsString();
    }

    /**
     *
     *
     */
    public String getIconURL() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("icon_url").getAsString();
    }

    /**
     *
     *
     */
    public String getForcastURL() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("forecast_url").getAsString();
    }

    /**
     *
     *
     */
    public String getHistoryURL() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("history_url").getAsString();
    }

    /**
     *
     *
     */
    public String getObURL() {
        return jse.getAsJsonObject().get("current_observation").getAsJsonObject().get("ob_url").getAsString();
    }

    /*
     * *********************************************************************************************
     *
     * Methods for daily forecast information
     *
     * *********************************************************************************************
     */

    public int[] getTextForecastPeriod() {
        int[] period = new int[20];
        for (int i = 0; i < 20; i++) {
            period[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("txt_forecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("period").getAsInt();
        }
        return period;
    }

    public String[] getTextForecastIcon() {
        String[] icon = new String[20];
        for (int i = 0; i < 20; i++) {
            icon[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("txt_forecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("icon").getAsString();
        }
        return icon;
    }

    public String[] getTextForecastIconURL() {
        String[] iconURL = new String[20];
        for (int i = 0; i < 20; i++) {
            iconURL[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("txt_forecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("icon_url").getAsString();
        }
        return iconURL;
    }

    public String[] getTextForecastTitle() {
        String[] title = new String[20];
        for (int i = 0; i < 20; i++) {
            title[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("txt_forecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("title").getAsString();
        }
        return title;
    }

    public String[] getTextForecastForecastText() {
        String[] forecastText = new String[20];
        for (int i = 0; i < 20; i++) {
            forecastText[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("txt_forecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("fcttext").getAsString();
        }
        return forecastText;
    }

    public String[] getTextForecastForecastTextMetric() {
        String[] forecastTextMetric = new String[20];
        for (int i = 0; i < 20; i++) {
            forecastTextMetric[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("txt_forecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("fcttext_metric").getAsString();
        }
        return forecastTextMetric;
    }

    public String[] getTextForecastPop() {
        String[] pop = new String[20];
        for (int i = 0; i < 20; i++) {
            pop[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("txt_forecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("pop").getAsString();
        }
        return pop;
    }

    public String[] getSimpleForecastDateEpoch() {
        String[] epoch = new String[10];
        for (int i = 0; i < 10; i++) {
            epoch[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("epoch").getAsString();
        }
        return epoch;
    }

    public String[] getSimpleForecastDatePretty() {
        String[] pretty = new String[10];
        for (int i = 0; i < 10; i++) {
            pretty[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("pretty").getAsString();
        }
        return pretty;
    }

    public int[] getSimpleForecastDateDay() {
        int[] dateDay = new int[10];
        for (int i = 0; i < 10; i++) {
            dateDay[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("day").getAsInt();
        }
        return dateDay;
    }

    public int[] getSimpleForecastDateMonth() {
        int[] dateMonth = new int[10];
        for (int i = 0; i < 10; i++) {
            dateMonth[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("month").getAsInt();
        }
        return dateMonth;
    }

    public int[] getSimpleForecastDateYear() {
        int[] dateYear = new int[10];
        for (int i = 0; i < 10; i++) {
            dateYear[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("year").getAsInt();
        }
        return dateYear;
    }

    public int[] getSimpleForecastDateDayOfYear() {
        int[] dayOfYear = new int[10];
        for (int i = 0; i < 10; i++) {
            dayOfYear[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("yday").getAsInt();
        }
        return dayOfYear;
    }

    public int[] getSimpleForecastDateHour() {
        int[] hour = new int[10];
        for (int i = 0; i < 10; i++) {
            hour[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("hour").getAsInt();
        }
        return hour;
    }

    public String[] getSimpleForecastDateMinute() {
        String[] minute = new String[10];
        for (int i = 0; i < 10; i++) {
            minute[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("min").getAsString();
        }
        return minute;
    }

    public int[] getSimpleForecastDateSecond() {
        int[] second = new int[10];
        for (int i = 0; i < 10; i++) {
            second[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("sec").getAsInt();
        }
        return second;
    }

    public String[] getSimpleForecastDateIsDst() {
        String[] isdst = new String[10];
        for (int i = 0; i < 10; i++) {
            isdst[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("isdst").getAsString();
        }
        return isdst;
    }

    public String[] getSimpleForecastDateMonthName() {
        String[] monthName = new String[10];
        for (int i = 0; i < 10; i++) {
            monthName[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("monthname").getAsString();
        }
        return monthName;
    }

    public String[] getSimpleForecastDateMonthNameShort() {
        String[] monthNameShort = new String[10];
        for (int i = 0; i < 10; i++) {
            monthNameShort[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("monthname_short").getAsString();
        }
        return monthNameShort;
    }

    public String[] getSimpleForecastDateWeekDayShort() {
        String[] weekDayShort = new String[10];
        for (int i = 0; i < 10; i++) {
            weekDayShort[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("weekday_short").getAsString();
        }
        return weekDayShort;
    }

    public String[] getSimpleForecastDateWeekDay() {
        String[] weekDay = new String[10];
        for (int i = 0; i < 10; i++) {
            weekDay[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("weekday").getAsString();
        }
        return weekDay;
    }

    public String[] getSimpleForecastDateAMPM() {
        String[] ampm = new String[10];
        for (int i = 0; i < 10; i++) {
            ampm[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("ampm").getAsString();
        }
        return ampm;
    }

    public String[] getSimpleForecastDateTimeZoneShort() {
        String[] timeZone = new String[10];
        for (int i = 0; i < 10; i++) {
            timeZone[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("tz_short").getAsString();
        }
        return timeZone;
    }

    public String[] getSimpleForecastDateTimeZoneLong() {
        String[] timeZone = new String[10];
        for (int i = 0; i < 10; i++) {
            timeZone[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("date").getAsJsonObject()
                    .get("tz_long").getAsString();
        }
        return timeZone;
    }

    public String[] getSimpleForecastPeriod() {
        String[] period = new String[10];
        for (int i = 0; i < 10; i++) {
            period[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("period").getAsString();
        }
        return period;
    }

    public String[] getSimpleForecastHighTempsF() {
        String[] highTempsF = new String[10];
        for (int i = 0; i < 10; i++) {
            highTempsF[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("high").getAsJsonObject()
                    .get("fahrenheit").getAsString();
        }
        return highTempsF;
    }

    public String[] getSimpleForecastHighTempsC() {
        String[] highTempsC = new String[10];
        for (int i = 0; i < 10; i++) {
            highTempsC[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("high").getAsJsonObject()
                    .get("celsius").getAsString();
        }
        return highTempsC;
    }

    public String[] getSimpleForecastLowTempsF() {
        String[] lowTempsF = new String[10];
        for (int i = 0; i < 10; i++) {
            lowTempsF[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("low").getAsJsonObject()
                    .get("fahrenheit").getAsString();
        }
        return lowTempsF;
    }

    public String[] getSimpleForecastLowTempsC() {
        String[] lowTempsC = new String[10];
        for (int i = 0; i < 10; i++) {
            lowTempsC[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("low").getAsJsonObject()
                    .get("celsius").getAsString();
        }
        return lowTempsC;
    }

    public String[] getSimpleForecastConditions() {
        String[] forecastConditions = new String[10];
        for (int i = 0; i < 10; i++) {
            forecastConditions[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("conditions").getAsString();
        }
        return forecastConditions;
    }

    public String[] getSimpleForecastIcon() {
        String[] forecastIcons = new String[10];
        for (int i = 0; i < 10; i++) {
            forecastIcons[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("icon").getAsString();
        }
        return forecastIcons;
    }

    public String[] getSimpleForecastIconURL() {
        String[] iconURL = new String[10];
        for (int i = 0; i < 10; i++) {
            iconURL[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("icon_url").getAsString();
        }
        return iconURL;
    }

    public String[] getSimpleForecastSkyIcon() {
        String[] skyIcon = new String[10];
        for (int i = 0; i < 10; i++) {
            skyIcon[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("skyicon").getAsString();
        }
        return skyIcon;
    }

    public String[] getSimpleForecastPop() {
        String[] pop = new String[10];
        for (int i = 0; i < 10; i++) {
            pop[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("pop").getAsString();
        }
        return pop;
    }

    public int[] getSimpleForecastQPFAllDayIN() {
        int[] qpfAllDayIn = new int[10];
        for (int i = 0; i < 10; i++) {
            qpfAllDayIn[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("qpf_allday").getAsJsonObject()
                    .get("in").getAsInt();
        }
        return qpfAllDayIn;
    }

    public int[] getSimpleForecastQPFAllDayMM() {
        int[] qpfAllDayMM = new int[10];
        for (int i = 0; i < 10; i++) {
            qpfAllDayMM[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("qpf_allday").getAsJsonObject()
                    .get("mm").getAsInt();
        }
        return qpfAllDayMM;
    }

    public int[] getSimpleForecastQPFDayIN() {
        int[] qpfDayIn = new int[10];
        for (int i = 0; i < 10; i++) {
            qpfDayIn[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("qpf_day").getAsJsonObject()
                    .get("in").getAsInt();
        }
        return qpfDayIn;
    }

    public int[] getSimpleForecastQPFDayMM() {
        int[] qpfDayMM = new int[10];
        for (int i = 0; i < 10; i++) {
            qpfDayMM[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("qpf_day").getAsJsonObject()
                    .get("mm").getAsInt();
        }
        return qpfDayMM;
    }

    public int[] getSimpleForecastQPFNightIN() {
        int[] qpfNightIN = new int[10];
        for (int i = 0; i < 10; i++) {
            qpfNightIN[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("qpf_night").getAsJsonObject()
                    .get("in").getAsInt();
        }
        return qpfNightIN;
    }

    public int[] getSimpleForecastQPFNigjtMM() {
        int[] qpfNightMM = new int[10];
        for (int i = 0; i < 10; i++) {
            qpfNightMM[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("qpf_night").getAsJsonObject()
                    .get("mm").getAsInt();
        }
        return qpfNightMM;
    }

    public int[] getSimpleForecastSnowAllDayIN() {
        int[] snowAllDayIN = new int[10];
        for (int i = 0; i < 10; i++) {
            snowAllDayIN[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("snow_allday").getAsJsonObject()
                    .get("in").getAsInt();
        }
        return snowAllDayIN;
    }

    public int[] getSimpleForecastSnowAllDayCM() {
        int[] snowAllDayCM = new int[10];
        for (int i = 0; i < 10; i++) {
            snowAllDayCM[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("snow_allday").getAsJsonObject()
                    .get("cm").getAsInt();
        }
        return snowAllDayCM;
    }

    public int[] getSimpleForecastSnowDayIN() {
        int[] snowNightIn = new int[10];
        for (int i = 0; i < 10; i++) {
            snowNightIn[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("snow_day").getAsJsonObject()
                    .get("in").getAsInt();
        }
        return snowNightIn;
    }

    public int[] getSimpleForecastSnowDayCM() {
        int[] snowNightCM = new int[10];
        for (int i = 0; i < 10; i++) {
            snowNightCM[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("snow_day").getAsJsonObject()
                    .get("cm").getAsInt();
        }
        return snowNightCM;
    }

    public int[] getSimpleForecastSnowNightIN() {
        int[] snowNightIn = new int[10];
        for (int i = 0; i < 10; i++) {
            snowNightIn[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("snow_night").getAsJsonObject()
                    .get("in").getAsInt();
        }
        return snowNightIn;
    }

    public int[] getSimpleForecastSnowNightCM() {
        int[] snowNightCM = new int[10];
        for (int i = 0; i < 10; i++) {
            snowNightCM[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("snow_night").getAsJsonObject()
                    .get("cm").getAsInt();
        }
        return snowNightCM;
    }

    public int[] getSimpleForecastMaxWindMPH() {
        int[] maxWindMPH = new int[10];
        for (int i = 0; i < 10; i++) {
            maxWindMPH[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("maxwind").getAsJsonObject()
                    .get("mph").getAsInt();
        }
        return maxWindMPH;
    }

    public int[] getSimpleForecastMaxWindKPH() {
        int[] maxWindKPH = new int[10];
        for (int i = 0; i < 10; i++) {
            maxWindKPH[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("maxwind").getAsJsonObject()
                    .get("kph").getAsInt();
        }
        return maxWindKPH;
    }

    public String[] getSimpleForecastMaxWindDirection() {
        String[] maxWindDirection = new String[10];
        for (int i = 0; i < 10; i++) {
            maxWindDirection[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("maxwind").getAsJsonObject()
                    .get("dir").getAsString();
        }
        return maxWindDirection;
    }

    public int[] getSimpleForecastMaxWindDegrees() {
        int[] maxWindDegrees = new int[10];
        for (int i = 0; i < 10; i++) {
            maxWindDegrees[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("maxwind").getAsJsonObject()
                    .get("degrees").getAsInt();
        }
        return maxWindDegrees;
    }

    public int[] getSimpleForecastAveWindMPH() {
        int[] aveWindMPH = new int[10];
        for (int i = 0; i < 10; i++) {
            aveWindMPH[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("avewind").getAsJsonObject()
                    .get("mph").getAsInt();
        }
        return aveWindMPH;
    }

    public int[] getSimpleForecastAveWindKPH() {
        int[] aveWindKPH = new int[10];
        for (int i = 0; i < 10; i++) {
            aveWindKPH[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("avewind").getAsJsonObject()
                    .get("kph").getAsInt();
        }
        return aveWindKPH;
    }

    public String[] getSimpleForecastAveWindDirection() {
        String[] aveWindDirection = new String[10];
        for (int i = 0; i < 10; i++) {
            aveWindDirection[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("avewind").getAsJsonObject()
                    .get("dir").getAsString();
        }
        return aveWindDirection;
    }

    public int[] getSimpleForecastAveWindDegrees() {
        int[] aveWindDegrees = new int[10];
        for (int i = 0; i < 10; i++) {
            aveWindDegrees[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("avewind").getAsJsonObject()
                    .get("degrees").getAsInt();
        }
        return aveWindDegrees;
    }

    public int[] getSimpleForecastAveHumidity() {
        int[] aveHumidity = new int[10];
        for (int i = 0; i < 10; i++) {
            aveHumidity[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("avehumidity").getAsInt();
        }
        return aveHumidity;
    }

    public int[] getSimpleForecastMaxHumidity() {
        int[] maxHumidity = new int[10];
        for (int i = 0; i < 10; i++) {
            maxHumidity[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("maxhumidity").getAsInt();
        }
        return maxHumidity;
    }

    public int[] getSimpleForecastMinHumidity() {
        int[] minHumidity = new int[10];
        for (int i = 0; i < 10; i++) {
            minHumidity[i] = jse.getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("simpleforecast").getAsJsonObject()
                    .get("forecastday").getAsJsonArray()
                    .get(i).getAsJsonObject()
                    .get("minhumidity").getAsInt();
        }
        return minHumidity;
    }

    /*
     * *********************************************************************************************
     *
     * Methods for hourly forecast information
     *
     * *********************************************************************************************
     */

    public String[] getHourlyForecastFCTTIMEHour() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("hour").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEHourPadded() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("hour_padded").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMinute() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("min").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMinuteUnpadded() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("min_unpadded").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMESecond() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("sec").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEYear() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("year").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMonth() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("mon").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMonthPadded() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("mon_padded").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMonthAbbrev() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("mon_abbrev").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMonthDay() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("mday").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMonthDayPadded() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("mday_padded").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEYearDay() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("yday").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEIsDST() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("isdst").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEEpoch() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("epoch").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEPretty() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("pretty").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMECivil() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("civil").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMonthName() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("month_name").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEMonthNameAbbrev() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("month_name_abbrev").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEWeekdayName() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("weekday_name").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEWeekdayNameNight() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("weekday_name_night").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEWeekdayNameAbbrev() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("weekday_name_abbrev").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEWeekdayNameUnlang() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("weekday_name_unlang").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEWeekdayNameNightUnlang() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("weekday_name_night_unlang").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEAMPM() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("ampm").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMETimeZone() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("tz").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEAge() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("age").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTTIMEUTCDate() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("FCTTIME").getAsJsonObject().get("UTCDATE").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastTempF() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("temp").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastTempC() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("temp").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastDewpointF() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("dewpoint").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastDewpointC() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("dewpoint").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastCondition() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("condition").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastIcon() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("icon").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastIconURL() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("icon_url").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFCTCode() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("fctcode").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastSky() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("sky").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastWindSpeedEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("wspd").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastWindSpeedMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("wspd").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastWindDirectionEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("wdir").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastWindDirectionMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("wdir").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastWX() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("wx").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastUVIndex() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("uvi").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastHumidity() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("humidity").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastWindChillEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("windchill").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastWindChillMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("windchill").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastHeatIndexEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("heatindex").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastHeatIndexMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("heatindex").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFeelsLikeEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("feelslike").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastFeelsLikeMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("feelslike").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastQPFEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("qpf").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastQPFMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("qpf").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastSnowEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("snow").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastSnowMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("snow").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastPop() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("pop").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastMSLPEnglish() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("mslp").getAsJsonObject().get("english").getAsString();
        }
        return returnValue;
    }

    public String[] getHourlyForecastMSLPMetric() {
        String[] returnValue = new String[36];
        for (int i = 0; i < returnValue.length; i++) {
            returnValue[i] = jse.getAsJsonObject().get("hourly_forecast").getAsJsonArray().get(i).getAsJsonObject().get("mslp").getAsJsonObject().get("metric").getAsString();
        }
        return returnValue;
    }
}