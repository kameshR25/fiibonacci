package com.smartfinancein.smartfinance.fibonacci;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ebook extends AppCompatActivity {

    WebView free_ebook;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        free_ebook = (WebView) findViewById(R.id.free_ebook);
        //webview
        webview(free_ebook, allLinks.ebookLink);
        //Textview
        text = findViewById(R.id.text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SF eBook");
    }

    public void webview(WebView webPage, String link){
        WebSettings webSettings3 = webPage.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webPage.loadUrl(link);
    }
}
