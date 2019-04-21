package com.agenda.smart.restaurant.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.CategoriesAdapter;
import com.agenda.smart.restaurant.controllers.Service;
import com.agenda.smart.restaurant.model.Categories;
import com.agenda.smart.restaurant.model.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerCategories;
    CategoriesAdapter categoriesAdapter;
    GridLayoutManager LayoutManager;
    Categories categories;
    TextView resturantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        recyclerCategories = findViewById(R.id.categories_recycle);
        LayoutManager = new GridLayoutManager(this, 3);
        recyclerCategories.setLayoutManager(LayoutManager);
        resturantName = findViewById(R.id.resturant_name);

        getCategories();
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

    public void getCategories() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري التحميل...");
        progressDialog.show();
        Log.e("TAG", "isSuccessful");

        Service.Fetcher.getInstance().getCategories().enqueue(new Callback<Categories>() {

            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    categories = response.body();
                    categoriesAdapter = new CategoriesAdapter(CategoriesActivity.this, categories);
                    recyclerCategories.setAdapter(categoriesAdapter);

                }
            }


            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("TAG ", "onFailure");
                Toast.makeText(CategoriesActivity.this, "حاول مره اخرى", Toast.LENGTH_SHORT).show();
            }

        });
    }


    public void gotoCartActivity(View view) {
        Intent intent = new Intent(CategoriesActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}
