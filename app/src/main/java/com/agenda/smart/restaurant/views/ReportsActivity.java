package com.agenda.smart.restaurant.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ReportsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView categoriesReports, clientReports, taxesReports,
            orderDaily, orderInterval, profitsInterval, employees, resturantName, cashClose;
    SharedPreferences dataSaver;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        categoriesReports = findViewById(R.id.categories);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        taxesReports = findViewById(R.id.taxes);
        resturantName = findViewById(R.id.resturant_name);
        orderDaily = findViewById(R.id.order_daily);
        employees = findViewById(R.id.employees);
        cashClose = findViewById(R.id.shift_close);
        profitsInterval = findViewById(R.id.profits_interval);
        orderInterval = findViewById(R.id.orders_interval);
        clientReports = findViewById(R.id.clients);
        exit = findViewById(R.id.exit);
        orderDaily.setOnClickListener(this);
        profitsInterval.setOnClickListener(this);
        orderInterval.setOnClickListener(this);
        categoriesReports.setOnClickListener(this);
        clientReports.setOnClickListener(this);
        cashClose.setOnClickListener(this);
        exit.setOnClickListener(this);
        employees.setOnClickListener(this);
        taxesReports.setOnClickListener(this);
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

        if (view == categoriesReports) {
            Intent intent = new Intent(ReportsActivity.this, CategoriesReportsActivity.class);
            startActivity(intent);
        }
        if (view == orderInterval) {
            Intent intent = new Intent(ReportsActivity.this, ReportsOrderIntervalActivity.class);
            startActivity(intent);
        }
        if (view == orderDaily) {
            Intent intent = new Intent(ReportsActivity.this, ReportsOrderDailyActivity.class);
            startActivity(intent);
        }

        if (view == taxesReports) {
            Intent intent = new Intent(ReportsActivity.this, TaxesReportsActivity.class);
            startActivity(intent);
        }

        if (view == profitsInterval) {
            Intent intent = new Intent(ReportsActivity.this, EarningReportIntervalActivity.class);
            startActivity(intent);
        }

        if (view == employees) {
            Intent intent = new Intent(ReportsActivity.this, ReportsEmployesActivity.class);
            startActivity(intent);
        }

        if (view == clientReports) {
            Intent intent = new Intent(ReportsActivity.this, ReportsClientActivity.class);
            startActivity(intent);
        }

        if (view == cashClose) {
            Intent intent = new Intent(ReportsActivity.this, CashCloseActivity.class);
            startActivity(intent);
        }

        if (view == exit) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            dataSaver.edit()
                    .putInt(AppKeys.ADMIN_ID, -1)
                    .apply();
            dataSaver.edit()
                    .putBoolean(AppKeys.ISADMIN, true)
                    .apply();
            dataSaver.edit()
                    .putString(AppKeys.TOKEN_KEY, "")
                    .apply();
            finish();
        }
    }
}
