package com.test1.weatherapp;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.test1.weatherapp.MESSAGE";
    JSONObject response;
    MainActivity mainActivity;
    ArrayList<String> param = new ArrayList<String>();
    int state_position;
    ImageButton forecastButton;
    TextSwitcher mTextSwitcher;
    EditText streetText ;
    EditText cityText ;
    String city;
    String street;
    String state;
    Spinner spinnerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mainActivity = this;

        validate();

        }

    private void validate() {



        spinnerState=(Spinner) findViewById(R.id.state);


        String[] mItems = getResources().getStringArray(R.array.state_array);
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, mItems);
        //bundle adapter
        spinnerState.setAdapter(_Adapter);
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  validation();
                mTextSwitcher.setText("");
                state_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mTextSwitcher = (TextSwitcher) findViewById(R.id.text_validation);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(MainActivity.this);
                tv.setTextSize(20);
                tv.setTextColor(Color.RED);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                return tv;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        mTextSwitcher.setInAnimation(in);
        mTextSwitcher.setOutAnimation(out);
        mTextSwitcher.setText("");
       // validation();
        streetText = (EditText) findViewById(R.id.street);
        streetText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // validation();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validation();




            }

            @Override
            public void afterTextChanged(Editable s) {
                validation();
            }
        });
        cityText = (EditText) findViewById(R.id.city);
        cityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // validation();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validation();

            }

            @Override
            public void afterTextChanged(Editable s) {
                validation();
            }
        });

        forecastButton = (ImageButton)findViewById(R.id.forecastlink);
        forecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://forecast.io";
                Uri forecastURL = Uri.parse(url);
                Intent launch = new Intent(Intent.ACTION_VIEW, forecastURL);
                startActivity(launch);
            }
        });


    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public boolean validation() {
        String tstreet = streetText.getText().toString().trim();
        String tcity = cityText.getText().toString().trim();
        String tstate = spinnerState.getSelectedItem().toString();

        if (tstreet.isEmpty()) {
            mTextSwitcher.setText("Please enter a Street");
            return false;
        } else if (tcity.isEmpty()) {
            mTextSwitcher.setText("Please enter a City");
            return false;
        } else if (tstate.equals("Select")) {
            mTextSwitcher.setText("Please select a State");
            return false;
        }
        else {
            mTextSwitcher.setText("");
        }
        return true;
    }

    @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

            public void takeAction(View view) {
              /*  ArrayList<String> ar = new ArrayList<String>();
                Intent intent = new Intent(this, DisplayMessageActivity.class);
                EditText editText1 = (EditText) findViewById(R.id.street);
                EditText editText2 = (EditText) findViewById(R.id.city);
                Spinner state = (Spinner) findViewById(R.id.state);


                ar.add(editText1.getText().toString());
                ar.add(editText2.getText().toString());
                ar.add(state.toString());
                // String message = editText.getText().toString();
                // intent.putExtra(EXTRA_MESSAGE, message);
                intent.putStringArrayListExtra(EXTRA_MESSAGE, ar);
                startActivity(intent);*/

                if (validation() == false) {
                    return;
                }


                ArrayList<String> param = new ArrayList<String>();

                RadioGroup rg = (RadioGroup) findViewById(R.id.radiogr);
                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedId);
                String degree = (String) rb.getText();
                String state_position_string = Integer.toString(state_position);


                street = (String)streetText.getText().toString().trim();
                city = (String)cityText.getText().toString().trim();
                state = spinnerState.getSelectedItem().toString().trim();

                param.add(0, street);
                param.add(1, city);
                param.add(2, state);
                param.add(3, degree);
                param.add(4, state_position_string);
                JSONAsyncTask gj = new JSONAsyncTask(param);
                gj.execute();

            }

            public void showPic(View view) {
                setContentView(R.layout.activity_about);

            }

        public void clearAction(View view) {
            mTextSwitcher.setText("");
            streetText.setText("");
            cityText.setText("");
            spinnerState.setSelection(0, true);
            RadioButton rb= (RadioButton)findViewById(R.id.Farenheit);
            rb.setChecked(true);
            //validation();
        }



    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        private String street;
        private String city;
        private String state;
        private String degree;
        private String statePosition;


        CurrentDataModel currentDataModel;
        ArrayList<HourlyDataModel> hourlyList = new ArrayList<HourlyDataModel>();
        ArrayList<DailyDataModel> dailyList = new ArrayList<DailyDataModel>();

        public JSONAsyncTask(ArrayList al) {
            this.street = (String) al.get(0);
            this.city = (String) al.get(1);
            this.state = (String) al.get(2);
            this.degree = (String) al.get(3);
            this.statePosition = (String)al.get(4);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                String parsedString = "";


                //------------------------------

                final String FORECAST_BASE_URL ="http://weatherreport-env.elasticbeanstalk.com/HW8/index.php?";
                final String STREET_PARAM = "street";
                final String CITY_PARAM = "city";
                final String STATE_PARAM = "state";
                final String DEGREE_PARAM = "degree";
                final String ACTION_PARAM = "action";

                if (degree.equalsIgnoreCase("Celsius")) {
                    degree = "si";
                }
                else {
                    degree = "us";
                }

                Uri builtUri = Uri.parse(FORECAST_BASE_URL)
                        .buildUpon()
                        .appendQueryParameter(STREET_PARAM, street)
                        .appendQueryParameter(CITY_PARAM, city)
                        .appendQueryParameter(STATE_PARAM, state)
                        .appendQueryParameter(DEGREE_PARAM, degree)
                        .appendQueryParameter(ACTION_PARAM, "test")
                        .build();

                URL url = new URL(builtUri.toString());

                URLConnection conn = url.openConnection();
                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");

                httpConn.connect();

                InputStream is = httpConn.getInputStream();
                parsedString = convertinputStreamToString(is);


                JSONObject object = (JSONObject) new JSONTokener(parsedString).nextValue();

                response =(JSONObject) new JSONObject(object.getString("json"));

                return true;
            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }


        public String convertinputStreamToString(InputStream ists) throws IOException {
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



        protected void onPostExecute(Boolean result) {
            dialog.cancel();

            if(result == false) {
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
                return;
            }
            parseJson(response);
            Intent intent = new Intent(mainActivity, ResultActivity.class);

            intent.putExtra("json",currentDataModel);
            intent.putParcelableArrayListExtra("jsonhr", hourlyList);
            intent.putParcelableArrayListExtra("jsondl", dailyList);
            intent.putExtra("city", this.city);
            intent.putExtra("state",this.state);
            intent.putExtra("degree",this.degree);
            intent.putExtra("statePosition",this.statePosition);


            //use parcelable concept


            startActivity(intent);



        }

        public void parseJson(JSONObject response){
            //   JSONObject jsono = jsonov.getJSONObject()
            // For Current object:

            try {
            JSONObject curr =  response.getJSONObject("currently");
                currentDataModel = new CurrentDataModel();
                currentDataModel.setSummary(curr.getString("summary").toString());
                currentDataModel.setCloudCover(curr.getString("cloudCover"));
                currentDataModel.setDewPoint(curr.getString("dewPoint"));
                currentDataModel.setHumidity(curr.getString("humidity"));
                currentDataModel.setVisibility(curr.getString("visibility"));
                currentDataModel.setIcon(curr.getString("icon"));
                currentDataModel.setPrecipIntensity(curr.getString("precipIntensity"));
                currentDataModel.setPrecipProbablity(curr.getString("precipProbability"));
                currentDataModel.setPressure(curr.getString("pressure"));

                currentDataModel.setWindSpeed(curr.getString("windSpeed"));
                currentDataModel.setTemperature(curr.getString("temperature"));
                currentDataModel.setTimezone(response.getString("timezone"));

                JSONObject hourly =  response.getJSONObject("hourly");
                JSONArray jArrayHourly = hourly.getJSONArray("data");


                for (int h=0 ; h< jArrayHourly.length(); h++ ){
                    JSONObject jh =  jArrayHourly.getJSONObject(h);
                    HourlyDataModel hm = new HourlyDataModel();
                    hm.setSummary(jh.getString("summary"));
                    hm.setIcon(jh.getString("icon"));
                    hm.setTemperature(jh.getString("temperature"));
                    hm.setTime(jh.getString("time"));

                    hourlyList.add(hm);
                }

                JSONObject daily =  response.getJSONObject("daily");
                JSONArray jArrayDaily = daily.getJSONArray("data");


                for (int d=0 ; d < jArrayDaily.length(); d++ ){
                    JSONObject jd =  jArrayDaily.getJSONObject(d);
                    DailyDataModel dm = new DailyDataModel();
                    dm.setSummary(jd.getString("summary"));
                    dm.setIcon(jd.getString("icon"));
                    dm.setTemperatureMax(jd.getString("temperatureMax"));
                    dm.setTemperatureMin(jd.getString("temperatureMin"));
                    dm.setTime(jd.getString("time"));
                    dm.setSunriseTime(jd.getString("sunriseTime"));
                    dm.setSunsetTime(jd.getString("sunsetTime"));

                    dailyList.add(dm);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }




        }

    } //Inner class ends


} //Outer class ends
