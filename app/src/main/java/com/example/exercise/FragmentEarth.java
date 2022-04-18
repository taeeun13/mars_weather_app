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
    String worldWeatherMapApiKey = "f7ba5036adfc078bc9d35926eb3b86ca";
    String earthGeocodingApi = "http://api.openweathermap.org/geo/1.0/direct?q=Seoul&limit=5&appid=f7ba5036adfc078bc9d35926eb3b86ca";
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

            //Get Data
//            String data = response.toString();
//            Log.d("data", data);
//            JSONObject jsonObj = new JSONObject(data);
//            JSONArray jsonArr = (JSONArray) jsonObj.get("main");
//            Log.d("data",jsonArr.getString(0));
//            JSONObject object = jsonArr.getJSONObject(0);
//            String date = object.getString("terrestrial_date");
//            String marsMaxTempStr = object.getString("max_temp");
//            String marsMinTempStr = object.getString("min_temp");
//            Log.d("data", date);
            String earthData = response.toString();
            Log.d("data", earthData);
            JSONObject earthJsonObj = new JSONObject(earthData);
            JSONObject earthJsonMainObj = (JSONObject) earthJsonObj.get("main");
            String earthMaxTempStr = earthJsonMainObj.getString("temp_max");
            String earthMinTempStr = earthJsonMainObj.getString("temp_min");

            earthMaxTempView.setText(earthMaxTempStr);
            earthMinTempView.setText(earthMinTempStr);

        } catch  (Exception e) {
            e.printStackTrace();
        }


    }
}