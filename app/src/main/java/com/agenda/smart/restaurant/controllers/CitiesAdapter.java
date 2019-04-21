package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.agenda.smart.restaurant.ChooseDialog;
import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.City;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Abdelrahman on 7/8/2018.
 */

public class CitiesAdapter extends ArrayAdapter {
    private Context mContext;
    private int resourceID;
    private List<City> cities;
    private SharedPreferences dataSaver;
    private ChooseDialog holder;

    public CitiesAdapter(@NonNull Context context, int resource, @NonNull List<City> cities, ChooseDialog holder) {
        super(context, resource);
        this.mContext = context;
        this.resourceID = resource;
        this.cities = cities;
        this.holder = holder;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public City getItem(int position) {
        return cities.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(resourceID, parent, false);
        TextView name = (TextView) row.findViewById(R.id.name);
        name.setText(cities.get(position).getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = mContext.getApplicationContext();
                dataSaver = getDefaultSharedPreferences(context);

                dataSaver.edit()
                        .putString(AppKeys.CITY_NAME, cities.get(position).getName())
                        .apply();
                dataSaver.edit()
                        .putInt(AppKeys.CITY_ID, cities.get(position).getCityUID())
                        .apply();

                holder.dismiss();
            }
        });
        return row;
    }
}

