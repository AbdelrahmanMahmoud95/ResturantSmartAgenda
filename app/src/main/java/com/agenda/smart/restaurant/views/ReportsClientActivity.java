package com.agenda.smart.restaurant.views;

import android.app.FragmentTransaction;
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

import com.agenda.smart.restaurant.DateDialog;
import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.ClientsReportsAdapter;
import com.agenda.smart.restaurant.controllers.EmployesReportsAdapter;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.ReportsClient;
import com.agenda.smart.restaurant.model.ReportsEmployees;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ReportsClientActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    TextView dateFrom, dateTo,resturantName;
    Button search;
    SharedPreferences dataSaver;
    ClientsReportsAdapter clientsReportsAdapter;
    RecyclerView recyclerClient;
    GridLayoutManager LayoutManager;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    String api_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_client);
        dateFrom = findViewById(R.id.date_from);
        dateTo = findViewById(R.id.date_to);
        search = findViewById(R.id.search);
        resturantName = findViewById(R.id.resturant_name);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        recyclerClient = findViewById(R.id.client_recycle);
        LayoutManager = new GridLayoutManager(this, 1);
        recyclerClient.setLayoutManager(LayoutManager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.drawer_toggle_color));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);

        search.setOnClickListener(this);
        dateFrom.setOnClickListener(this);
        dateTo.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (view == dateFrom) {
            DateDialog dateDialog = new DateDialog(view);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            dateDialog.show(fragmentTransaction, "Date Picker");
        }

        if (view == dateTo) {
            DateDialog dateDialog = new DateDialog(view);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            dateDialog.show(fragmentTransaction, "Date Picker");
        }

        if (view == search) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("جاري التحميل...");
            progressDialog.show();
            String date_from = dateFrom.getText().toString();
            String date_to = dateTo.getText().toString();
            Log.e("TAG", "date_to " + date_to);
            Log.e("TAG", "date_from " + date_from);
            Log.e("TAG", "api_token " + api_token);
            if (date_from.equals("التاريخ من")) {
                date_from = "";
            }
            if (date_to.equals("التاريخ الي")) {
                date_to = "";
            }

            Log.e("TAG", "date_to " + date_to);
            Log.e("TAG", "date_from " + date_from);
            Service.Fetcher.getInstance().getReportClient(api_token, date_from, date_to).enqueue(new Callback<ReportsClient>() {
                @Override
                public void onResponse(Call<ReportsClient> call, Response<ReportsClient> response) {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        ReportsClient client;
                        client = response.body();

                        if (client.getCustomers().size() == 0) {
                            Toast.makeText(ReportsClientActivity.this, "لا يوجد تقارير لهذه الفترة", Toast.LENGTH_SHORT).show();
                        }

                        clientsReportsAdapter = new ClientsReportsAdapter(ReportsClientActivity.this, client);
                        recyclerClient.setAdapter(clientsReportsAdapter);

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(ReportsClientActivity.this, "يجب ان تكون مسجل الدخول اولا", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ReportsClient> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.e("TAG ", "onFailure" + t.getMessage());
                    Toast.makeText(ReportsClientActivity.this, "خطأ", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.nav_logout) {
            intent = new Intent(this, LoginActivity.class);
            dataSaver.edit()
                    .putInt(AppKeys.ADMIN_ID, -1)
                    .apply();
            dataSaver.edit()
                    .putBoolean(AppKeys.ISADMIN, true)
                    .apply();
            dataSaver.edit()
                    .putString(AppKeys.TOKEN_KEY, "")
                    .apply();
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_categories) {
            intent = new Intent(this, CategoriesReportsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orderInterval) {
            intent = new Intent(this, ReportsOrderIntervalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orderDaily) {
            intent = new Intent(this, ReportsOrderDailyActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_earning) {
            intent = new Intent(this, EarningReportIntervalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tax) {
            intent = new Intent(this, TaxesReportsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_employees) {
            intent = new Intent(this, ReportsEmployesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_clients) {
            intent = new Intent(this, ReportsClientActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_cash) {
            intent = new Intent(this, CashCloseActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }}
