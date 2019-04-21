package com.agenda.smart.restaurant.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.agenda.smart.restaurant.R;

public class CashierActivity extends AppCompatActivity {
    WebView webView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);
        webView = findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        Log.e("TAG0","before");
        //http://resturant.support-smartagenda.com/cashier/home
        webView.loadUrl("http://support-smartagenda.com/admin-panel/login");
        Log.e("TAG0","after");

        webView.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
