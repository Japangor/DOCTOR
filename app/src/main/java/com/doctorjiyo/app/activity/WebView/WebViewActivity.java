package com.doctorjiyo.app.activity.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.webkit.WebView;

import com.doctorjiyo.app.R;

public class WebViewActivity extends AppCompatActivity  {

    WebView webView;

    Intent intent;
    String RecordID;
    GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        intent=getIntent();
        RecordID=intent.getStringExtra("RecordID");

        webView = (WebView) (findViewById(R.id.webview));
        webView.loadUrl("http://manage.doctorjiyo.com/ViewRecord?RecordID="+RecordID);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

    }


}
