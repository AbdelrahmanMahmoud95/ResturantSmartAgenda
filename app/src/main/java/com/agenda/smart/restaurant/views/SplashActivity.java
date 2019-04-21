package com.agenda.smart.restaurant.views;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agenda.smart.restaurant.R;
import com.agenda.smart.restaurant.controllers.ViewPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView arrowImage;
    TextView skipText;
    ViewPager mPager;
    ViewPagerAdapter pagerAdapter;
    private static int currentPage = 0;
    private int[] images = {R.drawable.kofta, R.drawable.stek, R.drawable.botato};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        arrowImage = (ImageView) findViewById(R.id.arrow_image);
        skipText = (TextView) findViewById(R.id.skip_text);
        mPager = (ViewPager) findViewById(R.id.pager);
        arrowImage.setOnClickListener(this);
        skipText.setOnClickListener(this);
        pagerAdapter = new ViewPagerAdapter(SplashActivity.this);
        mPager.setAdapter(pagerAdapter);
        init();
    }


    private void init() {
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 6000, 3000);
    }

   // fa202b
    @Override
    public void onClick(View view) {
        if (view == arrowImage) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
        if (view == skipText) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
    }


}
