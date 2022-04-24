package com.example.exercise;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class FragmentMars extends Fragment {
    TextView marsDateTextView;
    TextView marsMaxTempView;
    TextView marsMinTempView;
    String marsApiURL = "https://mars.nasa.gov/rss/api/?feed=weather&category=msl&feedtype=json";

    TextView marsPressureView;
    TextView marsSunriseView;
    TextView marsSunsetView;
    TextView marsUVView;

    public static FragmentMars newInstance(int number) {
        FragmentMars fragment1 = new FragmentMars();
        Bundle bundle = new Bundle();
        bundle.putInt("number", number);
        fragment1.setArguments(bundle);
        return fragment1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mars, container, false);

        marsDateTextView = v.findViewById(R.id.MarsDateText);
        marsMaxTempView = v.findViewById(R.id.MarsMaxTemp);
        marsMinTempView = v.findViewById(R.id.MarsMinTemp);
        marsPressureView = v.findViewById(R.id.MarsPressure);
        marsSunriseView = v.findViewById(R.id.MarsSunrise);
        marsSunsetView = v.findViewById(R.id.MarsSunset);
        marsUVView = v.findViewById(R.id.MarsUV);

        new Thread(() -> {
            getJSON();
        }).start();

        return v;
    }

    public void getJSON(){
        try {

            //Loading API
            URL url = new URL(marsApiURL);
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
            String data = response.toString();
            Log.d("data", data);
            JSONObject jsonObj = new JSONObject(data);
            JSONArray jsonArr = (JSONArray) jsonObj.get("soles");
            Log.d("data",jsonArr.getString(0));
            JSONObject object = jsonArr.getJSONObject(0);
            String date = object.getString("terrestrial_date");
            String marsMaxTempStr = object.getString("max_temp");
            String marsMinTempStr = object.getString("min_temp");
            String marsPressure = object.getString("pressure");
            String marsSunrise = object.getString("sunrise");
            String marsSunset = object.getString("sunset");
            String marsUV = object.getString("local_uv_irradiance_index");
            Log.d("data", date);

            //setText
            marsDateTextView.setText(date);
            marsMaxTempView.setText(marsMaxTempStr + "°");
            marsMinTempView.setText(marsMinTempStr + "°");
            marsPressureView.setText(marsPressure);
            marsSunriseView.setText(marsSunrise);
            marsSunsetView.setText(marsSunset);
            marsUVView.setText(marsUV);

        } catch  (Exception e) {
            e.printStackTrace();
        }
    }
}