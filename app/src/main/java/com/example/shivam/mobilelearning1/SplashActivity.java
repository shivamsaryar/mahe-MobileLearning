package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Boolean signedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_splash);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged: signed_in with id: " + user.getUid());
                    signedIn = true;
                    //curr_user_id = user.getUid().toString();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                    signedIn = false;
                }
                // [START_EXCLUDE]
                proceed(signedIn);
                // [END_EXCLUDE]
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
            Intent DashboardIntent = new Intent(SplashActivity.this, MainActivity.class);
            Toast.makeText(this, "Splash signed in", Toast.LENGTH_SHORT).show();
            startActivity(DashboardIntent);
        }
        else{
            Intent loginIntent = new Intent(SplashActivity.this, GoogleSignInActivity.class);
            Toast.makeText(this, "splash signed out", Toast.LENGTH_SHORT).show();
            startActivity(loginIntent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
