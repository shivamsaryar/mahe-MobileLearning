package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class A1_SplashActivity extends AppCompatActivity {

    private static final String MyTAG = "ShivamLog";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressBar splashProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_splash);
        splashProgressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);
        splashProgressBar.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //user is signed in
                    splashProgressBar.setVisibility(View.INVISIBLE);
                    Intent dashboardIntent = new Intent(A1_SplashActivity.this, A2_DashboardActivity.class);
                    //Intent dashboardIntent = new Intent(A1_SplashActivity.this, A7_QuizActivity.class);
                    Toast.makeText(getApplicationContext(), "Splash - User signed in", Toast.LENGTH_SHORT).show();
                    startActivity(dashboardIntent);
                } else {
                    //user is not signed in
                    splashProgressBar.setVisibility(View.INVISIBLE);
                    Intent loginIntent = new Intent(A1_SplashActivity.this, A1a_GoogleSignInActivity.class);
                    Toast.makeText(getApplicationContext(), "Splash - User signed out", Toast.LENGTH_SHORT).show();
                    startActivity(loginIntent);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(MyTAG, "onStart");
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MyTAG, "onResume()");
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(MyTAG, "onStop");
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
