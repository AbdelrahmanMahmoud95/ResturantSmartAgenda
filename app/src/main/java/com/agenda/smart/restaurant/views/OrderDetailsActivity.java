package com.agenda.smart.restaurant.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.DetailsAdapter;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.Details;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    String api_token;
    int order_id;
    Details details;
    SharedPreferences dataSaver;
    DetailsAdapter detailsAdapter;
    GridLayoutManager layoutManager;
    RecyclerView cartRecyclerView;
    TextView taxValue, orderTotalCost;
    Button orderNow;
    TextView resturantName;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent intent = getIntent();
        order_id = intent.getIntExtra("order_id", 0);
        dataSaver = getDefaultSharedPreferences(this);
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        taxValue = findViewById(R.id.tax_value);
        orderTotalCost = findViewById(R.id.order_total_cost);
        orderNow = findViewById(R.id.order_now);
        cartRecyclerView = findViewById(R.id.cart_list);
        resturantName = findViewById(R.id.resturant_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.drawer_toggle_color));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        layoutManager = new GridLayoutManager(OrderDetailsActivity.this, 1);
        cartRecyclerView.setLayoutManager(layoutManager);
        orderNow.setOnClickListener(this);

        getOrderDetails();
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


    public void getOrderDetails() {
        Service.Fetcher.getInstance().getDetails(order_id, api_token).enqueue(new Callback<Details>() {

            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                Log.e("ADAPTER", "api_token " + api_token);
                Log.e("ADAPTER", "order_id " + order_id);
                if (response.isSuccessful()) {
                    details = response.body();
                    detailsAdapter = new DetailsAdapter(OrderDetailsActivity.this, details);
                    cartRecyclerView.setAdapter(detailsAdapter);
                    double tax = details.getOrder().getTax();
                    double cost = details.getOrder().getCost();
                    double totalCost = cost * (1 + (tax / 100));
                    taxValue.setText(" تشمل قيمة الضريبة المضافة " + String.valueOf(details.getOrder().getTax()) + "%");
                    String total_cost = String.valueOf(totalCost);
                    orderTotalCost.setText(total_cost);

                }
            }
            @Override
            public void onFailure(Call<Details> call, Throwable t) {

                Log.e("TAG ", "onFailure");
            }

        });

    }

    @Override
    public void onClick(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري طلب الاوردر...");
        progressDialog.show();
        Log.e("TAG", "orderID " + order_id);
        Service.Fetcher.getInstance().checkOut(order_id, api_token).enqueue(new Callback<GeneralResponse>() {

            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {

                    GeneralResponse generalResponse = response.body();
                    int status = generalResponse.getStatus();

                    if (status == 1) {

                        Log.e("TAG", "isSuccessful ");
                        progressDialog.dismiss();
                        Toast.makeText(OrderDetailsActivity.this, "تم طلب الاوردر", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OrderDetailsActivity.this, OrderActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Log.e("TAG", "notSuccessful ");
                        progressDialog.dismiss();
                        String message = "";
                        for (int i = 0; i < response.body().getMessages().size(); i++) {
                            message = "";
                            message += response.body().getMessages().get(i);
                            Toast.makeText(OrderDetailsActivity.this, message, Toast.LENGTH_LONG).show();

                        }
                    }
                } else {
                    progressDialog.dismiss();
                    Log.e("TAG", "notSuccessful ");

                }

            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Log.e("TAG", "onFailure " + t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(OrderDetailsActivity.this, "حاول مره اخرى", Toast.LENGTH_LONG).show();


            }
        });
    }

    public void gotoCartActivity(View view) {
        Intent intent = new Intent(OrderDetailsActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.log_out) {
            intent = new Intent(this, LoginActivity.class);

            dataSaver.edit()
                    .putInt(AppKeys.WAITER_ID, -1)
                    .apply();
            dataSaver.edit()
                    .putString(AppKeys.TOKEN_KEY, "")
                    .apply();
            finish();
        } else if (id == R.id.categories) {
            intent = new Intent(this, CategoriesActivity.class);
        } else if (id == R.id.tables) {
            intent = new Intent(this, TablesActivity.class);
        }
        startActivity(intent);
        finish();


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
