package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class A7_QuizActivity extends BaseActivity {

    Toolbar quizToolbar;
    private static final String TAG = "QuizActivity";
    LinearLayout quizLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    Bundle quizBundle;
    String courseName;
    String topicName;
    HashMap<String, String> ansMap;
    HashMap<String, String> userAnswers;
    String option;
    String answer;
    int quesNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7__quiz);
        quizToolbar = (Toolbar) findViewById(R.id.quiz_toolbar);
        setSupportActionBar(quizToolbar);
        getSupportActionBar().setTitle("Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        quizToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        quizBundle = getIntent().getExtras();
        courseName = quizBundle.getString("CourseName");
        topicName = quizBundle.getString("TopicName");

        quizLinearLayout = (LinearLayout) findViewById(R.id.quiz_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);

        ansMap = new HashMap<>();
        userAnswers = new HashMap<>();

        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child("Quiz").child(topicName).child("Questions").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                //iterating through all questions
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    quesNo ++;
                    //get question
                    String question = child.getKey();

                    //add question view
                    final TextView questionTextView = new TextView(getApplicationContext());
                    questionTextView.setText(question);
                    questionTextView.setTextSize(30);
                    questionTextView.setLayoutParams(layoutParams);
                    quizLinearLayout.addView(questionTextView, layoutParams);

                    final RadioGroup quesRadioGroup = new RadioGroup(getApplicationContext());
                    quizLinearLayout.addView(quesRadioGroup);

                    //get options
                    for (DataSnapshot child2:child.getChildren()){
                        option = child2.getKey();
                        answer = child2.getValue().toString();
                        Log.i(TAG, option + ": " + answer);

                        if(answer == "true"){
                            ansMap.put(option, answer);
                        }

                        //add options view
                        RadioButton optionRadioButton = new RadioButton(getApplicationContext());
                        optionRadioButton.setText(option);
                        optionRadioButton.setTextSize(24);
                        quesRadioGroup.addView(optionRadioButton);
                    }
                    quesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                            String optionSelected = ((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                            if(ansMap.containsKey(optionSelected)){
                                Log.i(TAG, "Correct");
                            }
                            else{
                                Log.i(TAG, "Wrong");
                            }
                        }
                    });

                }
                Log.i(TAG, String.valueOf(ansMap));
                Log.i(TAG, "No of questions is " + quesNo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
