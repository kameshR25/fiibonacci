package com.smartfinancein.smartfinance.fibonacci;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class paidSoftware extends AppCompatActivity {

    WebView software;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_software);

        //displaying how to use videos
        software = (WebView) findViewById(R.id.software);
        webview(software, allLinks.paidSoftwareLink);

        getSupportActionBar().setTitle("SF Softwares");
    }

    public void webview(WebView webPage, String link){
        WebSettings webSettings3 = webPage.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webPage.loadUrl(link);
    }
}
