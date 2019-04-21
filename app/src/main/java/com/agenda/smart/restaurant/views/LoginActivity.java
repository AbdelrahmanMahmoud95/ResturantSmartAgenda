package com.agenda.smart.restaurant.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.GeneralResponse;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText phoneNumber, password;
    Button login, loginAdmin, loginKasher;
    SharedPreferences dataSaver;
    String api_token;
    int admin_id, wainter_id;
    TextView resturantName;
    boolean isAdmin = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneNumber = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        loginAdmin = findViewById(R.id.login_admin);
        loginKasher = findViewById(R.id.login_kasher);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        login.setOnClickListener(this);
        loginAdmin.setOnClickListener(this);
        loginKasher.setOnClickListener(this);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
        wainter_id = dataSaver.getInt(AppKeys.WAITER_ID, -1);
        admin_id = dataSaver.getInt(AppKeys.ADMIN_ID, -1);
        isAdmin = dataSaver.getBoolean(AppKeys.ISADMIN, false);
        resturantName = findViewById(R.id.resturant_name);
        getSetting();
        if (isAdmin == true && api_token != "") {
            Intent intent = new Intent(LoginActivity.this, ReportsActivity.class);
            startActivity(intent);
            finish();

        } else if (api_token != "" && isAdmin == false) {
            Intent intent = new Intent(LoginActivity.this, TablesActivity.class);
            startActivity(intent);
            finish();

        }

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

        if (view == login) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("جاري التحميل...");
            progressDialog.show();
            String phone = phoneNumber.getText().toString();
            String pass = password.getText().toString();
            Service.Fetcher.getInstance().loginWaiter(phone, pass).enqueue(new Callback<GeneralResponse>() {

                @Override
                public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                    if (response.isSuccessful()) {

                        GeneralResponse generalResponse = response.body();
                        int status = generalResponse.getStatus();

                        if (status == 1) {

                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(password.getWindowToken(), 0);

                            Log.e("TAG", "isSuccessful ");
                            progressDialog.dismiss();
                            Toast.makeText(getApplication(), "تم تسجيل الدخول", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, TablesActivity.class);
                            startActivity(intent);
                            finish();


                            dataSaver.edit()
                                    .putInt(AppKeys.WAITER_ID, generalResponse.getWaiterId())
                                    .apply();
                            dataSaver.edit()
                                    .putBoolean(AppKeys.ISADMIN, false)
                                    .apply();
                            dataSaver.edit()
                                    .putString(AppKeys.TOKEN_KEY, generalResponse.getApiToken())
                                    .apply();

                        } else {
                            Log.e("TAG", "notSuccessful ");
                            progressDialog.dismiss();
                            String message = "";
                            for (int i = 0; i < response.body().getMessages().size(); i++) {
                                message = "";
                                message += response.body().getMessages().get(i);
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

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
                    Toast.makeText(LoginActivity.this, "حاول مره اخرى", Toast.LENGTH_LONG).show();


                }
            });

        }
        if (loginAdmin == view) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("جاري التحميل...");
            progressDialog.show();
            String phone = phoneNumber.getText().toString();
            String pass = password.getText().toString();
            Service.Fetcher.getInstance().loginAdmin(phone, pass).enqueue(new Callback<GeneralResponse>() {

                @Override
                public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                    if (response.isSuccessful()) {

                        GeneralResponse generalResponse = response.body();
                        int status = generalResponse.getStatus();

                        if (status == 1) {

                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(password.getWindowToken(), 0);

                            Log.e("TAG", "isSuccessful ");
                            progressDialog.dismiss();
                            Toast.makeText(getApplication(), "تم تسجيل الدخول", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, ReportsActivity.class);
                            startActivity(intent);
                            finish();
                            Log.e("TAG", "token1 " + api_token);


                            dataSaver.edit()
                                    .putInt(AppKeys.ADMIN_ID, generalResponse.getAdminId())
                                    .apply();
                            dataSaver.edit()
                                    .putBoolean(AppKeys.ISADMIN, true)
                                    .apply();
                            dataSaver.edit()
                                    .putString(AppKeys.TOKEN_KEY, generalResponse.getApiToken())
                                    .apply();

                        } else {
                            Log.e("TAG", "notSuccessful ");
                            progressDialog.dismiss();
                            String message = "";
                            for (int i = 0; i < response.body().getMessages().size(); i++) {
                                message = "";
                                message += response.body().getMessages().get(i);
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

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
                    Toast.makeText(LoginActivity.this, "حاول مره اخرى", Toast.LENGTH_LONG).show();


                }
            });

        }
        if (loginKasher == view) {
            Intent intent = new Intent(LoginActivity.this,CashierActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();

        dialog.setTitle("إغلاق التطبيق");
        dialog.setMessage("هل انت متأكد انك تريد الخروج من التطبيق؟");

        dialog.show();
    }
}




