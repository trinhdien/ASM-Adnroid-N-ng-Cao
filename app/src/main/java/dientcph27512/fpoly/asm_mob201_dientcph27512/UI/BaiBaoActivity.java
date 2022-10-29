package dientcph27512.fpoly.asm_mob201_dientcph27512.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dientcph27512.fpoly.asm_mob201_dientcph27512.R;

public class BaiBaoActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_bao);
        webView = (WebView) findViewById(R.id.webView);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        String tieude = intent.getStringExtra("tieude");
        setTitle(tieude);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        webView.loadUrl(link);
    }
}