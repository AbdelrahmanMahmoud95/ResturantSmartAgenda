package com.agenda.smart.restaurant.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.model.Meals;
import com.agenda.smart.restaurant.model.ReportsMeals;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by User on 22/10/2018.
 */

public class ReportsMealsAdapter extends RecyclerView.Adapter<ReportsMealsAdapter.ReportsMealsViewHolder> {

    Context context;
    ReportsMeals meals;
    SharedPreferences dataSaver;

    public ReportsMealsAdapter(Context context, ReportsMeals meals) {
        this.context = context;
        this.meals = meals;

    }

    @Override
    public ReportsMealsAdapter.ReportsMealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_reports, parent, false);
        ReportsMealsAdapter.ReportsMealsViewHolder reportsMealsViewHolder = new ReportsMealsAdapter.ReportsMealsViewHolder(view);
        return reportsMealsViewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportsMealsViewHolder holder, final int position) {
        dataSaver = getDefaultSharedPreferences(context);
        holder.itemName.setText(meals.getMeals().get(position).getName());
        double sales_cost =meals.getMeals().get(position).getSalesCost();
        double returns_cost = meals.getMeals().get(position).getReturnsCost();
        holder.itemPrice.setText(String.valueOf(sales_cost));
        holder.cost.setText(String.valueOf(sales_cost - returns_cost));
    }

    @Override
    public int getItemCount() {
        return meals.getMeals().size();
    }

    public class ReportsMealsViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, cost;


        public ReportsMealsViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.name);
            itemPrice = itemView.findViewById(R.id.price);
            cost = itemView.findViewById(R.id.sales_cost);


        }
    }


}
