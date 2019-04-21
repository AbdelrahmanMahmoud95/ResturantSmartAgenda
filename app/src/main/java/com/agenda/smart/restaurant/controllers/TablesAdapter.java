package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.Tables;
import com.agenda.smart.restaurant.views.CategoriesActivity;
import com.squareup.picasso.Picasso;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Abdelrahman on 8/1/2018.
 */

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.TablesViewHolder> {
    Tables tables;
    SharedPreferences dataSaver;
    Context context;

    public TablesAdapter(Context context, Tables tables) {
        this.context = context;
        this.tables = tables;

    }

    @Override
    public TablesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tables, parent, false);
        TablesViewHolder tablesViewHolder = new TablesViewHolder(view);
        return tablesViewHolder;

    }

    @Override
    public void onBindViewHolder(TablesViewHolder holder, final int position) {
        dataSaver = getDefaultSharedPreferences(context);

        Log.e("TAG", "tables.getTables().get(position).getId() " + tables.getTables().get(position).getId());
        holder.numbers.setText(String.valueOf(tables.getTables().get(position).getId()));
        Picasso.with(context).load(String.valueOf(tables.getTables().get(position).getTableImage() )).into(holder.tableImage);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoriesActivity.class);
                context.startActivity(intent);
                dataSaver.edit()
                        .putInt(AppKeys.TABLE_ID, tables.getTables().get(position).getId())
                        .apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tables.getTables().size();
    }

    public class TablesViewHolder extends RecyclerView.ViewHolder {
        TextView numbers;
        ImageView tableImage;
        RelativeLayout layout;

        public TablesViewHolder(View itemView) {
            super(itemView);
            numbers = itemView.findViewById(R.id.table_number);
            layout = itemView.findViewById(R.id.table_layout);
            tableImage = itemView.findViewById(R.id.table_image);


        }
    }
}
