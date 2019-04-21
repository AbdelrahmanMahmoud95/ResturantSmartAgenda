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
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.EarningReportsAdapter;
import com.agenda.smart.restaurant.controllers.ReportsOrderDailyAdapter;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.ReportsEarning;
import com.agenda.smart.restaurant.model.ReportsOrderDaily;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class EarningReportIntervalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences dataSaver;
    EarningReportsAdapter earningReportsAdapter;
    RecyclerView recyclerEarning;
    GridLayoutManager LayoutManager;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    String api_token;
    TextView purchase, earningNet, totalStorge, resturantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_report_interval);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        purchase = findViewById(R.id.purchase);
        earningNet = findViewById(R.id.earning);
        totalStorge = findViewById(R.id.total_storge);
        resturantName = findViewById(R.id.resturant_name);
        recyclerEarning = findViewById(R.id.earning_recycler);
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

        LayoutManager = new GridLayoutManager(this, 1);
        recyclerEarning.setLayoutManager(LayoutManager);
        getEarningReport();
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


    public void getEarningReport() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري التحميل...");
        progressDialog.show();

        Service.Fetcher.getInstance().getReportEarningInterval(api_token).enqueue(new Callback<ReportsEarning>() {

            @Override
            public void onResponse(Call<ReportsEarning> call, Response<ReportsEarning> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    ReportsEarning earning;
                    earning = response.body();
                    totalStorge.setText(String.valueOf(earning.getEarning()));
                    earningNet.setText(String.valueOf(earning.getEarningsCost()));
                    purchase.setText(String.valueOf(earning.getPurchasesCost()));
                    earningReportsAdapter = new EarningReportsAdapter(EarningReportIntervalActivity.this, earning);
                    recyclerEarning.setAdapter(earningReportsAdapter);

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(EarningReportIntervalActivity.this, "يجب ان تكون مسجل الدخول اولا", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ReportsEarning> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("TAG ", "onFailure");
                Toast.makeText(EarningReportIntervalActivity.this, "خطأ", Toast.LENGTH_SHORT).show();
            }

        });
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
    }
}

