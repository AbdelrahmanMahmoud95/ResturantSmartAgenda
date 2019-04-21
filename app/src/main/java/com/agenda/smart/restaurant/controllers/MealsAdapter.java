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
import com.agenda.smart.restaurant.model.Meal;
import com.agenda.smart.restaurant.model.Region;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by User on 22/10/2018.
 */

public class MealsAdapter extends ArrayAdapter {
    private Context mContext;
    private int resourceID;
    private List<Meal> meals;
    private SharedPreferences dataSaver;
    private ChooseDialog holder;

    public MealsAdapter(@NonNull Context context, int resource, @NonNull List<Meal> meals, ChooseDialog holder) {
        super(context, resource);
        this.mContext = context;
        this.resourceID = resource;
        this.meals = meals;
        this.holder = holder;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public Meal getItem(int position) {
        return meals.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(resourceID, parent, false);
        TextView name = (TextView) row.findViewById(R.id.name);
        name.setText(meals.get(position).getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = mContext.getApplicationContext();
                dataSaver = getDefaultSharedPreferences(context);

                dataSaver.edit()
                        .putString(AppKeys.MEALS_NAME, meals.get(position).getName())
                        .apply();
                dataSaver.edit()
                        .putInt(AppKeys.MEALS_ID, meals.get(position).getId())
                        .apply();

                holder.dismiss();
            }
        });
        return row;
    }
}
