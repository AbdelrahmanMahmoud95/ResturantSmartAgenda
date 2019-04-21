package com.agenda.smart.restaurant.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.model.Meals;
import com.agenda.smart.restaurant.views.ItemDetailsActivity;
import com.agenda.smart.restaurant.views.LoginActivity;
import com.agenda.smart.restaurant.views.TablesActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by Abdelrahman on 7/9/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    Context context;
    Meals meals;
    SharedPreferences dataSaver;
    ItemSizeAdapter itemSizeAdapter;
    GridLayoutManager layoutManager;
    String api_token;
    int table_id;
    int meal_id;
    int item_size = -1;


    public ItemsAdapter(Context context, Meals meals) {
        this.context = context;
        this.meals = meals;

    }

    @Override
    public ItemsAdapter.ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ItemsAdapter.ItemsViewHolder itemsViewHolder = new ItemsAdapter.ItemsViewHolder(view);
        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemsAdapter.ItemsViewHolder holder, final int position) {
        dataSaver = getDefaultSharedPreferences(context);

        Picasso.with(context).load(String.valueOf(meals.getMeals().get(position).getImage())).into(holder.itemImage);
        holder.itemName.setText(meals.getMeals().get(position).getName());


        if (meals.getMeals().get(position).getPrice() == null) {
            holder.onlySize.setVisibility(View.GONE);

        } else {
            holder.onlySize.setText(String.valueOf(meals.getMeals().get(position).getPrice()));

        }
        meal_id = meals.getMeals().get(position).getId();
        holder.onlySize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postOrder();
                holder.onlySize.setChecked(false);
            }
        });


        layoutManager = new GridLayoutManager(context, 1);
        holder.sizeRecycler.setLayoutManager(layoutManager);
        itemSizeAdapter = new ItemSizeAdapter(context, meals.getMeals().get(position).getSizes());
        holder.sizeRecycler.setAdapter(itemSizeAdapter);
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
                Log.e("TAG", "api_token " + api_token);
                Log.e("TAG", "table_id " + table_id);
                Log.e("TAG", "meal_id " + meal_id);
                Log.e("TAG", "quantityNumber " + quantityNumber);
                Log.e("TAG", "item_size " + item_size);

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
        return meals.getMeals().size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        RelativeLayout layout;
        RecyclerView sizeRecycler;
        RadioButton onlySize;

        public ItemsViewHolder(View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            layout = itemView.findViewById(R.id.meals_layout);
            sizeRecycler = itemView.findViewById(R.id.sizes_recycler);
            onlySize = itemView.findViewById(R.id.only_size);

        }
    }

}
