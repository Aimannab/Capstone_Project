package com.example.android.capstone_project;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.androidlibrary.RandomCountriesResultListActivity;

import java.util.ArrayList;

public class CountriesWidgetProvider extends AppWidgetProvider {

    static ArrayList<String> randomCountriesList = new ArrayList<>();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Constructing the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_gridview);

        //Creating Pending Intent for MainActivity
        Intent intent = new Intent(context, RandomCountriesResultListActivity.class);

        //Adding category
        intent.addCategory(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_gridview, pendingIntent);

        // Setting the GridWidgetService intent to act as the adapter for the GridView
        Intent gridViewIntent = new Intent(context, GridWidgetService.class);
        views.setRemoteAdapter(R.id.widget_gridview, gridViewIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
