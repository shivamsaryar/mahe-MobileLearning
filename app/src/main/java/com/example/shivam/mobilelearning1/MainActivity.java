package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.annotation.NonNull;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_main);

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
}
