package com.example.android.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Aiman Nabeel on 24/10/2018.
 */
public class CountriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ref: https://stackoverflow.com/questions/3389501/activity-transition-in-android
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_countries);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    //Creating a Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Making Menu to direct user to RandomCountriesResultListActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent randomCountriesResultListIntent = new Intent(this, RandomCountriesResultListActivity.class);
        startActivity(randomCountriesResultListIntent);

        return super.onOptionsItemSelected(item);

    }
}
