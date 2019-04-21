package com.agenda.smart.restaurant.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.AppKeys;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.controllers.TablesAdapter;
import com.agenda.smart.restaurant.model.Settings;
import com.agenda.smart.restaurant.model.Tables;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class TablesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerTable;
    TablesAdapter tablesAdapter;
    GridLayoutManager LayoutManager;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    SharedPreferences dataSaver;
    Tables tables;
    int table_id = 0;
    TextView resturantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        resturantName = findViewById(R.id.resturant_name);
        recyclerTable = findViewById(R.id.tables_recycle);
        LayoutManager = new GridLayoutManager(this, 3);
        recyclerTable.setLayoutManager(LayoutManager);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        dataSaver.edit()
                .putInt(AppKeys.TABLE_ID, 0)
                .apply();
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
        getTables();
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

    public void getTables() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري التحميل...");
        progressDialog.show();
        Log.e("TAG", "isSuccessful");

        Service.Fetcher.getInstance().getTables().enqueue(new Callback<Tables>() {

            @Override
            public void onResponse(Call<Tables> call, Response<Tables> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    tables = response.body();
                    tablesAdapter = new TablesAdapter(TablesActivity.this, tables);
                    recyclerTable.setAdapter(tablesAdapter);

                }
            }


            @Override
            public void onFailure(Call<Tables> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("TAG ", "onFailure");
                Toast.makeText(TablesActivity.this, "حاول مره اخرى", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.log_out) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            dataSaver.edit()
                    .putInt(AppKeys.WAITER_ID, -1)
                    .apply();
            dataSaver.edit()
                    .putBoolean(AppKeys.ISADMIN, false)
                    .apply();
            dataSaver.edit()
                    .putString(AppKeys.TOKEN_KEY, "")
                    .apply();
            finish();
        } else if (id == R.id.categories) {
            if (table_id == 0) {
                Toast.makeText(TablesActivity.this, "يجب اختيار الطاوله اولا", Toast.LENGTH_LONG).show();
            } else {
                intent = new Intent(this, CategoriesActivity.class);
                startActivity(intent);
                //finish();
            }
        } else if (id == R.id.tables) {
            intent = new Intent(this, TablesActivity.class);
            startActivity(intent);
            finish();
        }



        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void gotoCartActivity(View view) {
        Intent intent = new Intent(TablesActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}
