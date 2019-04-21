package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.ReportsMeals;
import com.agenda.smart.restaurant.model.ReportsTaxes;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by User on 22/10/2018.
 */

public class ReportsTaxesAdapter extends RecyclerView.Adapter<ReportsTaxesAdapter.ReportsTaxesViewHolder> {

    Context context;
    ReportsTaxes taxes;


    public ReportsTaxesAdapter(Context context, ReportsTaxes taxes) {
        this.context = context;
        this.taxes = taxes;

    }

    @Override
    public ReportsTaxesAdapter.ReportsTaxesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taxes_reports, parent, false);
        ReportsTaxesAdapter.ReportsTaxesViewHolder reportsTaxesViewHolder = new ReportsTaxesAdapter.ReportsTaxesViewHolder(view);
        return reportsTaxesViewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportsTaxesAdapter.ReportsTaxesViewHolder holder, final int position) {
        holder.costWithTax.setText(String.valueOf(taxes.getOrders().get(position).getCostWithTax()));
        double cost_value = taxes.getOrders().get(position).getCost();
        double tax_value = taxes.getOrders().get(position).getTax();
        holder.cost.setText(String.valueOf(cost_value));
        holder.orderNumber.setText(String.valueOf(taxes.getOrders().get(position).getId()));
        holder.tax.setText(String.valueOf(tax_value));
        holder.taxValue.setText(String.valueOf(tax_value * cost_value));
        holder.date.setText(taxes.getOrders().get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return taxes.getOrders().size();
    }

    public class ReportsTaxesViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber, cost, costWithTax, tax, taxValue, date;


        public ReportsTaxesViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            costWithTax = itemView.findViewById(R.id.cost_with_tax);
            cost = itemView.findViewById(R.id.cost);
            tax = itemView.findViewById(R.id.tax);
            taxValue = itemView.findViewById(R.id.tax_value);
            orderNumber = itemView.findViewById(R.id.order_number);


        }
    }

}
