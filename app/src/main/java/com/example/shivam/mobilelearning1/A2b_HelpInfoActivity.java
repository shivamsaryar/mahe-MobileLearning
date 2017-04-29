package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class A2b_HelpInfoActivity extends AppCompatActivity {

    WebView helpWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2b_help_info);

        helpWebView = (WebView) findViewById(R.id.help_web_view);
        helpWebView.getSettings().setJavaScriptEnabled(true);
        String pdfPath = "https://firebasestorage.googleapis.com/v0/b/mobilelearning1-576b3.appspot.com/o/Learning%20Styles%20and%20Strategies.pdf?alt=media&token=f9e99f01-d641-4b16-a415-9719a8e8fa22";
        helpWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdfPath);
        helpWebView.zoomOut();

    }
}
