package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /*
    APPLICATION ACTIVITY SEQUENCE:

    BaseActivity
    MainActivity
    QuestionnaireScrollingActivity
    DisplayLearningStyleActivity
    Dashboard
    */

    Button proceedButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proceedButton = (Button) findViewById(R.id.proceed_to_login_button);
        final Intent proceedToLoginIntent = new Intent(this, QuestionnaireScrollingActivity.class);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(proceedToLoginIntent);
            }
        });
    }
}
