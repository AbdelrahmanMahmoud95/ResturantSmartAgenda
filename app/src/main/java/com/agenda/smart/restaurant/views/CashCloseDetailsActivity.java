package com.agenda.smart.restaurant.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.ItemsAdapter;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.CashCloseDetails;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.model.Meals;
import com.agenda.smart.restaurant.model.Settings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class CashCloseDetailsActivity extends AppCompatActivity {
    int cashier_id;
    CashCloseDetails details;
    String api_token;
    SharedPreferences dataSaver;
    TextView name, date, time, total, totalCash, totalBack, totalNet,resturantName;
    Button closeCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_close_details);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        total = findViewById(R.id.total);
        totalCash = findViewById(R.id.total_cash);
        totalBack = findViewById(R.id.total_net);
        totalNet = findViewById(R.id.total_back);
        closeCash = findViewById(R.id.cash_close);
        resturantName = findViewById(R.id.resturant_name);

        Intent intent = getIntent();
        cashier_id = intent.getIntExtra("id", 0);
        getCashDetails();
        getSetting();
        closeCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCloseCash();
            }
        });
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


    public void getCashDetails() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري التحميل...");
        progressDialog.show();
        Log.e("TAG", "category_id " + cashier_id);

        Service.Fetcher.getInstance().getCashCloseDetails(cashier_id, api_token).enqueue(new Callback<CashCloseDetails>() {

            @Override
            public void onResponse(Call<CashCloseDetails> call, Response<CashCloseDetails> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    details = response.body();
                    name.setText(details.getCashier().getName());

                    Date currentTime = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(currentTime);
                    date.setText(formattedDate);

                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);
                    time.setText(currentDateTimeString);
                    total.setText(String.valueOf(details.getCashier().getShiftCost().getNetCost()));
                    totalCash.setText(String.valueOf(details.getCashier().getShiftCost().getCashCost()));
                    totalNet.setText(String.valueOf(details.getCashier().getShiftCost().getVisaCost()));
                    totalBack.setText(String.valueOf(details.getCashier().getShiftCost().getReturnsCost()));
                }
            }

            @Override
            public void onFailure(Call<CashCloseDetails> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("TAG ", "onFailure");
                Toast.makeText(CashCloseDetailsActivity.this, "حاول مره اخرى", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void updateCloseCash() {


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري التحميل...");
        progressDialog.show();

        Service.Fetcher.getInstance().updateCashClose(cashier_id, api_token).enqueue(new Callback<GeneralResponse>() {

            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(CashCloseDetailsActivity.this, "تم التحصيل بنجاح", Toast.LENGTH_LONG).show();
                    total.setText("0");
                    totalBack.setText("0");
                    totalCash.setText("0");
                    totalNet.setText("0");
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Log.e("TAG", "onFailure " + t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(CashCloseDetailsActivity.this, "حاول مره اخرى", Toast.LENGTH_LONG).show();


            }
        });


    }
}
