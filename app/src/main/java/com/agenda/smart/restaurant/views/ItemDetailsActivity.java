package com.agenda.smart.restaurant.views;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.BottomNavigationViewHelper;

public class ItemDetailsActivity extends BasicActivityCategories {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(navigationView);

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_item_details;
    }

    @Override
    int getNavigationMenuItemId() {
        return 0;
    }
}
