package com.agenda.smart.restaurant.controllers;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.CheckOut;
import com.agenda.smart.restaurant.model.Details;
import com.agenda.smart.restaurant.views.OrderDetailsActivity;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


/**
 * Created by Abdelrahman on 8/7/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    CheckOut checkOut;
    Context context;
    int orderId;
    SharedPreferences dataSaver;
    String total_cost;


    public OrderAdapter(Context context, CheckOut checkOut) {
        this.context = context;
        this.checkOut = checkOut;

    }


    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, final int position) {
        dataSaver = getDefaultSharedPreferences(context);
        holder.tableNumber.setText(" رقم الطاولة " + String.valueOf(checkOut.getOrders().get(position).getTableId()));
        holder.tableNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderId = checkOut.getOrders().get(position).getId();
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("order_id", orderId);
                context.startActivity(intent);
                ((Activity) context).finish();

            }
        });
        total_cost = String.valueOf(checkOut.getOrders().get(position).getCostWithTax());
        dataSaver.edit().
                putString(AppKeys.TOTAL_COST, total_cost)
                .apply();
    }


    @Override
    public int getItemCount() {
        return checkOut.getOrders().size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tableNumber;


        public OrderViewHolder(View itemView) {
            super(itemView);
            tableNumber = itemView.findViewById(R.id.table_number);


        }
    }
}
