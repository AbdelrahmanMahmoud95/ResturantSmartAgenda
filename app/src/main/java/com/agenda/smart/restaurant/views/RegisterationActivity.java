package com.agenda.smart.restaurant.views;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.ChooseDialog;
import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.City;
import com.agenda.smart.restaurant.model.Region;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class RegisterationActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnDismissListener {
    EditText email, phone, password, username, address;
    TextView city, region;
    Button submit;
    RadioGroup addressSelections;
    LinearLayout cityLinear, regionLinear;
    int address_type = 0;
    int city_id = 0;
    int region_id = 0;
    int userActive = 1;
    int userDelete = 0;
    double lat, lon;
    SharedPreferences dataSaver;
    boolean isCityDialog, isRegionDialog;
    String city_name, region_name;
    private static final String Fine_location = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String coarse_Location = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int External_Permission_Request_code = 0505;
    private boolean PermissionGranted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        cityLinear = findViewById(R.id.linear_city);
        city = findViewById(R.id.city);
        regionLinear = findViewById(R.id.linear_region);
        region = findViewById(R.id.region);
        email = findViewById(R.id.email);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.text_address);
        submit = findViewById(R.id.submit);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        regionLinear.setOnClickListener(this);
        cityLinear.setOnClickListener(this);

        addressSelections = (RadioGroup) findViewById(R.id.address_selection);
        address.setVisibility(View.GONE);
        getLocationPermission();
        addressSelections.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.gps_selection) {
                    address_type = 2;
                    address.setVisibility(View.GONE);


                } else if (i == R.id.enter_address_selection) {
                    address.setVisibility(View.VISIBLE);
                    address_type = 1;
                }
            }
        });

    }


    private void getLocationPermission() {
        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};


        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                coarse_Location) == PackageManager.PERMISSION_GRANTED) {
            PermissionGranted = true;


            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    Fine_location) == PackageManager.PERMISSION_GRANTED) {
                PermissionGranted = true;


            } else {
                ActivityCompat.requestPermissions(this, permission, External_Permission_Request_code);
            }
        } else {
            ActivityCompat.requestPermissions(this, permission, External_Permission_Request_code);
        }

    }

    @Override
    public void onClick(View view) {
        if (view == cityLinear) {
            dataSaver.edit()
                    .putInt(AppKeys.CITY_ID, 0)
                    .apply();

            city_name = "";
            city_id = 0;
            isCityDialog = true;
            region.setText("");
            final ChooseDialog dialog = new ChooseDialog(this, "اختر المدينة");
            dialog.setOnDismissListener(this);
            dialog.show();
            Service.Fetcher.getInstance().getCities().enqueue(new Callback<List<City>>() {
                @Override
                public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                    if (response.isSuccessful()) {
                        dialog.showCitiesList(response.body());

                    } else {
                        dialog.dismiss();
                        Toast.makeText(RegisterationActivity.this, "notSuccessful", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
        if (view == regionLinear) {
            dataSaver.edit()
                    .putInt(AppKeys.REGION_ID, 0)
                    .apply();

            if (city_id == 0) {
                Toast.makeText(this, "برجاء اختيار المدينة اولا", Toast.LENGTH_SHORT).show();

            } else {
                region_id = 0;
                region_name = "";
                isRegionDialog = true;
                final ChooseDialog dialog = new ChooseDialog(this, "اختر المدينة");
                dialog.setOnDismissListener(this);
                dialog.show();
                Service.Fetcher.getInstance().getRegions(city_id).enqueue(new Callback<List<Region>>() {
                    @Override
                    public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                        if (response.isSuccessful()) {
                            dialog.showRegionsList(response.body());

                        } else {
                            dialog.dismiss();
                            Toast.makeText(RegisterationActivity.this, "notSuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Region>> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (isCityDialog) {
            city_name = dataSaver.getString(AppKeys.CITY_NAME, "");
            city_id = dataSaver.getInt(AppKeys.CITY_ID, 0);
            if (city_id != -1) {
                city.setText(city_name);
            }

        }


        if (isRegionDialog) {

            region_name = dataSaver.getString(AppKeys.REGION_NAME, "");
            region_id = dataSaver.getInt(AppKeys.REGION_ID, 0);
            if (region_id != -1) {
                region.setText(region_name);
            }
        }
    }

    public void register(View view) {

        String mobil = phone.getText().toString();
        String pass = password.getText().toString();
        String name = username.getText().toString();
        String userAddress = address.getText().toString();
        String mail = email.getText().toString();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("برجاء الانتظار");
        dialog.show();
        Log.e("TAG", "userAddress " + userAddress);

        Service.Fetcher.getInstance().registerUser(name, pass, mail, mobil, region_id, userAddress,userActive,userDelete, lat, lon).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(RegisterationActivity.this, "تم تسجيل بياناتك بنجاح", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "isSuccessful");
                } else {
                    Log.e("TAG", "isNOTSuccessful");
                    dialog.dismiss();
                    Toast.makeText(RegisterationActivity.this, "خطا في تسجيل البيانات", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Log.e("TAG", "onFailure");
                Toast.makeText(RegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
