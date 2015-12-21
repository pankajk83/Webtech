package com.test1.weatherapp;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class DetailsActivity extends AppCompatActivity {
    ArrayList<HourlyDataModel> hourlyList;
    ArrayList<DailyDataModel> dailyList;
    HourlyDataModel hm;
    DailyDataModel dm;
    TableLayout tableLayd ;
    TableLayout tableLayh ;
    Button hours_button;
    Button days_button;
    String city;
    String stateCode;
    private TextView detailsMessage;
    String degree;
    private String timezone;
    private String minTempval;
    private String maxTempval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
       /* tableLayd = (TableLayout) findViewById(R.id.result2);
        tableLayh = (TableLayout) findViewById(R.id.result1);*/

        hourlyList = intent.getParcelableArrayListExtra("hourly");
        dailyList = intent.getParcelableArrayListExtra("daily");
        this.city = intent.getStringExtra("city");
        this.stateCode = intent.getStringExtra("stateCode");
        this.timezone = intent.getStringExtra("timezone");

        detailsMessage = (TextView)findViewById(R.id.details);
        this.degree = intent.getStringExtra("degree");

        detailsMessage.setText("More details for "+city+","+stateCode);

        hours_button = (Button)findViewById(R.id.button7);
        hours_button.setPressed(true);
        hours_button.setFocusable(true);
        hours_button.setFocusableInTouchMode(true);
        next24Hours();
        days_button = (Button)findViewById(R.id.button8);
        hours_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hours_button.setFocusableInTouchMode(true);
                hours_button.setPressed(true);
                hours_button.setFocusable(true);

                days_button.setPressed(false);
                days_button.setFocusable(false);
                days_button.setFocusableInTouchMode(false);
               /* if(hours_button.isPressed())
                {
                    hours_button.setFocusableInTouchMode(true);
                    hours_button.setPressed(true);
                    hours_button.setFocusable(true);

                    days_button.setPressed(false);
                    days_button.setFocusable(false);
                    days_button.setFocusableInTouchMode(false);


                }*/
                next24Hours();
                return false;
            }
        });


        days_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                days_button.setFocusableInTouchMode(true);
                hours_button.setFocusableInTouchMode(false);

                days_button.setPressed(true);
                days_button.setFocusable(true);

                hours_button.setPressed(false);
                hours_button.setFocusable(false);
              /*
                if(days_button.isPressed())
                {

                    days_button.setPressed(true);
                    days_button.setFocusable(true);

                    hours_button.setPressed(false);
                    hours_button.setFocusable(false);



                }*/
                next7Days(v);
                return false;
            }
        });
    }
