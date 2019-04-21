package com.agenda.smart.restaurant.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.OrderAdapter;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.CheckOut;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class OrderActivity extends AppCompatActivity {
    OrderAdapter orderAdapter;
    CheckOut checkOut;
    RecyclerView orderRecyclerView;
    GridLayoutManager layoutManager;
    SharedPreferences dataSaver;
    String api_token;
    TextView resturantName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        dataSaver = getDefaultSharedPreferences(OrderActivity.this);
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        orderRecyclerView = findViewById(R.id.order_recycler_view);
        layoutManager = new GridLayoutManager(this, 1);
        orderRecyclerView.setLayoutManager(layoutManager);
        resturantName = findViewById(R.id.resturant_name);
        getOrdersNumber();
        getSetting();
    }


    public void getSetting() {

        Service.Fetcher.getInstance().getSetting().enqueue(new Callback<Settings>() {

            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.isSuccessful()) {
                    Settings settings = response.body();
                    resturantName.setText(settings.getSetting().getResturantName());

                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                Log.e("TAG ", "onFailure");
            }

        });
    }


    public void getOrdersNumber() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري التحميل...");
        progressDialog.show();

        Service.Fetcher.getInstance().getOrder(api_token).enqueue(new Callback<CheckOut>() {

            @Override
            public void onResponse(Call<CheckOut> call, Response<CheckOut> response) {
                Log.e("TAG", "api_token " + api_token);

                if (response.isSuccessful()) {
                    Log.e("TAG", "isSuccessful");

                    progressDialog.dismiss();
                    checkOut = response.body();
                    orderAdapter = new OrderAdapter(OrderActivity.this, checkOut);
                    orderRecyclerView.setAdapter(orderAdapter);

                } else {
                    Log.e("TAG", "notSuccessful");

                }
            }


            @Override
            public void onFailure(Call<CheckOut> call, Throwable t) {
                progressDialog.dismiss();
               //  Toast.makeText(OrderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
    public void gotoCartActivity(View view) {
        Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
        startActivity(intent);

    }

}
