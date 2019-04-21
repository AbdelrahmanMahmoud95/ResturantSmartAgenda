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
import android.widget.RadioButton;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.model.Meals;
import com.agenda.smart.restaurant.model.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by User on 01/11/2018.
 */

public class ItemSizeAdapter extends RecyclerView.Adapter<ItemSizeAdapter.ItemSizeViewHolder> {
    Context context;
    SharedPreferences dataSaver;
    String api_token;
    int table_id;
    int meal_id;
    int item_size;
    List<Size> sizes;

    public ItemSizeAdapter(Context context, List<Size> sizes) {
        this.context = context;
        this.sizes = sizes;

    }

    @Override
    public ItemSizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_sizes, parent, false);
        ItemSizeViewHolder itemsViewHolder = new ItemSizeViewHolder(view);
        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemSizeViewHolder holder, final int position) {
        dataSaver = getDefaultSharedPreferences(context);
        meal_id = sizes.get(position).getMealId();
        item_size = sizes.get(position).getId();
        holder.small.setText(sizes.get(position).getName() + String.valueOf(sizes.get(position).getPrice()));
        holder.small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "تم اختيار حجم " + sizes.get(position).getName(), Toast.LENGTH_LONG).show();
                postOrder();
                holder.small.setChecked(false);
            }
        });
    }

    public void postOrder() {

        final AlertDialog builder = new AlertDialog.Builder(context).create();

        final View v = LayoutInflater.from(context).inflate(R.layout.order_dialog, null);

        Button submit = v.findViewById(R.id.submit);
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        table_id = dataSaver.getInt(AppKeys.TABLE_ID, 0);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText quantity = v.findViewById(R.id.quantity);
                String quantityNumber = quantity.getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("جاري الاضافة...");
                progressDialog.show();
//                Log.e("TAG", "api_token " + api_token);
//                Log.e("TAG", "table_id " + table_id);
//                Log.e("TAG", "meal_id " + meal_id);
//                Log.e("TAG", "quantityNumber " + quantityNumber);

                Service.Fetcher.getInstance().postOrder(api_token, table_id, meal_id, quantityNumber, item_size).enqueue(new Callback<GeneralResponse>() {

                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.isSuccessful()) {

                            GeneralResponse generalResponse = response.body();
                            int status = generalResponse.getStatus();

                            if (status == 1) {
                                builder.dismiss();
                                Log.e("TAG", "isSuccessful ");
                                progressDialog.dismiss();
                                Toast.makeText(context, "تم الاضافة الي السلة", Toast.LENGTH_SHORT).show();


                            } else {
                                Log.e("TAG", "notSuccessful ");
                                progressDialog.dismiss();
                                String message = "";
                                for (int i = 0; i < response.body().getMessages().size(); i++) {
                                    message = "";
                                    message += response.body().getMessages().get(i);
                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                                }
                            }
                        } else {
                            progressDialog.dismiss();
                            Log.e("TAG", "notSuccessful ");
                            Toast.makeText(context, "notSuccessful", Toast.LENGTH_LONG).show();

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
        });
        builder.setView(v);
        // dialog.setCancelable(false);
        builder.show();
    }


    @Override
    public int getItemCount() {
        return sizes.size();
    }

    public class ItemSizeViewHolder extends RecyclerView.ViewHolder {
        RadioButton small;

        public ItemSizeViewHolder(View itemView) {
            super(itemView);
            small = itemView.findViewById(R.id.small);
        }
    }
}