//View view
  public void next24Hours() {


     /* TableLayout tableLay = (TableLayout) findViewById(R.id.result1);
      TableLayout tableLay2 = (TableLayout) findViewById(R.id.result2);*/
      tableLayd = (TableLayout) findViewById(R.id.result2);
      tableLayh = (TableLayout) findViewById(R.id.result1);
      tableLayh.setVisibility(View.VISIBLE);
      tableLayd.setVisibility(View.INVISIBLE);

      tableLayh.removeAllViews();


      TableRow rowcol = new TableRow(this);
      rowcol.setPadding(5, 10, 5, 10);
      TextView col1 = new TextView(this);
     // col1.setGravity(Gravity.CENTER);

      col1.setTextSize(25);
      col1.setText("Time");




      TextView col2 = new TextView(this);
     // col2.setGravity(Gravity.CENTER);
      col2.setTextSize(25);
      col2.setText("Summary");
      col2.setPadding(10, 10, 30, 10);

      TextView col3 = new TextView(this);
      //col3.setGravity(Gravity.CENTER);
      col3.setTextSize(25);
      col3.setText("Temp(" + (char) 0x00B0 + degree + ")");

      rowcol.setBackgroundResource(R.color.hourlyHeading);

      rowcol.addView(col1);
      rowcol.addView(col2);
      rowcol.addView(col3);

      tableLayh.addView(rowcol);



      for (int k = 0 ;k< 24; k++) {
          hm = hourlyList.get(k);
          TableRow row = new TableRow(this);
          row.setPadding(5, 10, 5, 10);
          TextView v1 = new TextView(this);
         // v1.setGravity(Gravity.CENTER);
          v1.setTextSize(20);
          v1.setTextColor(Color.BLACK);


          String timeS = hm.getTime();

          if (timeS == null) {
              v1.setText("N/A");
          }
          else {
              v1.setText(convertTime(hm.getTime(),timezone));
          }

         /* TextView v2 = new TextView(this);
          v2.setGravity(Gravity.CENTER);
          v2.setTextSize(20);
          v2.setText(hm.getSummary());*/

          ImageView v2 = new ImageView(this);
          TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100,100);
          v2.setLayoutParams(layoutParams);
          //  col2.setAdjustViewBounds(true);
          String icon = hm.getIcon();
          switch (icon) {
              case "clear-day":
                  v2.setImageResource(R.drawable.clear);
                  break;
              case "clear-night":
                  v2.setImageResource(R.drawable.clear_night);
                  break;
              case "partly-cloudy-day":
                  v2.setImageResource(R.drawable.cloud_day);
                  break;
              case "partly-cloudy-night":
                  v2.setImageResource(R.drawable.cloud_night);
                  break;
              case "rain":
                  v2.setImageResource(R.drawable.rain);
                  break;
              case "snow":
                  v2.setImageResource(R.drawable.rain);
                  break;
              case "sleet":
                  v2.setImageResource(R.drawable.sleet);
                  break;
              case "wind":
                  v2.setImageResource(R.drawable.wind);
                  break;
              case "fog":
                  v2.setImageResource(R.drawable.fog);
                  break;
              case "cloudy":
                  v2.setImageResource(R.drawable.cloudy);
                  break;
          }

          TextView v3 = new TextView(this);
         // v3.setGravity(Gravity.CENTER);
          v3.setTextSize(20);
          v3.setTextColor(Color.BLACK);


          String tempS = hm.getTemperature();

          if (tempS == null) {
              v3.setText("N/A");
          }
          else {
              int temp = (int)Double.parseDouble(tempS);
              v3.setPadding(50,10,10,10);
              v3.setText(Integer.toString(temp));
          }


          if (k%2 == 0) {
              row.setBackgroundResource(R.color.colorHourlyEven);

          }
          else {
              row.setBackgroundResource(R.color.colorHourlyOdd);

          }

          row.addView(v1);
          row.addView(v2);
          row.addView(v3);

          tableLayh.addView(row);


      }

      final TableRow tableRow = new TableRow(this);
    //  tableRow.setPadding(5, 10, 5, 10);


      tableRow.setBackgroundResource(R.color.colorHourlyEven);


      TextView textView = new TextView(getApplicationContext());
      textView.setPadding(45, 10, 25, 10);
      TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
      layoutParams2.gravity = Gravity.CENTER;
      layoutParams2.column = 1;
      textView.setBackgroundResource(R.color.expand_button);
      textView.setLayoutParams(layoutParams2);
      textView.setText("+");
      textView.setTextSize(25);
      textView.setTextColor(Color.parseColor("#000000"));
      tableRow.addView(textView);
      tableLayh.addView(tableRow);
      //textView.setPadding(20, 10, 20, 10);
      textView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              tableLayh.removeView(tableRow);


