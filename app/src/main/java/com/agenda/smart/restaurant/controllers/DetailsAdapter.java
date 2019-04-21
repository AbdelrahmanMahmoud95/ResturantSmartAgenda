package com.agenda.smart.restaurant.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.Details;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.views.OrderActivity;
import com.agenda.smart.restaurant.views.OrderDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Abdelrahman on 8/7/2018.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    Context context;
    Details details;
    String api_token;
    SharedPreferences dataSaver;
    int order_id;

    public DetailsAdapter(Context context, Details details) {
        this.context = context;
        this.details = details;

    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_view, parent, false);
        DetailsViewHolder detailsViewHolder = new DetailsViewHolder(view);
        return detailsViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        dataSaver = getDefaultSharedPreferences(context);
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        double cost = details.getOrder().getDetails().get(position).getCost();
        int quantity = details.getOrder().getDetails().get(position).getQuantity();
        double totalCost = cost * quantity;
        holder.itemName.setText(details.getOrder().getDetails().get(position).getMealName());
        holder.itemPrice.setText(String.valueOf(cost));
        holder.itemQuantity.setText(String.valueOf(quantity));
        holder.itemTotalPrice.setText(String.valueOf(totalCost));
        final int item_id = details.getOrder().getDetails().get(position).getId();
        holder.deleteMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("هل تريد حذف الوجبة؟").setCancelable(false)
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final ProgressDialog progressDialog = new ProgressDialog(context);
                                progressDialog.setMessage("جاري الحذف...");
                                progressDialog.show();
                                Log.e("TAG", "item_id " + item_id);
                                Service.Fetcher.getInstance().deleteItem(item_id, api_token).enqueue(new Callback<GeneralResponse>() {

                                    @Override
                                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                                        if (response.isSuccessful()) {

                                            GeneralResponse generalResponse = response.body();
                                            int status = generalResponse.getStatus();
                                            Log.e("TAG", "status " + status);
                                            if (status == 1) {
                                                Log.e("TAG", "isSuccessful ");
                                                progressDialog.dismiss();
                                                Toast.makeText(context, "تم الحذف", Toast.LENGTH_SHORT).show();
                                                order_id = details.getOrder().getId();
                                                Intent intent = new Intent(context, OrderDetailsActivity.class);
                                                intent.putExtra("order_id", order_id);
                                                context.startActivity(intent);
                                                ((Activity) context).finish();
                                            } else {
                                                Toast.makeText(context, "تم الحذف", Toast.LENGTH_SHORT).show();
                                                Intent intent1 = new Intent(context, OrderActivity.class);
                                                intent1.putExtra("order_id", order_id);
                                                context.startActivity(intent1);
                                                ((Activity) context).finish();

                                                Log.e("TAG", "notSuccessful ");
                                                progressDialog.dismiss();
                                                String message = "";
                                                for (int i = 0; i < response.body().getMessages().size(); i++) {
                                                    message = "";
                                                    message += response.body().getMessages().get(i);
                                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();


                                                }
                                            }
//                                            } else {
//                                                Log.e("TAG", "notSuccessful ");
//                                                progressDialog.dismiss();
//                                                String message = "";
//                                                for (int i = 0; i < response.body().getMessages().size(); i++) {
//                                                    message = "";
//                                                    message += response.body().getMessages().get(i);
//                                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//
//                                                }
//                                            }

                                        } else {
                                            progressDialog.dismiss();
                                            Log.e("TAG", "notSuccessful ");

                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                                        Log.e("TAG", "onFailure " + t.getMessage());
                                        progressDialog.dismiss();
                                        Toast.makeText(context, "حاول مره اخرى", Toast.LENGTH_LONG).show();


                                    }
                                });
                            }


                        }).setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return details.getOrder().getDetails().size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemPrice, itemTotalPrice;
        Button deleteMeal;

        public DetailsViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.name_cart_item);
            itemQuantity = itemView.findViewById(R.id.quantity_cart_item);
            itemPrice = itemView.findViewById(R.id.one_price_cart_item);
            itemTotalPrice = itemView.findViewById(R.id.total_cart_item);
            deleteMeal = itemView.findViewById(R.id.delete_cart_item);

        }
    }
}
