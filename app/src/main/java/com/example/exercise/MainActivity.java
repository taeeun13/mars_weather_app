package com.example.exercise;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView marsMaxTempView;
    private TextView marsMinTempView;

    private final String apiURL = "https://mars.nasa.gov/rss/api/?feed=weather&category=msl&feedtype=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.MarsDateText);
        marsMaxTempView = findViewById(R.id.MarsMaxTemp);
        marsMinTempView = findViewById(R.id.MarsMinTemp);

        new Thread(() -> {
            getJSON();
        }).start();
    }

    public void getJSON(){
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input;
            StringBuilder response = new StringBuilder();
            while ((input = br.readLine()) != null) {
                response.append(input);
            }
            br.close();

            String data = response.toString();
            Log.d("data", data);
            JSONObject jsonObj = new JSONObject(data);
            JSONArray jsonArr = (JSONArray) jsonObj.get("soles");
            Log.d("data",jsonArr.getString(0));
            JSONObject object = jsonArr.getJSONObject(0);
            String date = object.getString("terrestrial_date");
            String marsMaxTempStr = object.getString("max_temp");
            String marsMinTempStr = object.getString("min_temp");
            Log.d("data", date);
            mTextView.setText(date);
            marsMaxTempView.setText(marsMaxTempStr);
            marsMinTempView.setText(marsMinTempStr);
        } catch  (Exception e) {
            e.printStackTrace();
        }
    }

}


