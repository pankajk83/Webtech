package com.test1.weatherapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.test1.weatherapp.MapFragment;

public class MapActivity extends FragmentActivity {

    double lon;
    double lat;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        lon = intent.getDoubleExtra("lon",0.0);
        lat = intent.getDoubleExtra("lat",0.0);
        context = MapActivity.this;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapFragment fragment = new MapFragment();
        fragment.setlonlat(lon,lat,context);
        fragmentTransaction.add(R.id.map_container, fragment);
        fragmentTransaction.commit();

    }
}
