package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.ReportsOrderDaily;
import com.agenda.smart.restaurant.model.ReportsTaxes;

/**
 * Created by User on 23/10/2018.
 */

public class ReportsOrderDailyAdapter extends RecyclerView.Adapter<ReportsOrderDailyAdapter.ReportsOrderDailyViewHolder> {

    Context context;
    ReportsOrderDaily orderDaily;


    public ReportsOrderDailyAdapter(Context context, ReportsOrderDaily orderDaily) {
        this.context = context;
        this.orderDaily = orderDaily;

    }

    @Override
    public ReportsOrderDailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_daily_report, parent, false);
        ReportsOrderDailyViewHolder reportsOrderDailyViewHolder = new ReportsOrderDailyViewHolder(view);
        return reportsOrderDailyViewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportsOrderDailyViewHolder holder, final int position) {
        holder.costWithTax.setText(String.valueOf(orderDaily.getOrders().get(position).getCostWithTax()));
        holder.cost.setText(String.valueOf(orderDaily.getOrders().get(position).getCost()));
        holder.orderNumber.setText(String.valueOf(orderDaily.getOrders().get(position).getId()));
        String cashier_name = orderDaily.getOrders().get(position).getCashier().getName();
        if (cashier_name == null) {
            holder.cashierName.setText("لايوجد");

        } else {
            holder.cashierName.setText(String.valueOf(orderDaily.getOrders().get(position).getCashier().getName()));
        }
        holder.date.setText(orderDaily.getOrders().get(position).getCreatedAt());
        holder.orderTyp.setText(orderDaily.getOrders().get(position).getTypeName());
        holder.status.setText(orderDaily.getOrders().get(position).getStatusName());
    }

    @Override
    public int getItemCount() {
        return orderDaily.getOrders().size();
    }

    public class ReportsOrderDailyViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber, cost, costWithTax, cashierName, status, date, orderTyp;


        public ReportsOrderDailyViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            costWithTax = itemView.findViewById(R.id.cost_with_tax);
            cost = itemView.findViewById(R.id.cost);
            cashierName = itemView.findViewById(R.id.cashier_name);
            status = itemView.findViewById(R.id.status);
            orderTyp = itemView.findViewById(R.id.order_typ);
            orderNumber = itemView.findViewById(R.id.order_number);


        }
    }


}
