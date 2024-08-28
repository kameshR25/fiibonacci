package com.smartfinancein.smartfinance.fibonacci;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class video extends AppCompatActivity {

    WebView use_videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //displaying how to use videos
        use_videos = (WebView) findViewById(R.id.use_videos);
        webview(use_videos, allLinks.howToUseVideos);

        getSupportActionBar().setTitle("Fibonacci Videos");
    }

    public void webview(WebView webPage, String link){
        WebSettings webSettings3 = webPage.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webPage.loadUrl(link);
    }

    }

