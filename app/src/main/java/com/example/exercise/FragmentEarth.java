package com.example.exercise;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

//import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FragmentEarth extends Fragment {

    TextView earthDateTextView;
    TextView earthMaxTempView;
    TextView earthMinTempView;
    //String worldWeatherMapApiKey = "f7ba5036adfc078bc9d35926eb3b86ca";
    //String earthGeocodingApi = "http://api.openweathermap.org/geo/1.0/direct?q=Seoul&limit=5&appid=f7ba5036adfc078bc9d35926eb3b86ca";
    //Latitude = 37.532600, Longitude: 127.024612
    String earthApiURL = "https://api.openweathermap.org/data/2.5/weather?lat=37.5666791&lon=126.9782914&appid=f7ba5036adfc078bc9d35926eb3b86ca";

    public static FragmentEarth newInstance(int number) {
        FragmentEarth fragment_e = new FragmentEarth();
        Bundle bundle = new Bundle();
        bundle.putInt("number", number);
        fragment_e.setArguments(bundle);
        return fragment_e;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v2 = inflater.inflate(R.layout.fragment_earth, container, false);

        earthDateTextView = v2.findViewById(R.id.EarthDateText);
        earthMaxTempView = v2.findViewById(R.id.EarthMaxTemp);
        earthMinTempView = v2.findViewById(R.id.EarthMinTemp);
        //loading API


        new Thread(() -> {
            getJSON();
        }).start();

        return v2;
    }

    private void getJSON() {

        try {
            //Loading api
            URL url = new URL(earthApiURL);
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

            String earthData = response.toString();
            Log.d("data", earthData);
            JSONObject earthJsonObj = new JSONObject(earthData);
            JSONObject earthJsonMainObj = (JSONObject) earthJsonObj.get("main");
            double earthMaxTemp = earthJsonMainObj.getDouble("temp_max") - 273.15;
            double earthMinTemp = earthJsonMainObj.getDouble("temp_min")  - 273.15;

            earthMaxTemp = Math.round(earthMaxTemp * 10) / 10.0;
            earthMinTemp = Math.round(earthMinTemp * 10) / 10.0;
            String earthMaxTempStr = Double.toString(earthMaxTemp);
            String earthMinTempStr = Double.toString(earthMinTemp);
            earthMaxTempView.setText(earthMaxTempStr + "°");
            earthMinTempView.setText(earthMinTempStr + "°");

        } catch  (Exception e) {
            e.printStackTrace();
        }


    }
}