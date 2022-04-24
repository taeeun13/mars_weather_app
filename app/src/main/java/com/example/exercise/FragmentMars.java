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
import java.util.ArrayList;
import java.util.Arrays;


public class FragmentMars extends Fragment {
    TextView marsDateTextView;
    TextView marsMaxTempView;
    TextView marsMinTempView;
    String marsApiURL = "https://mars.nasa.gov/rss/api/?feed=weather&category=msl&feedtype=json";

    TextView marsPressureView;
    TextView marsSunriseView;
    TextView marsSunsetView;
    TextView marsUVView;
    TextView mFd0MaxTempView;
    TextView mFd1MaxTempView;
    TextView mFd2MaxTempView;
    TextView mFd3MaxTempView;
    TextView mFd4MaxTempView;
    TextView mFd5MaxTempView;
    TextView mFd6MaxTempView;

    //**MinTemp**
    TextView mFd0MinTempView;
    TextView mFd1MinTempView;
    TextView mFd2MinTempView;
    TextView mFd3MinTempView;
    TextView mFd4MinTempView;
    TextView mFd5MinTempView;
    TextView mFd6MinTempView;

    //Date
    TextView mFd0DateView;
    TextView mFd1DateView;
    TextView mFd2DateView;
    TextView mFd3DateView;
    TextView mFd4DateView;
    TextView mFd5DateView;
    TextView mFd6DateView;


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

        mFd0MaxTempView = v.findViewById(R.id.md0MaxTemp);
        mFd1MaxTempView = v.findViewById(R.id.md1MaxTemp);
        mFd2MaxTempView = v.findViewById(R.id.md2MaxTemp);
        mFd3MaxTempView = v.findViewById(R.id.md3MaxTemp);
        mFd4MaxTempView = v.findViewById(R.id.md4MaxTemp);
        mFd5MaxTempView = v.findViewById(R.id.md5MaxTemp);
        mFd6MaxTempView = v.findViewById(R.id.md6MaxTemp);

        mFd0MinTempView = v.findViewById(R.id.md0MinTemp);
        mFd1MinTempView = v.findViewById(R.id.md1MinTemp);
        mFd2MinTempView = v.findViewById(R.id.md2MinTemp);
        mFd3MinTempView = v.findViewById(R.id.md3MinTemp);
        mFd4MinTempView = v.findViewById(R.id.md4MinTemp);
        mFd5MinTempView = v.findViewById(R.id.md5MinTemp);
        mFd6MinTempView = v.findViewById(R.id.md6MinTemp);

        mFd0DateView = v.findViewById(R.id.md0Date);
        mFd1DateView = v.findViewById(R.id.md1Date);
        mFd2DateView = v.findViewById(R.id.md2Date);
        mFd3DateView = v.findViewById(R.id.md3Date);
        mFd4DateView = v.findViewById(R.id.md4Date);
        mFd5DateView = v.findViewById(R.id.md5Date);
        mFd6DateView = v.findViewById(R.id.md6Date);

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

            ArrayList<TextView> mFMaxTempArr = new ArrayList<>(
                    Arrays.asList(mFd0MaxTempView, mFd1MaxTempView, mFd2MaxTempView, mFd3MaxTempView, mFd4MaxTempView, mFd5MaxTempView, mFd6MaxTempView)
            );
            ArrayList<TextView> mFMinTempArr = new ArrayList<>(
                    Arrays.asList(mFd0MinTempView, mFd1MinTempView, mFd2MinTempView, mFd3MinTempView, mFd4MinTempView, mFd5MinTempView, mFd6MinTempView)
            );

            ArrayList<TextView> mFDateArr = new ArrayList<>(
                    Arrays.asList(mFd0DateView, mFd1DateView, mFd2DateView, mFd3DateView, mFd4DateView, mFd5DateView, mFd6DateView)
            );


            for (int i = 0; i <= 6; i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                String maxTemp = obj.getString("max_temp");
                String minTemp = obj.getString("min_temp");
                String fdate = obj.getString("terrestrial_date").substring(5);
                mFMaxTempArr.get(i).setText(maxTemp + "°");
                mFMinTempArr.get(i).setText(minTemp + "°");
                mFDateArr.get(i).setText(fdate);
            }

        } catch  (Exception e) {
            e.printStackTrace();
        }
    }
}