package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.ReportsEarning;
import com.agenda.smart.restaurant.model.ReportsOrderDaily;

/**
 * Created by User on 24/10/2018.
 */

public class EarningReportsAdapter  extends RecyclerView.Adapter<EarningReportsAdapter.EarningReportsViewHolder> {

    Context context;
    ReportsEarning reportsEarning;



    public EarningReportsAdapter(Context context, ReportsEarning reportsEarning) {
        this.context = context;
        this.reportsEarning = reportsEarning;

    }

    @Override
    public EarningReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.earning_reports, parent, false);
        EarningReportsViewHolder earningReportsViewHolder = new EarningReportsViewHolder(view);
        return earningReportsViewHolder;
    }

    @Override
    public void onBindViewHolder(final EarningReportsViewHolder holder, final int position) {
        holder.cost.setText(String.valueOf(reportsEarning.getPurchases().get(position).getCost()));
        holder.date.setText(reportsEarning.getPurchases().get(position).getCreatedAt());
        holder.name.setText(reportsEarning.getPurchases().get(position).getTitle());
        holder.responsor.setText(reportsEarning.getPurchases().get(position).getAdmin().getName());

    }


    @Override
    public int getItemCount() {
        return reportsEarning.getPurchases().size();
    }

    public class EarningReportsViewHolder extends RecyclerView.ViewHolder {
        TextView  cost, responsor, name, date;


        public EarningReportsViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            responsor = itemView.findViewById(R.id.responsor);
            cost = itemView.findViewById(R.id.cost);
            name = itemView.findViewById(R.id.name);





        }
    }


}
