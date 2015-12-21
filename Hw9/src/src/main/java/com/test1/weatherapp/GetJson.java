package com.test1.weatherapp;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pankaj on 11/18/15.
 */
public class GetJson extends AsyncTask<Void, Void, Void> {

    private String street;
    private String city;
    private String state;
    private String degree;

    public GetJson(ArrayList al){
        this.street = (String) al.get(0);
        this.city = (String) al.get(1);
        this.state = (String) al.get(2);
        this.degree  = (String) al.get(3);


    }
    public String getJson(){
        String parsedString = "";

        List<HourlyDataModel> hourlyList = new ArrayList<HourlyDataModel>();
        List<DailyDataModel> dailyList = new ArrayList<DailyDataModel>();

        try {

           // "http://weatherreport-env.elasticbeanstalk.com/HW8/index.php?street"=street"&city="+la&state=CA&degree=us&action=test")

           // URL url = new URL("http://cs-server.usc.edu:45678/hw/hw6/sample_json_output.json");
            URL url = new URL("http://weatherreport-env.elasticbeanstalk.com/HW8/index.php?street=la&city=la&state=CA&degree=us&action=test");

        //    List params = new LinkedList();


            //JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
            URLConnection conn = url.openConnection();

            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");


            httpConn.connect();

            InputStream is = httpConn.getInputStream();
            parsedString = convertinputStreamToString(is);


            JSONObject object = (JSONObject) new JSONTokener(parsedString).nextValue();

           // json = json.replace("\\\"","'");
           // JSONObject jsono = new JSONObject(parsedString);

            JSONObject  jsono =(JSONObject) new JSONObject(object.getString("json"));


         //   JSONObject jsono = jsonov.getJSONObject()
            // For Current object:
            JSONObject curr =  jsono.getJSONObject("currently");
            CurrentDataModel cm = new CurrentDataModel();
            cm.setSummary(curr.getString("summary").toString());
            cm.setCloudCover(curr.getString("cloudCover"));
            cm.setDewPoint(curr.getString("dewPoint"));
            cm.setHumidity(curr.getString("humidity"));
            cm.setIcon(curr.getString("icon"));
            cm.setPrecipIntensity(curr.getString("precipIntensity"));
            cm.setPressure(curr.getString("pressure"));
            cm.setWindSpeed(curr.getString("windSpeed"));
            cm.setTemperature(curr.getString("temperature"));


            JSONObject hourly =  jsono.getJSONObject("hourly");
            JSONArray jArrayHourly = hourly.getJSONArray("data");


        /*    for (int h=0 ; h< jArrayHourly.length(); h++ ){
                JSONObject jh =  jArrayHourly.getJSONObject(h);
                HourlyDataModel hm = new HourlyDataModel();
                hm.setSummary(jh.getString("summary"));
                hm.setCloudCover(jh.getString("cloudCover"));
                hm.setDewPoint(jh.getString("dewPoint"));
                hm.setHumidity(jh.getString("humidity"));
                hm.setIcon(jh.getString("icon"));
                hm.setPrecipIntensity(jh.getString("precipIntensity"));
                hm.setPressure(jh.getString("pressure"));
                hm.setWindSpeed(jh.getString("windSpeed"));
                hm.setTemperature(jh.getString("temperature"));

                hourlyList.add(hm);

            }

            JSONObject daily =  jsono.getJSONObject("daily");
            JSONArray jArrayDaily = daily.getJSONArray("data");


            for (int d=0 ; d < jArrayDaily.length(); d++ ){
                JSONObject jh =  jArrayHourly.getJSONObject(d);
                DailyDataModel dm = new DailyDataModel();
                dm.setSummary(jh.getString("summary"));
                dm.setCloudCover(jh.getString("cloudCover"));
                dm.setDewPoint(jh.getString("dewPoint"));
                dm.setHumidity(jh.getString("humidity"));
                dm.setIcon(jh.getString("icon"));
                dm.setPrecipIntensity(jh.getString("precipIntensity"));
                dm.setPressure(jh.getString("pressure"));
                dm.setWindSpeed(jh.getString("windSpeed"));
                dm.setSunriseTime(jh.getString("sunriseTime"));
                dm.setSunsetTime(jh.getString("sunsetTime"));

                dm.setTemperature(jh.getString("temperature"));

                dailyList.add(dm);

            }
*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        return parsedString;
    }

    public static String convertinputStreamToString(InputStream ists) throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        getJson();
        return null;
    }
}
