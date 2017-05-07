package com.example.shivam.mobilelearning1;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

public class A5_ViewCoursePDF extends AppCompatActivity {

    private static final String TAG = "ViewPdfActivity";
    WebView myPdfWebView;
    Bundle pdfPathBundle;
    String pdfUrl;
    LinearLayout pdfViewLinearLayout;
    LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_view_course_pdf);

        pdfPathBundle = getIntent().getExtras();
        pdfUrl = pdfPathBundle.getString("PdfDownloadPath");

        pdfViewLinearLayout = (LinearLayout) findViewById(R.id.pdfView_linear_layout);

        //set pdf into google pdf viewer
        myPdfWebView = (WebView) findViewById(R.id.pdf_web_view);
        myPdfWebView.getSettings().setJavaScriptEnabled(true);
        myPdfWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdfUrl);
        myPdfWebView.zoomOut();
    }

}
