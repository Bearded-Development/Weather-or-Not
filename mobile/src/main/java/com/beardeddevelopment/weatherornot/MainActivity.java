package com.beardeddevelopment.weatherornot;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {
    Model model;
    private final int PREVIEWFORECASTLENGTH = 6;
    // Conditions preview elements
    EditText previewLocation;
    Button goButton;
    TextView previewConditions, previewTemps;
    ImageView previewIcon;
    // Forecast preview elements
    TextView[] previewForecastDay, previewForecastTemps, previewForecastPrecip;
    ImageView[] previewForecastIcons;
    boolean tempF = true;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        goButton = (Button) findViewById(R.id.goButton);


        previewForecastDay = new TextView[PREVIEWFORECASTLENGTH];
        previewForecastTemps = new TextView[PREVIEWFORECASTLENGTH];
        previewForecastPrecip = new TextView[PREVIEWFORECASTLENGTH];
        previewForecastIcons = new ImageView[PREVIEWFORECASTLENGTH];

        previewConditions = (TextView) findViewById(R.id.preview_conditions);
        previewTemps = (TextView) findViewById(R.id.preview_temperature);
        previewLocation = (EditText) findViewById(R.id.preview_location);
        previewLocation.setText("autoip");
        previewIcon = (ImageView) findViewById(R.id.preview_icon_big);

        previewForecastDay[0] = (TextView) findViewById(R.id.preview_forecast_day_0);
        previewForecastDay[1] = (TextView) findViewById(R.id.preview_forecast_day_1);
        previewForecastDay[2] = (TextView) findViewById(R.id.preview_forecast_day_2);
        previewForecastDay[3] = (TextView) findViewById(R.id.preview_forecast_day_3);
        previewForecastDay[4] = (TextView) findViewById(R.id.preview_forecast_day_4);
        previewForecastDay[5] = (TextView) findViewById(R.id.preview_forecast_day_5);

        previewForecastTemps[0] = (TextView) findViewById(R.id.preview_forecast_temps_0);
        previewForecastTemps[1] = (TextView) findViewById(R.id.preview_forecast_temps_1);
        previewForecastTemps[2] = (TextView) findViewById(R.id.preview_forecast_temps_2);
        previewForecastTemps[3] = (TextView) findViewById(R.id.preview_forecast_temps_3);
        previewForecastTemps[4] = (TextView) findViewById(R.id.preview_forecast_temps_4);
        previewForecastTemps[5] = (TextView) findViewById(R.id.preview_forecast_temps_5);

        previewForecastPrecip[0] = (TextView) findViewById(R.id.preview_forecast_chance_rain_0);
        previewForecastPrecip[1] = (TextView) findViewById(R.id.preview_forecast_chance_rain_1);
        previewForecastPrecip[2] = (TextView) findViewById(R.id.preview_forecast_chance_rain_2);
        previewForecastPrecip[3] = (TextView) findViewById(R.id.preview_forecast_chance_rain_3);
        previewForecastPrecip[4] = (TextView) findViewById(R.id.preview_forecast_chance_rain_4);
        previewForecastPrecip[5] = (TextView) findViewById(R.id.preview_forecast_chance_rain_5);

        // ImageViews
        previewIcon = (ImageView) findViewById(R.id.preview_icon_big);
        previewForecastIcons[0] = (ImageView) findViewById(R.id.preview_forecast_icon_0);
        previewForecastIcons[1] = (ImageView) findViewById(R.id.preview_forecast_icon_1);
        previewForecastIcons[2] = (ImageView) findViewById(R.id.preview_forecast_icon_2);
        previewForecastIcons[3] = (ImageView) findViewById(R.id.preview_forecast_icon_3);
        previewForecastIcons[4] = (ImageView) findViewById(R.id.preview_forecast_icon_4);
        previewForecastIcons[5] = (ImageView) findViewById(R.id.preview_forecast_icon_5);


        getWeather();
        goButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getWeather();
            }
        });
    }

    private void getWeather() {
        ModelAsyncTask modelAsyncTask = new ModelAsyncTask();
        modelAsyncTask.execute(previewLocation.getText().toString());
    }


    private class ModelAsyncTask extends AsyncTask<String, Void, Model> {

        @Override
        protected Model doInBackground(String... strings) {
            try {
                if (strings[0] != "") {
                    model = new Model(strings[0]);
                } else {
                    model = new Model();
                }
                return model;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Model model) {
            if (model != null) {
                refreshPreview(model);
            }
        }
    }

    public void refreshPreview(Model m) {
        previewLocation.setText(m.getDisplayLocationFull());
        previewConditions.setText(m.getConditionsWeather());
        previewTemps.setText(m.getConditionsTempString());
        for (int i = 0; i < 6; i++) {
            previewForecastDay[i].setText(m.getDailyForecastWeekdayShort()[i]);
            previewForecastPrecip[i].setText(m.getDailyForecastPOP()[i]);
            if (tempF) {
                previewForecastTemps[i].setText(m.getDailyForecastHighTempF()[i] + "/" + m.getDailyForecastLowTempF()[i]);
            } else if (!tempF) {
                previewForecastTemps[i].setText(m.getDailyForecastHighTempC()[i] + "/" + m.getDailyForecastLowTempC()[i]);
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Weather or Not")
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}