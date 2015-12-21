package com.test1.weatherapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.InflateException;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class ResultActivity extends AppCompatActivity {
    CurrentDataModel message;
    ArrayList<HourlyDataModel> hourlyList;
    ArrayList<DailyDataModel> dailyList;
    HourlyDataModel hm;
    DailyDataModel dm;
    String city;
    String state;
    String degree;
    String statePosition;
    String stateCode;
    ImageSwitcher mImageSwitcher;
    private String temperatureVal;
    private String iconVal;
    private String precipitationVal;
    private String chanceOfRainVal;
    private String windSpeedVal;
    private String dewPointVal;
    private String humidityVal;
    private String visibilityVal;
    private String sunriseVal;
    private String sunsetVal;
    private String minTempval;
    private String maxTempVal;
    private double lon;
    private  double lat;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ImageButton share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get the message from the intent
        Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        this.message = (CurrentDataModel)intent.getSerializableExtra("json");
        this.hourlyList = intent.getParcelableArrayListExtra("jsonhr");
        this.dailyList = intent.getParcelableArrayListExtra("jsondl");
        this.city = intent.getStringExtra("city");
        this.state = intent.getStringExtra("state");
        this.degree = intent.getStringExtra("degree");
        this.statePosition =intent.getStringExtra("statePosition");
        this.lat = intent.getDoubleExtra("lat", 0.0);
        this.lon = intent.getDoubleExtra("lon", 0.0);
        int statePos = Integer.parseInt(statePosition);

        String[] mState_Code = getResources().getStringArray(R.array.state_codes);
        stateCode = mState_Code[statePos- 1];

        if (degree.equalsIgnoreCase("us")) {
            degree = "F";
        }
        else {
            degree = "C";
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        share = (ImageButton) findViewById(R.id.fb);

        share.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         try {

                                             String summary = message.getSummary();
                                             String temperature = message.getTemperature();
                                             int temp = (int) Double.parseDouble(temperature);
                                             String tVal = Integer.toString(temp)+(char) 0x00B0 + degree;

                                             String icon = message.getIcon();
                                            String icons = "icons/" + icon + ".png";
                                            String iconPath =  "http://ec2-52-27-121-240.us-west-2.compute.amazonaws.com/HW8/" + icons;

                                             ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                                     .setContentTitle("Current Weather in " + city + "," + stateCode)
                                                     .setContentUrl(Uri.parse("http://forecast.io"))
                                                     .setContentDescription(summary + "," + tVal)
                                                     .setImageUrl(Uri.parse(iconPath))
                                                     .build();

                                             shareDialog.show(linkContent);
                                             shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                                                 @Override
                                                 public void onSuccess(Sharer.Result result) {
                                                 }

                                                 @Override
                                                 public void onCancel() {
                                                 }

                                                 @Override
                                                 public void onError(FacebookException e) {
                                                 }
                                             }, 123456);
                                         } catch (Exception e) {
                                             e.printStackTrace();
                                         }
                                     }
                                 }
        );






        try {

            init();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void moreDetails(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putParcelableArrayListExtra("hourly",hourlyList);
        intent.putParcelableArrayListExtra("daily", dailyList);
        intent.putExtra("city", this.city);
        intent.putExtra("stateCode", this.stateCode);
        intent.putExtra("degree",this.degree);
        intent.putExtra("timezone", message.getTimezone());

        startActivity(intent);
    }

    public void displayMap(View view){
                 Intent intent = new Intent();
                 intent.putExtra("lon", this.lon);
                 intent.putExtra("lat", this.lat);
                 intent.setClass(ResultActivity.this, MapActivity.class);
                 ResultActivity.this.startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar
        // automatically handles clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    void init () {
        formatData();
        TableLayout stk = (TableLayout) findViewById(R.id.result);
        stk.setVisibility(View.VISIBLE);

        TableRow row1 = new TableRow(this);
        row1.setPadding(60, 10, 10, 10);
        ImageView iView = new ImageView(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(170,170);
        iView.setLayoutParams(layoutParams);
        //  col2.setAdjustViewBounds(true);
        String icon = message.getIcon();
        switch (icon) {
            case "clear-day":
                iView.setImageResource(R.drawable.clear);
                break;
            case "clear-night":
                iView.setImageResource(R.drawable.clear_night);
                break;
            case "partly-cloudy-day":
                iView.setImageResource(R.drawable.cloud_day);
                break;
            case "partly-cloudy-night":
                iView.setImageResource(R.drawable.cloud_night);
                break;
            case "rain":
                iView.setImageResource(R.drawable.rain);
                break;
            case "snow":
                iView.setImageResource(R.drawable.rain);
                break;
            case "sleet":
                iView.setImageResource(R.drawable.sleet);
                break;
            case "wind":
                mImageSwitcher.setImageResource(R.drawable.wind);
                break;
            case "fog":
                iView.setImageResource(R.drawable.fog);
                break;
            case "cloudy":
                iView.setImageResource(R.drawable.cloudy);
                break;
        }
       row1.addView(iView);


        String str = message.getSummary()+" "+"in"+" "+city+","+stateCode;
        TableRow row2 = new TableRow(this);
        TextView v21 = new TextView(this);



        if (str.length() > 27) {
            row2.setPadding(5, 10, 0, 10);
            v21.setTextSize(18);
            v21.setGravity(Gravity.LEFT);
        }
        else {
            row2.setPadding(50, 10, 10, 10);
            v21.setTextSize(25);
            v21.setGravity(Gravity.CENTER);
        }

        v21.setText(str);
        row2.addView(v21);

        TableRow row3 = new TableRow(this);
        row3.setPadding(60, 10, 10, 10);
        TextView v31 = new TextView(this);
        v31.setTextSize(25);
       // v31.setFontFeatureSettings(TextView.BO);
        v31.setGravity(Gravity.CENTER);
        v31.setText(temperatureVal);
        row3.addView(v31);


        String minMax= minTempval+" | "+maxTempVal;
        TableRow row4 = new TableRow(this);

        row4.setPadding(60, 10, 10, 10);
        TextView v41 = new TextView(this);
        v41.setTextSize(25);
        v41.setGravity(Gravity.CENTER);
        v41.setText(minMax);
        row4.addView(v41);


        TableRow row5 = new TableRow(this);
         row5.setPadding(0,10,0,10);
        TextView v51 = new TextView(this);
        v51.setText("Precipitation");
        v51.setTextSize(20);
        TextView v52 = new TextView(this);
            //v52.setText(message.getPrecipIntensity());
        v52.setText(precipitationVal);
        v52.setTextSize(18);
      //  v52.setGravity(Gravity.RIGHT);
        row5.addView(v51);row5.addView(v52);

        TableRow row6 = new TableRow(this);
        row6.setPadding(0, 10, 0, 10);
        TextView v61 = new TextView(this);
        v61.setText("Chance of Rain");
        v61.setTextSize(20);
        TextView v62 = new TextView(this);

        v62.setText(chanceOfRainVal);
        v62.setTextSize(18);
      //  v62.setGravity(Gravity.RIGHT);
        row6.addView(v61);row6.addView(v62);

        TableRow row7 = new TableRow(this);
        row7.setPadding(0, 10, 0, 10);
        TextView v71 = new TextView(this);
        v71.setText("Wind Speed");
        v71.setTextSize(20);
        TextView v72 = new TextView(this);
      //  v72.setGravity(Gravity.RIGHT);
        v72.setTextSize(18);
        v72.setText(windSpeedVal);
        row7.addView(v71);row7.addView(v72);

        TableRow row8 = new TableRow(this);
        row8.setPadding(0, 10, 0, 10);
        TextView v81 = new TextView(this);
        v81.setText("Dew Point");
        v81.setTextSize(20);
        TextView v82 = new TextView(this);
      // v82.setGravity(Gravity.RIGHT);
        v82.setText(dewPointVal);
        v82.setTextSize(18);
        row8.addView(v81);row8.addView(v82);

        TableRow row9 = new TableRow(this);
        row9.setPadding(0, 10, 0, 10);
        TextView v91 = new TextView(this);
        v91.setText("Humidity");
        v91.setTextSize(20);
        TextView v92 = new TextView(this);
       // v92.setGravity(Gravity.RIGHT);
        v92.setText(humidityVal);
        v92.setTextSize(18);
        row9.addView(v91);row9.addView(v92);

        TableRow row10 = new TableRow(this);
        row10.setPadding(0, 10, 0, 10);
        TextView v101 = new TextView(this);
        v101.setTextSize(20);
        v101.setText("Visibility");
        TextView v102 = new TextView(this);
       // v102.setGravity(Gravity.RIGHT);
        v102.setText(visibilityVal);
        v102.setTextSize(18);
        row10.addView(v101);row10.addView(v102);
        //TableRow.LayoutParams layoutParams1 = new TableRow.LayoutParams(50,100);
        TableRow row11 = new TableRow(this);
        row11.setPadding(0, 10, 0, 10);
        TextView v111 = new TextView(this);
        v111.setText("Sunrise");
        v111.setTextSize(20);
        TextView v112 = new TextView(this);
      //  v112.setGravity(Gravity.RIGHT);
     //   v112.setLayoutParams(layoutParams1);
        v112.setText(sunriseVal);
        v112.setTextSize(18);
        row11.addView(v111);row11.addView(v112);
        /*
         android:layout_width="fill_parent"
                            android:layout_height="wrap_content"
                            android:textSize="25sp"
                            android:layout_gravity="right"
         */


        TableRow row12 = new TableRow(this);
        row12.setPadding(0, 10, 0, 10);
        TextView v121 = new TextView(this);
        v121.setText("Sunset");
        v121.setTextSize(20);
        TextView v122 = new TextView(this);
       // v122.setGravity(Gravity.RIGHT);
      //  v122.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK);


        v122.setText(sunsetVal);
        v122.setTextSize(18);
        row12.addView(v121);row12.addView(v122);


        stk.addView(row1);stk.addView(row2); stk.addView(row3);stk.addView(row4);

        stk.addView(row5); stk.addView(row6);
        stk.addView(row7); stk.addView(row8);
        stk.addView(row9); stk.addView(row10);
        stk.addView(row11); stk.addView(row12);

    }

    private void formatData() {


            String temperature = message.getTemperature();
            if (temperature == null) {
                temperatureVal = "N/A";
            } else {
                int temp = (int)(Double.parseDouble(temperature));
                temperatureVal= Integer.toString(temp)+(char) 0x00B0 + degree;
            }

            String minTemp = dailyList.get(0).getTemperatureMin();
            String maxTemp = dailyList.get(0).getTemperatureMax();
            if (minTemp == null) {
                minTempval = "L: N/A";
            } else {
                int temp = (int)Double.parseDouble(minTemp);
                minTempval = "L: " + temp;
            }
            if (maxTemp == null) {
                maxTempVal = "H: N/A";
            } else {
                int temp = (int)Double.parseDouble(maxTemp);
                maxTempVal = "H: " + temp;
            }

            String precipitation = message.getPrecipIntensity();

            if (precipitation == null){
                precipitationVal = "N/A";
            }
            else {
                double temp = Double.parseDouble(precipitation);
                if (temp >= 0 && temp < 0.002) {
                    precipitationVal = "None";
                }
                else if (temp >= 0.002 && temp < 0.017) {
                    precipitationVal = "Very Light";
                }
                else if (temp >= 0.017 && temp < 0.1) {
                    precipitationVal = "Light";
                }
                else if (temp >= 0.1 && temp < 0.4) {
                    precipitationVal = "Moderate";
                }
                else if (temp >= 0.4) {
                    precipitationVal = "Heavy";
                }
                else {
                    precipitationVal = "None";
                }
            }

            String chanceOfRain  = message.getPrecipProbablity();

            if (chanceOfRain == null) {
              chanceOfRainVal = "N/A";
            }
            else {
                double temp = Double.parseDouble(chanceOfRain);
                int tempI = (int)(temp*100);
                chanceOfRainVal = Integer.toString(tempI)+"%";

            }

            String windSpeed = message.getWindSpeed();


            if (windSpeed == null) {
                windSpeedVal = "N/A";
            }
            else {

                if (degree.equalsIgnoreCase("F")) {
                    windSpeedVal = windSpeed + " mph";
                } else {
                    windSpeedVal = windSpeed + " m/s";
                }
            }

            String dewPoint = message.getDewPoint();

            if (dewPoint == null) {
                dewPointVal = "N/A";
            }
            else {
                int temp = (int)Double.parseDouble(dewPoint);
                dewPointVal = Integer.toString(temp)+(char) 0x00B0 + degree;

            }

            String humidity = message.getHumidity();
            if (humidity == null) {
                humidityVal = "N/A";
            }
            else {
                double temp = Double.parseDouble(humidity);
                int k = (int)temp*100;
                humidityVal = k+"%";
            }

            String visibility = message.getVisibility();

            if (visibility == null) {
                visibilityVal = "N/A";
            }
            else {
                if (degree.equalsIgnoreCase("F")) {
                    visibilityVal = visibility+" mi";
                }
                else {
                    visibilityVal = visibility+" km";
                }

            }

            String sunrise = dailyList.get(0).getSunriseTime();
            String timezone = message.getTimezone();

            if (sunrise == null) {
                sunriseVal = "N/A";
            }
            else {
                sunriseVal = convertTime(sunrise,timezone);
            }

           String sunset = dailyList.get(0).getSunsetTime();
          if (sunset == null) {
              sunsetVal = "N/A";
             }
          else {
              sunsetVal = convertTime(sunset,timezone);
          }


    }


    public  String convertTime(String unixTimestamp, String timezone) {
        long unixTime = Long.parseLong(unixTimestamp);
        Date date  = new Date(unixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        if(timezone !=null)
        {
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
        }
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //use the requestCode to selfdefine
        if (requestCode == 123456) {
            Toast.makeText(getApplicationContext(), "Facebook Post Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Post Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
