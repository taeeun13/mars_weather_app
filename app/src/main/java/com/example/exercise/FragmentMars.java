package com.example.exercise;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;



import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.model.GradientColor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


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

    //**BarChart**
    BarChart mFBarChart;


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

        //**Bar Chart**
        mFBarChart = v.findViewById(R.id.mBarChart);

        new Thread(this::getJSON).start();

        Button button = (Button) v.findViewById(R.id.infoBtn);
        try {
            button.setOnClickListener(new MyOnClickListener());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return v;
    }

    public void show(String curiosityPicLink) throws IOException {
        Log.d("link", curiosityPicLink);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ImageView image = new ImageView(getActivity());
        Glide.with(getActivity()).load(curiosityPicLink).override(900, 900).into(image);

        builder.setView(image);

        builder.setTitle("Information");
        builder.setMessage(Html.fromHtml("<p>\nInstrument: Mars Curiosity Rover\n\nCurrent Location:\n<a href=\"https://mars.nasa.gov/maps/location/?mission=Curiosity\">Click here</a></p><p>\nLatest photo from curiosity rover:</p>"));
        builder.setNeutralButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.show();
        ((TextView)alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
    }


    public class MyOnClickListener implements View.OnClickListener {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> getCuriosityPic = new Callable<String>() {
            @Override
            public String call() throws IOException, JSONException {
                String text = null;

                URL url = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/latest_photos?api_key=KBKBygrnyM3Q4Rk5Rxg4hroSx0V2XLdJDupa8MkJ");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
                JSONArray jsonArr = (JSONArray) jsonObj.get("latest_photos");
                JSONObject object = jsonArr.getJSONObject(0);
                text = object.getString("img_src");

                //Log.d("link", text);
                return text;
            }
        };

        Future<String> future = executor.submit(getCuriosityPic);
        String pic = future.get(); // use future

        public MyOnClickListener() throws ExecutionException, InterruptedException {
        }

        @Override
        public void onClick(View v) {
            try {
                show(pic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void getJSON() {
        try {

            //Loading API
            URL url = new URL(marsApiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
            Log.d("data", jsonArr.getString(0));
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

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    marsDateTextView.setText(date);
                    marsMaxTempView.setText(marsMaxTempStr + "°C");
                    marsMinTempView.setText(marsMinTempStr + "°C");
                    marsPressureView.setText(marsPressure + "Pa");
                    marsSunriseView.setText(marsSunrise);
                    marsSunsetView.setText(marsSunset);
                    marsUVView.setText(marsUV);
                }
            });

            ArrayList<TextView> mFMaxTempArr = new ArrayList<>(
                    Arrays.asList(mFd0MaxTempView, mFd1MaxTempView, mFd2MaxTempView, mFd3MaxTempView, mFd4MaxTempView, mFd5MaxTempView, mFd6MaxTempView)
            );
            ArrayList<TextView> mFMinTempArr = new ArrayList<>(
                    Arrays.asList(mFd0MinTempView, mFd1MinTempView, mFd2MinTempView, mFd3MinTempView, mFd4MinTempView, mFd5MinTempView, mFd6MinTempView)
            );

            ArrayList<TextView> mFDateArr = new ArrayList<>(
                    Arrays.asList(mFd0DateView, mFd1DateView, mFd2DateView, mFd3DateView, mFd4DateView, mFd5DateView, mFd6DateView)
            );
            ArrayList<String> maxTempArr = new ArrayList<>();
            ArrayList<String> minTempArr = new ArrayList<>();
            ArrayList<String> fDateArr = new ArrayList<>();

            for (int i = 0; i <= 6; i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                String maxTemp = obj.getString("max_temp");
                String minTemp = obj.getString("min_temp");
                String fDate = obj.getString("terrestrial_date").substring(5);
                maxTempArr.add(maxTemp);
                minTempArr.add(minTemp);
                fDateArr.add(fDate);
            }


            new Thread(() -> {
                ArrayList<Float> minTempFloatArr = new ArrayList<>();
                ArrayList<Float> maxTempFloatArr = new ArrayList<>();

                float minTempTot = 3000.F;
                float maxTempTot = -2000.F;
                for (int i=0; i<=6; i++){
                    float minTempDay = Float.parseFloat(minTempArr.get(i));
                    float maxTempDay = Float.parseFloat(maxTempArr.get(i));
                    if (minTempDay < minTempTot) minTempTot = minTempDay;
                    if (maxTempDay > maxTempTot) maxTempTot = maxTempDay;
                    minTempFloatArr.add(minTempDay);
                    maxTempFloatArr.add(maxTempDay);
                }
                List<BarEntry> entries = new ArrayList<>();

                for (int i=0; i<=6; i++){
                    float y1 = maxTempTot - maxTempFloatArr.get(i);
                    float y2 = maxTempFloatArr.get(i) - minTempFloatArr.get(i);
                    float y3 = minTempFloatArr.get(i) - minTempTot;
                    entries.add(new BarEntry(6-i, new float[] {y3, y2, y1}));
                }

                BarDataSet bSet = new BarDataSet(entries, " ");
                bSet.setDrawValues(false);
                BarData bData = new BarData(bSet);
                List<GradientColor> gradientColors = new ArrayList<>();
                gradientColors.add(new GradientColor(Color.parseColor("#D1D1D1"), Color.parseColor("#D1D1D1")));
                gradientColors.add(new GradientColor(Color.parseColor("#A5D48A"), Color.parseColor("#F59056")));
                gradientColors.add(new GradientColor(Color.parseColor("#D1D1D1"), Color.parseColor("#D1D1D1")));
                bSet.setGradientColors(gradientColors);
                bData.setBarWidth(0.4f);

                mFBarChart.setData(bData);
                mFBarChart.invalidate();
                mFBarChart.setDescription(null);
                mFBarChart.getXAxis().setEnabled(false);
                mFBarChart.getLegend().setEnabled(false);
                
                YAxis left = mFBarChart.getAxisLeft();
                left.setDrawLabels(false); // no axis labels
                left.setDrawAxisLine(false); // no axis line
                left.setDrawGridLines(false); // no grid lines
                YAxis right = mFBarChart.getAxisRight();
                right.setEnabled(false);
                right.setDrawLabels(false); // no axis labels
                right.setDrawAxisLine(false); // no axis line
                right.setDrawGridLines(false); // no grid lines
            }).start();


            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <= 6; i++) {
                        mFMaxTempArr.get(i).setText(maxTempArr.get(i) + "°");
                        mFMinTempArr.get(i).setText(minTempArr.get(i) + "°");
                        mFDateArr.get(i).setText(fDateArr.get(i));
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}