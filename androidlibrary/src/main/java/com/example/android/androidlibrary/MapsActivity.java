package com.example.android.androidlibrary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.android.countrieslibrary.CountryData;
import com.google.gson.Gson;

/**
 * Created by Aiman Nabeel on 24/10/2018.
 */
//Ref: https://developers.google.com/maps/documentation/android-sdk/start
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final String COUNTRIES_KEY_EXTRA = "country";
    CountryData countryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Using Shared preferences here to get the saved CountryData object from CountriesFragment.java
        //https://stackoverflow.com/questions/5418160/store-and-retrieve-a-class-object-in-shared-preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString("country", "");
        CountryData countryData = gson.fromJson(json, CountryData.class);

        // Add a marker in country and move the camera
        //LatLng sydney = new LatLng(-34, 151); - For Testing
        //double countryLat = countryData.getCountryLat(); - For Testing
        LatLng countryNav = new LatLng(countryData.getCountryLat(), countryData.getCountryLng());
        mMap.addMarker(new MarkerOptions().position(countryNav).title(countryData.getCountryName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(countryNav));
    }
}
