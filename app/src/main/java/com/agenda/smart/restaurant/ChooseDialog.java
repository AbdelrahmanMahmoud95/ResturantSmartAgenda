package com.agenda.smart.restaurant;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agenda.smart.restaurant.controllers.CitiesAdapter;
import com.agenda.smart.restaurant.controllers.MealsAdapter;
import com.agenda.smart.restaurant.controllers.RegionsAdapter;
import com.agenda.smart.restaurant.model.City;
import com.agenda.smart.restaurant.model.Meal;
import com.agenda.smart.restaurant.model.Region;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Abdelrahman on 7/8/2018.
 */

public class ChooseDialog extends Dialog {
    Context context;
    ListView listView;
    TextView loadingTxt;
    ProgressBar progressBar;
    TextView title;
    String titleToSet;


    SharedPreferences dataSaver;

    public ChooseDialog(@NonNull Context context, String titleToSet) {
        super(context);
        this.context = context;
        this.titleToSet = titleToSet;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_dialog);

        listView = (ListView) findViewById(R.id.city_or_area_names);
        loadingTxt = (TextView) findViewById(R.id.loading_txt);
        progressBar = (ProgressBar) findViewById(R.id.loading_photo);
        title = (TextView) findViewById(R.id.dialog_title);
        title.setText(titleToSet);

        loadingTxt.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }



    public void showCitiesList(List<City> list) {
        loadingTxt.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        final ArrayAdapter adapter = new CitiesAdapter(context, R.layout.basic_view, list, this);
        listView.setAdapter(adapter);

    }

    public void showRegionsList(List<Region> list) {
        loadingTxt.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        final ArrayAdapter adapter = new RegionsAdapter(context, R.layout.basic_view, list, this);
        listView.setAdapter(adapter);

    }


    public void showMealsList(List<Meal> list) {
        loadingTxt.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        final ArrayAdapter adapter = new MealsAdapter(context, R.layout.basic_view, list, this);
        listView.setAdapter(adapter);

    }





    }


