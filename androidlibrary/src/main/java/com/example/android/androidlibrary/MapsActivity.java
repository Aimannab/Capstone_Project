package com.example.android.androidlibrary;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.android.countrieslibrary.CountryData;
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //LatLng countryNav = new LatLng(countryData.getCountryLat(), countryData.getCountryLng());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Country"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //double lat = getLat();
        //double lng = getLng();
        //Intent intent = this.getIntent();
        //String country = intent.getStringExtra(COUNTRIES_KEY_EXTRA);
        //LatLng countryLatLng = new LatLng(lat, lng);
        //mMap.addMarker(new MarkerOptions().position(countryLatLng).title("Marker in " + country));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(countryLatLng));



        /*try {
            List<Address> address = new Geocoder(this).getFromLocationName("Ireland", 1);
            if (address == null) {
                //Log.e(TAG, "Not found");
            } else {
                Address loc = address.get(0);
                //Log.e(TAG, loc.getLatitude() + " " + loc.getLongitude());
                LatLng pos = new LatLng(loc.getLatitude(), loc.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
