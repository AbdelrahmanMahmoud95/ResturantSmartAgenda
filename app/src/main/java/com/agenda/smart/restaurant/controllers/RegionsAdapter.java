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
import com.agenda.smart.restaurant.model.Region;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Abdelrahman on 7/12/2018.
 */

public class RegionsAdapter extends ArrayAdapter {
    private Context mContext;
    private int resourceID;
    private List<Region> regions;
    private SharedPreferences dataSaver;
    private ChooseDialog holder;

    public RegionsAdapter(@NonNull Context context, int resource, @NonNull List<Region> regions, ChooseDialog holder) {
        super(context, resource);
        this.mContext = context;
        this.resourceID = resource;
        this.regions = regions;
        this.holder = holder;
    }

    @Override
    public int getCount() {
        return regions.size();
    }

    @Override
    public Region getItem(int position) {
        return regions.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(resourceID, parent, false);
        TextView name = (TextView) row.findViewById(R.id.name);
        name.setText(regions.get(position).getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = mContext.getApplicationContext();
                dataSaver = getDefaultSharedPreferences(context);

                dataSaver.edit()
                        .putString(AppKeys.REGION_NAME, regions.get(position).getName())
                        .apply();
                dataSaver.edit()
                        .putInt(AppKeys.REGION_ID, regions.get(position).getDistrictUID())
                        .apply();

                holder.dismiss();
            }
        });
        return row;
    }
}
