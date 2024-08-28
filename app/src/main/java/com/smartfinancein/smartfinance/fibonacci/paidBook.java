package com.smartfinancein.smartfinance.fibonacci;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class paidBook extends AppCompatActivity {

    WebView paidbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_book);

        paidbook = (WebView) findViewById(R.id.paidbook);
        webview(paidbook, allLinks.paidBookLink);

        getSupportActionBar().setTitle("SF Book");
    }

    public void webview(WebView webPage, String link){
        WebSettings webSettings3 = webPage.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webPage.loadUrl(link);
    }
}
