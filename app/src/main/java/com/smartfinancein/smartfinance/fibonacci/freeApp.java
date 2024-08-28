package com.smartfinancein.smartfinance.fibonacci;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class freeApp extends AppCompatActivity {

    WebView trailApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_app);

        trailApp = (WebView) findViewById(R.id.trailApp);
        webview(trailApp, allLinks.trialAppLink);

        getSupportActionBar().setTitle("SF Trial App");
    }

    public void webview(WebView webPage, String link){
        WebSettings webSettings3 = webPage.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webPage.loadUrl(link);
    }
}
