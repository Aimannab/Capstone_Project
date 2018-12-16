package com.example.android.capstone_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.androidlibrary.CountriesFragment;
import com.example.android.androidlibrary.RandomCountriesResultListActivity;
import com.example.android.countrieslibrary.CountryData;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import com.google.gson.Gson;

//import static com.example.android.capstone_project.CountriesWidgetProvider.randomCountriesList;

public class GridWidgetService extends RemoteViewsService {

    //ArrayList<String> randomCountriesForWidgets= new ArrayList<>();
    CountryData countryData;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        Context context = null;

        //We have enclosed this class in another class so we can use the following method with our own customized parameters: added Intent intent parameter
        public GridRemoteViewsFactory(Context applicationContext, Intent intent) {
            context = applicationContext;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

            //Using Shared preferences here to get the saved CountryData object from CountriesFragment.java
            //https://stackoverflow.com/questions/5418160/store-and-retrieve-a-class-object-in-shared-preference
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Gson gson = new Gson();
            String json = prefs.getString("country", "");
            countryData = gson.fromJson(json, CountryData.class);

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        //Setting widget_gridview_item here
        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_gridview_item);
            views.setTextViewText(R.id.widget_gridview_item, countryData.getCountryName());

            //Setting up FillIn Intent here for the gridview - To fill in the PendingIntent Template
            Intent fillInIntent = new Intent();
            views.setOnClickFillInIntent(R.id.widget_gridview_item, fillInIntent);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

}
