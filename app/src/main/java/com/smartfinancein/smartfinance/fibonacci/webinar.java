package com.smartfinancein.smartfinance.fibonacci;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class webinar extends AppCompatActivity {

    WebView webinar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webinar);

        webinar = (WebView) findViewById(R.id.webinar);
        webview(webinar, allLinks.webinarLink);

        getSupportActionBar().setTitle("SF Webinar");
    }

    public void webview(WebView webPage, String link){
        WebSettings webSettings3 = webPage.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webPage.loadUrl(link);
    }
}
