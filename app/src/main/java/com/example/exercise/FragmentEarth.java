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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
public class FragmentEarth extends Fragment {

    //******************************Declare TextViews***********************************
    //**Date**
    TextView earthDateTextView;

    //**Today**
    TextView earthMaxTempView;
    TextView earthMinTempView;

    //**Forecast**

    //Notation
    //eF == earth Forecast
    //d0 == d+0, d1 == d+1, d2 == d+2, ...

    //*MaxTemp*
    TextView eFd0MaxTempView;
    TextView eFd1MaxTempView;
    TextView eFd2MaxTempView;
    TextView eFd3MaxTempView;
    TextView eFd4MaxTempView;
    TextView eFd5MaxTempView;
    TextView eFd6MaxTempView;

    //*MinTemp
    TextView eFd0MinTempView;
    TextView eFd1MinTempView;
    TextView eFd2MinTempView;
    TextView eFd3MinTempView;
    TextView eFd4MinTempView;
    TextView eFd5MinTempView;
    TextView eFd6MinTempView;


    //******************************Declare APIs***********************************
    //String worldWeatherMapApiKey = "f7ba5036adfc078bc9d35926eb3b86ca";
    //String earthGeocodingApi = "http://api.openweathermap.org/geo/1.0/direct?q=Seoul&limit=5&appid=f7ba5036adfc078bc9d35926eb3b86ca";
    //Latitude = 37.532600, Longitude: 127.024612
    String earthApiTodayURL = "https://api.openweathermap.org/data/2.5/weather?lat=37.5666791&lon=126.9782914&appid=f7ba5036adfc078bc9d35926eb3b86ca";
    String earthApiForecastURL = "https://api.openweathermap.org/data/2.5/onecall?lat=37.5666791&lon=126.9782914&exclude=hourly,minutely&appid=f7ba5036adfc078bc9d35926eb3b86ca";

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

        //**date**
        earthDateTextView = v2.findViewById(R.id.EarthDateText);

        //**Today**
        earthMaxTempView = v2.findViewById(R.id.EarthMaxTemp);
        earthMinTempView = v2.findViewById(R.id.EarthMinTemp);

        //**Forecast Max Temperature**
        eFd0MaxTempView = v2.findViewById(R.id.d0MaxTemp);
        eFd1MaxTempView = v2.findViewById(R.id.d1MaxTemp);
        eFd2MaxTempView = v2.findViewById(R.id.d2MaxTemp);
        eFd3MaxTempView = v2.findViewById(R.id.d3MaxTemp);
        eFd4MaxTempView = v2.findViewById(R.id.d4MaxTemp);
        eFd5MaxTempView = v2.findViewById(R.id.d5MaxTemp);
        eFd6MaxTempView = v2.findViewById(R.id.d6MaxTemp);

        //**Forecast Min Temperature**
        eFd0MinTempView = v2.findViewById(R.id.d0MinTemp);
        eFd1MinTempView = v2.findViewById(R.id.d1MinTemp);
        eFd2MinTempView = v2.findViewById(R.id.d2MinTemp);
        eFd3MinTempView = v2.findViewById(R.id.d3MinTemp);
        eFd4MinTempView = v2.findViewById(R.id.d4MinTemp);
        eFd5MinTempView = v2.findViewById(R.id.d5MinTemp);
        eFd6MinTempView = v2.findViewById(R.id.d6MinTemp);

        getDate();

        new Thread(() -> {
            getJSON();
        }).start();

        return v2;
    }

    private void getDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        earthDateTextView.setText(dFormat.format(date));
    }

    //******************************Loading Earth Api***********************************
    private void getJSON() {
        //******************************Today***********************************
        try {
            URL URL = new URL(earthApiTodayURL);
            HttpURLConnection con = (HttpURLConnection)URL.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input;
            StringBuilder response = new StringBuilder();
            while ((input = br.readLine()) != null) {
                response.append(input);
            }
            br.close();

            //MAX&MIN Temperature
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
            eFd0MaxTempView.setText(earthMaxTempStr + "°");
            eFd0MinTempView.setText(earthMaxTempStr + "°");

        } catch  (Exception e) {
            e.printStackTrace();
        }

        //******************************Forecast***********************************
        try {
            URL URL = new URL(earthApiForecastURL);
            HttpURLConnection con = (HttpURLConnection)URL.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String input;
            StringBuilder response = new StringBuilder();
            while ((input = br.readLine()) != null) {
                response.append(input);
            }
            br.close();

            //MAX&MIN Temperature
            String Data = response.toString();
            Log.d("data", Data);
            JSONObject jsonObj = new JSONObject(Data);
            JSONArray jsonDailyArr = (JSONArray) jsonObj.get("daily");
            Log.d("data",jsonDailyArr.getString(1));
            JSONObject jsonDailyObj = jsonDailyArr.getJSONObject(1); //
            JSONObject jsonTempObj = (JSONObject) jsonDailyObj.get("temp");
            double earthMaxTemp = jsonTempObj.getDouble("max") - 273.15;
            double earthMinTemp = jsonTempObj.getDouble("min") - 273.15;

            earthMaxTemp = Math.round(earthMaxTemp * 10) / 10.0;
            earthMinTemp = Math.round(earthMinTemp * 10) / 10.0;
            String earthMaxTempStr = Double.toString(earthMaxTemp);
            String earthMinTempStr = Double.toString(earthMinTemp);
            eFd1MaxTempView.setText(earthMaxTempStr + "°");
            //earthMinTempView.setText(earthMinTempStr + "°");

        } catch  (Exception e) {
            e.printStackTrace();
        }

    }
}