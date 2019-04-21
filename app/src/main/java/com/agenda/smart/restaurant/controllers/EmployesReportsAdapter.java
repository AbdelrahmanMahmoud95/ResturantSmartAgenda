package com.agenda.smart.restaurant.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.ReportsEarning;
import com.agenda.smart.restaurant.model.ReportsEmployees;

/**
 * Created by User on 24/10/2018.
 */

public class EmployesReportsAdapter extends RecyclerView.Adapter<EmployesReportsAdapter.EmployesReportsViewHolder> {

    Context context;
    ReportsEmployees reportsEmployees;



    public EmployesReportsAdapter(Context context,  ReportsEmployees reportsEmployees) {
        this.context = context;
        this.reportsEmployees = reportsEmployees;

    }

    @Override
    public EmployesReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employe_report, parent, false);
        EmployesReportsViewHolder employesReportsViewHolder = new EmployesReportsViewHolder(view);
        return employesReportsViewHolder;
    }

    @Override
    public void onBindViewHolder(final EmployesReportsViewHolder holder, final int position) {
        holder.salesCost.setText(String.valueOf(reportsEmployees.getEmployees().get(position).getSalesCost()));
        holder.orderNumber.setText(String.valueOf(reportsEmployees.getEmployees().get(position).getOrdersCount()));
        holder.name.setText(reportsEmployees.getEmployees().get(position).getName());
        holder.type.setText(reportsEmployees.getEmployees().get(position).getTypeName());
        holder.phone.setText(String.valueOf(reportsEmployees.getEmployees().get(position).getPhone()));
        holder.salary.setText(String.valueOf(reportsEmployees.getEmployees().get(position).getSalary()));

    }


    @Override
    public int getItemCount() {
        return reportsEmployees.getEmployees().size();
    }

    public class EmployesReportsViewHolder extends RecyclerView.ViewHolder {
        TextView salesCost, orderNumber, type, salary, phone,name;


        public EmployesReportsViewHolder(View itemView) {
            super(itemView);
            salary = itemView.findViewById(R.id.salary);
            phone = itemView.findViewById(R.id.phone);
            orderNumber = itemView.findViewById(R.id.order_number);
            type = itemView.findViewById(R.id.type);
            salesCost = itemView.findViewById(R.id.sales_cost);
            name = itemView.findViewById(R.id.name);





        }
    }


}