//hourlyList.size()
                  for (int m = 24; m < hourlyList.size(); m++) {
                      hm = hourlyList.get(m);
                      TableRow row = new TableRow(getApplicationContext());
                      row.setPadding(5, 10, 5, 10);
                      TextView v1 = new TextView(getApplicationContext());
                      // v1.setGravity(Gravity.CENTER);
                      v1.setTextSize(20);
                      v1.setTextColor(Color.BLACK);

                      String timeS = hm.getTime();

                      if (timeS == null) {
                          v1.setText("N/A");
                      } else {
                          v1.setText(convertTime(hm.getTime(), timezone));
                      }

         /* TextView v2 = new TextView(this);
          v2.setGravity(Gravity.CENTER);
          v2.setTextSize(20);
          v2.setText(hm.getSummary());*/

                      ImageView v2 = new ImageView(getApplicationContext());
                      TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100, 100);
                      v2.setLayoutParams(layoutParams);
                      //  col2.setAdjustViewBounds(true);
                      String icon = hm.getIcon();
                      switch (icon) {
                          case "clear-day":
                              v2.setImageResource(R.drawable.clear);
                              break;
                          case "clear-night":
                              v2.setImageResource(R.drawable.clear_night);
                              break;
                          case "partly-cloudy-day":
                              v2.setImageResource(R.drawable.cloud_day);
                              break;
                          case "partly-cloudy-night":
                              v2.setImageResource(R.drawable.cloud_night);
                              break;
                          case "rain":
                              v2.setImageResource(R.drawable.rain);
                              break;
                          case "snow":
                              v2.setImageResource(R.drawable.rain);
                              break;
                          case "sleet":
                              v2.setImageResource(R.drawable.sleet);
                              break;
                          case "wind":
                              v2.setImageResource(R.drawable.wind);
                              break;
                          case "fog":
                              v2.setImageResource(R.drawable.fog);
                              break;
                          case "cloudy":
                              v2.setImageResource(R.drawable.cloudy);
                              break;
                      }

                      TextView v3 = new TextView(getApplicationContext());
                      // v3.setGravity(Gravity.CENTER);
                      v3.setTextSize(20);
                      v3.setTextColor(Color.BLACK);

                      String tempS = hm.getTemperature();

                      if (tempS == null) {
                          v3.setText("N/A");
                      } else {
                          int temp = (int) Double.parseDouble(tempS);
                          v3.setPadding(50, 10, 10, 10);
                          v3.setText(Integer.toString(temp));
                      }


                      if (m % 2 == 0) {
                          row.setBackgroundResource(R.color.colorHourlyEven);

                      } else {
                          row.setBackgroundResource(R.color.colorHourlyOdd);

                      }

                      row.addView(v1);
                      row.addView(v2);
                      row.addView(v3);

                      tableLayh.addView(row);


                  }


          }});
      }

    public void next7Days(View view) {

            tableLayd = (TableLayout) findViewById(R.id.result2);
            tableLayh = (TableLayout) findViewById(R.id.result1);
            tableLayd.removeAllViews();
            tableLayd.setVisibility(View.VISIBLE);
            tableLayh.setVisibility(View.INVISIBLE);

            Resources colrArray = getResources();
            TypedArray colors = colrArray.obtainTypedArray(R.array.colors);

        for (int k = 1 ;k< dailyList.size(); k++) {
            dm = dailyList.get(k);
            TableRow rowd = new TableRow(this);
            rowd.setPadding(0, 10, 10, 10);
            TextView v1 = new TextView(this);
            //v1.setGravity(Gravity.CENTER);
            v1.setTextSize(20);
            v1.setTypeface(null, Typeface.BOLD);
            String tempTime = dm.getTime();

            if (tempTime == null) {
                v1.setText("N/A");
            }
            else{
                v1.setText(convertTimeIntoDay(tempTime, timezone));

            }

           /* TextView v2 = new TextView(this);
            v2.setGravity(Gravity.CENTER);
            v2.setTextSize(20);
            v2.setText(dm.getSummary());*/

            ImageView v2 = new ImageView(this);
            //v2.setPadding(90,100,0,100);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100,100);
            layoutParams.gravity = Gravity.RIGHT;
            layoutParams.weight = 1;

            v2.setLayoutParams(layoutParams);
            String icon = hm.getIcon();
            switch (icon) {
                case "clear-day":
                    v2.setImageResource(R.drawable.clear);
                    break;
                case "clear-night":
                    v2.setImageResource(R.drawable.clear_night);
                    break;
                case "partly-cloudy-day":
                    v2.setImageResource(R.drawable.cloud_day);
                    break;
                case "partly-cloudy-night":
                    v2.setImageResource(R.drawable.cloud_night);
                    break;
                case "rain":
                    v2.setImageResource(R.drawable.rain);
                    break;
                case "snow":
                    v2.setImageResource(R.drawable.rain);
                    break;
                case "sleet":
                    v2.setImageResource(R.drawable.sleet);
                    break;
                case "wind":
                    v2.setImageResource(R.drawable.wind);
                    break;
                case "fog":
                    v2.setImageResource(R.drawable.fog);
                    break;
                case "cloudy":
                    v2.setImageResource(R.drawable.cloudy);
                    break;
            }


          /*  TextView v3 = new TextView(this);
            v3.setGravity(Gravity.CENTER);
            v3.setTextSize(20);
            v3.setText(dm.getTemperatureMax());*/

            rowd.addView(v1);
            rowd.addView(v2);
           // rowd.addView(v3);

            TableRow rowd1 = new TableRow(this);
            rowd1.setPadding(0, 10, 10, 10);
            TextView v3 = new TextView(this);
          //  v3.setGravity(Gravity.CENTER);
            v3.setTextSize(20);
            String minTemp = dm.getTemperatureMin();
            String maxTemp = dm.getTemperatureMax();
            if (minTemp == null) {
                minTempval = "Min: N/A";
            } else {
                int temp = (int)Double.parseDouble(minTemp);
                minTempval = "Min: " + temp+(char) 0x00B0 + degree;;
            }
            if (maxTemp == null) {
                maxTempval = "Max: N/A";
            } else {
                int temp = (int)Double.parseDouble(maxTemp);
                maxTempval = "Max: " + temp+(char) 0x00B0 + degree;
            }
            v3.setText(minTempval+" | "+maxTempval);
            rowd1.addView(v3);


            int color = colors.getColor(k - 1, 0);
            rowd.setBackgroundColor(color);
            rowd1.setBackgroundColor(color);
/*
            if (k%2 == 0) {
                rowd.setBackgroundColor(Color.GREEN);
                rowd1.setBackgroundColor(Color.GREEN);
            }
            else {
                rowd.setBackgroundColor(Color.WHITE);
                rowd1.setBackgroundColor(Color.WHITE);
            }*/
            tableLayd.addView(rowd);
            tableLayd.addView(rowd1);


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
    public  String convertTimeIntoDay(String unixTimestamp, String timezone) {
        long unixTime = Long.parseLong(unixTimestamp);
        Date date  = new Date(unixTime * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd");
        if(timezone !=null)
        {
            sdf.setTimeZone(TimeZone.getTimeZone(timezone));
        }
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

}
