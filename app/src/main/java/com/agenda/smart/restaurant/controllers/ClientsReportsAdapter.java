package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.ReportsClient;
import com.agenda.smart.restaurant.model.ReportsEmployees;

/**
 * Created by User on 29/10/2018.
 */

public class ClientsReportsAdapter extends RecyclerView.Adapter<ClientsReportsAdapter.ClientReportsViewholder> {
    Context context;
    ReportsClient reportsClient;

    public ClientsReportsAdapter(Context context, ReportsClient reportsClient) {
        this.context = context;
        this.reportsClient = reportsClient;

    }

    @Override
    public ClientsReportsAdapter.ClientReportsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_reports, parent, false);
        ClientReportsViewholder clientReportsViewholder = new ClientReportsViewholder(view);
        return clientReportsViewholder;
    }

    @Override
    public void onBindViewHolder(ClientsReportsAdapter.ClientReportsViewholder holder, int position) {
        double returnCost = reportsClient.getCustomers().get(position).getReturnsCost();
        double sales = reportsClient.getCustomers().get(position).getSalesCost();
        holder.salesCost.setText(String.valueOf(sales));
        holder.returns.setText(String.valueOf(returnCost));
        holder.name.setText(reportsClient.getCustomers().get(position).getName());
        holder.address.setText(reportsClient.getCustomers().get(position).getAddress());
        holder.phone.setText(String.valueOf(reportsClient.getCustomers().get(position).getPhone()));
        double netSalary = sales - returnCost ;
        holder.netSalary.setText(String.valueOf(netSalary));
    }

    @Override
    public int getItemCount() {
        return reportsClient.getCustomers().size();
    }

    public class ClientReportsViewholder extends RecyclerView.ViewHolder {
        TextView salesCost, returns, address, netSalary, phone, name;

        public ClientReportsViewholder(View itemView) {
            super(itemView);
            returns = itemView.findViewById(R.id.returns);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            netSalary = itemView.findViewById(R.id.net);
            salesCost = itemView.findViewById(R.id.sales_cost);
            name = itemView.findViewById(R.id.name);
        }
    }
}
