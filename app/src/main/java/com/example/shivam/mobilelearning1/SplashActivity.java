package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String MyTAG = "ShivamLog";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Boolean signedIn;
    ProgressBar splashProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_splash);
        Log.i(MyTAG, "onCreate()");
        splashProgressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);
        splashProgressBar.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged: signed_in with id: " + user.getUid());
                    signedIn = true;
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                    signedIn = false;
                }
                proceed(signedIn);
            }
        };
        /*
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if(mUser != null){
            signedIn = true;
        }
        else{
            signedIn = false;
        }
        proceed(signedIn);
        */
    }

    private void proceed(Boolean signedIn) {
        if(signedIn == true){
            splashProgressBar.setVisibility(View.INVISIBLE);
            Intent dashboardIntent = new Intent(SplashActivity.this, DashboardActivity.class);
            Toast.makeText(this, "Splash - User signed in", Toast.LENGTH_SHORT).show();
            startActivity(dashboardIntent);
        }
        else{
            splashProgressBar.setVisibility(View.INVISIBLE);
            Intent loginIntent = new Intent(SplashActivity.this, GoogleSignInActivity.class);
            Toast.makeText(this, "Splash - User signed out", Toast.LENGTH_SHORT).show();
            startActivity(loginIntent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(MyTAG, "onStart");
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MyTAG, "onPause");
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

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(MyTAG, "onRestart");
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MyTAG, "onDestroy");
    }
}
