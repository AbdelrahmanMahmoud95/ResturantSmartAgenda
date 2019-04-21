package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.CashClose;
import com.agenda.smart.restaurant.model.ReportsOrderDaily;
import com.agenda.smart.restaurant.views.CashCloseDetailsActivity;

/**
 * Created by Abdelrahman on 11/12/2018.
 */


public class CashCloseAdapter extends RecyclerView.Adapter<CashCloseAdapter.CashCloseViewHolder> {

    Context context;
    CashClose cashClose;


    public CashCloseAdapter(Context context, CashClose cashClose) {
        this.context = context;
        this.cashClose = cashClose;

    }

    @Override
    public CashCloseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cash_close, parent, false);
        CashCloseViewHolder cashCloseViewHolder = new CashCloseViewHolder(view);
        return cashCloseViewHolder;
    }

    @Override
    public void onBindViewHolder(final CashCloseViewHolder holder, final int position) {
        holder.name.setText(String.valueOf(cashClose.getCashiers().get(position).getName()));
        if (cashClose.getCashiers().get(position).getFirstOrder() == null) {
            holder.firstDate.setText("-");
        } else {
            holder.firstDate.setText(cashClose.getCashiers().get(position).getFirstOrder().getCreatedAt());
        }
        if (cashClose.getCashiers().get(position).getLastOrder() == null) {
            holder.lastDate.setText("-");
        } else {
            holder.lastDate.setText(cashClose.getCashiers().get(position).getLastOrder().getCreatedAt());
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = cashClose.getCashiers().get(position).getId();
                Intent intent = new Intent(context, CashCloseDetailsActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cashClose.getCashiers().size();
    }

    public class CashCloseViewHolder extends RecyclerView.ViewHolder {
        TextView name, firstDate, lastDate;
        ImageView view;

        public CashCloseViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            firstDate = itemView.findViewById(R.id.first_time_order);
            lastDate = itemView.findViewById(R.id.last_time_order);
            view = itemView.findViewById(R.id.view);


        }
    }


}
