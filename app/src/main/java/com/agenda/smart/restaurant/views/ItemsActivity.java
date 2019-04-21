package com.agenda.smart.restaurant.views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.agenda.smart.restaurant.controllers.ItemsAdapter;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.Meals;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ItemsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerItems;
    ItemsAdapter itemsAdapter;
    GridLayoutManager LayoutManager;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    SharedPreferences dataSaver;
    Meals meals;
    String api_token;
    int category_id;
    TextView resturantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        //to display the name of all icon
        // navigationView.setOnNavigationItemSelectedListener(this);
        // BottomNavigationViewHelper.disableShiftMode(navigationView);
        dataSaver = getDefaultSharedPreferences(getApplicationContext());
        api_token = dataSaver.getString(AppKeys.TOKEN_KEY, "");
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
        // navigationView.getMenu().getItem(selected).setChecked(true);
        Intent intent = getIntent();
        category_id = intent.getIntExtra("categoryId", 0);
        recyclerItems = findViewById(R.id.meals_recycle);
        LayoutManager = new GridLayoutManager(this, 2);
        recyclerItems.setLayoutManager(LayoutManager);
        resturantName = findViewById(R.id.resturant_name);
        getMeals();
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

    public void getMeals() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري التحميل...");
        progressDialog.show();
        Log.e("TAG", "category_id " + category_id);

        Service.Fetcher.getInstance().getMeals(category_id).enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    meals = response.body();

                    itemsAdapter = new ItemsAdapter(ItemsActivity.this, meals);
                    recyclerItems.setAdapter(itemsAdapter);
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("TAG ", "onFailure");
                Toast.makeText(ItemsActivity.this, "حاول مره اخرى", Toast.LENGTH_SHORT).show();
            }

        });
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.log_out) {
            intent = new Intent(this, LoginActivity.class);
            dataSaver.edit()
                    .putInt(AppKeys.WAITER_ID, -1)
                    .apply();
            dataSaver.edit()
                    .putBoolean(AppKeys.ISADMIN, false)
                    .apply();
            dataSaver.edit()
                    .putString(AppKeys.TOKEN_KEY, "")
                    .apply();
            startActivity(intent);
            finish();
        } else if (id == R.id.categories) {

            intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
        } else if (id == R.id.tables) {
            intent = new Intent(this, TablesActivity.class);
            startActivity(intent);
            finish();
        }



        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void gotoCartActivity(View view) {
        Intent intent = new Intent(ItemsActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}
