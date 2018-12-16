package com.example.android.capstone_project;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.android.androidlibrary.RandomCountriesContentProvider;

import java.util.ArrayList;


public class CountriesWidgetService extends IntentService {

    public static String ACTION_RANDOM_COUNTRIES_LIST =
            "FROM_RANDOM_COUNTRIES_RESULT_LIST_ACTIVITY";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public CountriesWidgetService() {
        super("UpdateCountriesService");
    }

    public static void startRandomCountriesListService(Context context, ArrayList<String> ingredientsList) {
        Intent intent = new Intent(context, CountriesWidgetService.class);
        intent.putExtra(ACTION_RANDOM_COUNTRIES_LIST, ingredientsList);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent( Intent intent) {
        if (intent != null) {
            ArrayList<String> RandomCountriesResultList = intent.getExtras().getStringArrayList(ACTION_RANDOM_COUNTRIES_LIST);
            handleActionUpdateRandomCountriesListWidget(RandomCountriesResultList);
        }
    }

    private void handleActionUpdateRandomCountriesListWidget(ArrayList<String> randomCountriesList) {
        Intent intent = new Intent(this, CountriesWidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra(ACTION_RANDOM_COUNTRIES_LIST, randomCountriesList);
        sendBroadcast(intent);
    }
}
