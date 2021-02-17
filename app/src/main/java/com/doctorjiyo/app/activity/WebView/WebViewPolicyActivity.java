package com.doctorjiyo.app.activity.WebView;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.doctorjiyo.app.R;

public class WebViewPolicyActivity extends AppCompatActivity  {

    WebView webView;
    ImageButton close;

    Intent intent;
    String RecordID;
    GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_policy);

        //intent=getIntent();
        //RecordID=intent.getStringExtra("RecordID");

        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView = (WebView) (findViewById(R.id.webview));
        webView.loadUrl("http://manage.doctorjiyo.com/PrivacyPolicy");
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

    }


}
