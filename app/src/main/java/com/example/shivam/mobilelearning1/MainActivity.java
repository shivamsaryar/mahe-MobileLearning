package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends BaseActivity{

    /*
    APPLICATION ACTIVITY SEQUENCE:

    BaseActivity
    MainActivity
    QuestionnaireScrollingActivity
    DisplayLearningStyleActivity
    Dashboard
    */
    Button proceedToGoogleSignIn;
    Button proceedToQuestionnaire;

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public String curr_user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    curr_user_id = user.getUid().toString();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                updateUI(user);
                // [END_EXCLUDE]
            }
        };

        proceedToQuestionnaire = (Button) findViewById(R.id.proceed_to_questionnaire_button);
        final Intent proceedToQuestionnaireIntent = new Intent(this, QuestionnaireScrollingActivity.class);
        proceedToQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(proceedToQuestionnaireIntent);
            }
        });

        proceedToGoogleSignIn = (Button) findViewById(R.id.proceed_to_google_signin);
        proceedToGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mGoogleSignInIntent = new Intent(getApplicationContext(), GoogleSignInActivity.class);
                startActivity(mGoogleSignInIntent);
            }
        });
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

    public void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            Toast.makeText(this, "Hello, " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
        }
    }
}
