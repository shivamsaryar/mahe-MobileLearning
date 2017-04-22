package com.example.shivam.mobilelearning1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class A5_ViewCoursePDF extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=f8b2d11410&view=att&th=15982995740dac55&attid=0.1&disp=inline&realattid=8e4d94d0b4523937_0.1&safe=1&zw&saddbat=ANGjdJ-VVvqbgIJKNJc4ADJdgAbWhwZvTi1ND-Qi7u_MZJ6B3zLCBaffcW7aB-8RQgFI-T7eRHq7Q3oBPY8LlzK5K2RRUNI70byVFTLkrswrWYLu8HkeICh7sHh-jWDqCSDkU9tm51XV3GCgIoaNdJ0lF_D3BG-S3Ctg_-1B_70eB5WL0Ao6umM6LvqmgFgkYfo7_b5uixsFtnlPiILu2a43JXKJA6qXN4VGytrikRihdR5zT3Vgqqkm2ZHnYao1uD3XHzIPGEWYs1I9ebnOvetfwW1o7qs73tMozfx4fTRmT2YryV5EfN2ssncQgZRvS9kpOcrCDj2TPJw9Cyj8v2k9C2sO13c_U6WEqql3KzOxoutgjRJbp-2opoZnfCryCwqbJnKSjOisj3RCsB_wfywQzoNA7GdY3t2_pOxkhP6OAu6NQjzvv4Z9AMgBdgFci8ZiEtKu4ks5dGWj_CrruTrMN-8hLIyZS_TIFdT3Q8s6q0NI78uOHkElq1yQVVAywBT7L3Sck2jFBOhzgZ-vFA2acXvB-9G12Ui_QT3eB-H_N10u3_Q1XCN9pOQGbnpFlzE3JzPUmBSFz44UL8irAPzJ9zE4-4kewAMfxm8ysrOCQ5aFpsF57-J7VlGe9edhApcuRW9Wh7vAZPplCUd5ybISyEcm28JzyDP3iFrN-g");
        setContentView(R.layout.activity_9_view_course_pdf);
    }
}
