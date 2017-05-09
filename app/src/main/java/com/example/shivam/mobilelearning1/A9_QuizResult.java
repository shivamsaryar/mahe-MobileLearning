package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class A9_QuizResult extends AppCompatActivity {

    private static final String TAG = "QuizResults";
    Bundle resultBundle;
    ArrayList<String> allQues;
    ArrayList<String> correctAnswers;
    ArrayList<String> usersAnswers;
    Toolbar quizResultToolbar;
    LinearLayout quizResultLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    Button okButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a9__quiz_result);
        quizResultToolbar = (Toolbar) findViewById(R.id.quiz_result_toolbar);
        setSupportActionBar(quizResultToolbar);
        getSupportActionBar().setTitle("Quiz Results");
        getSupportActionBar().setSubtitle("1-Variables");

        quizResultLinearLayout = (LinearLayout) findViewById(R.id.quiz_result_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);
        okButton = (Button) findViewById(R.id.result_done_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(getApplicationContext(), A2_DashboardActivity.class);
                startActivity(goHome);
            }
        });

        resultBundle = getIntent().getExtras();
        if(resultBundle!=null){
            allQues = resultBundle.getStringArrayList("AllQues");
            correctAnswers = resultBundle.getStringArrayList("CorrectAnswers");
            usersAnswers = resultBundle.getStringArrayList("UsersAnswers");
        }
        for(int i = 0; i < allQues.size(); i++){
            Log.i(TAG, allQues.get(i));
            Log.i(TAG, usersAnswers.get(i));
            Log.i(TAG, correctAnswers.get(i));
        }
        for(int i = 0; i<allQues.size(); i++){

            final TextView quesTextView = new TextView(getApplicationContext());
            quesTextView.setTextSize(30);
            quesTextView.setText(allQues.get(i));
            quesTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            quesTextView.setTextColor(Color.BLACK);
            quesTextView.setLayoutParams(layoutParams);
            quizResultLinearLayout.addView(quesTextView, layoutParams);

            final TextView userAnsTextView = new TextView(getApplicationContext());
            userAnsTextView.setTextSize(22);
            userAnsTextView.setText("Your answer: " + usersAnswers.get(i));
            userAnsTextView.setTextColor(Color.BLUE);
            userAnsTextView.setLayoutParams(layoutParams);
            quizResultLinearLayout.addView(userAnsTextView, layoutParams);

            final TextView correctAnsTextView = new TextView(getApplicationContext());
            correctAnsTextView.setTextSize(22);
            correctAnsTextView.setText("Correct Answer: " + correctAnswers.get(i));
            correctAnsTextView.setTextColor(Color.RED);
            correctAnsTextView.setLayoutParams(layoutParams);
            quizResultLinearLayout.addView(correctAnsTextView, layoutParams);
        }
    }
}
