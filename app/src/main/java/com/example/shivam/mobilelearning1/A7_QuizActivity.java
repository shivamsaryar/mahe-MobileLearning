package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class A7_QuizActivity extends BaseActivity {

    Toolbar quizToolbar;
    private static final String TAG = "QuizActivity";
    LinearLayout quizLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    Bundle quizBundle;
    String courseName;
    String topicName;
    LinkedHashMap<String, String> correctAnsMap;
    LinkedHashMap<String, String> userAnswers;
    HashMap<String, String> allOptAndAns;
    String option;
    String answer;
    int quesNo = 0;
    Button submitButton;
    ArrayList<String> allQues;
    ArrayList<String> correctAnswers;
    ArrayList<String> usersAnswers;

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
        getSupportActionBar().setSubtitle(topicName);

        quizLinearLayout = (LinearLayout) findViewById(R.id.quiz_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);
        submitButton = (Button) findViewById(R.id.submit_quiz_answers_button);

        correctAnsMap = new LinkedHashMap<>();
        userAnswers = new LinkedHashMap<>();
        allOptAndAns = new HashMap<>();
        allQues = new ArrayList<>();
        correctAnswers = new ArrayList<>();
        usersAnswers = new ArrayList<>();

        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child("Quiz").child(topicName).child("Questions").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                //iterating through all questions
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    quesNo ++;
                    //get question
                    String question = child.getKey();
                    allQues.add(question);

                    //add question view
                    final TextView questionTextView = new TextView(getApplicationContext());
                    questionTextView.setText(question);
                    questionTextView.setTextSize(30);
                    questionTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    questionTextView.setLayoutParams(layoutParams);
                    quizLinearLayout.addView(questionTextView, layoutParams);

                    final RadioGroup quesRadioGroup = new RadioGroup(getApplicationContext());
                    quizLinearLayout.addView(quesRadioGroup);

                    //get options
                    for (DataSnapshot child2:child.getChildren()){
                        option = child2.getKey();
                        answer = child2.getValue().toString();
                        Log.i(TAG, option + ": " + answer);
                        allOptAndAns.put(option, answer);

                        if(answer == "true"){
                            correctAnsMap.put(option, answer);
                            correctAnswers.add(option);
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
                            usersAnswers.add(optionSelected);
                            if(correctAnsMap.containsKey(optionSelected)){
                                Log.i(TAG, "Correct");
                                String opt = ((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                                String optNew = ((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString().concat("        * Correct *");
                                ((RadioButton) findViewById(group.getCheckedRadioButtonId())).setText(optNew);
                                userAnswers.put(opt, "true");
                            }
                            else{
                                Log.i(TAG, "Wrong");
                                String opt = ((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString();
                                String optNew = ((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString().concat("        * Wrong *");
                                ((RadioButton) findViewById(group.getCheckedRadioButtonId())).setText(optNew);
                                userAnswers.put(opt, "false");
                            }
                            for (int i = 0; i < quesRadioGroup.getChildCount(); i++) {
                                quesRadioGroup.getChildAt(i).setEnabled(false);
                            }
                        }
                    });

                }
                Log.i(TAG, "All options and answers" + String.valueOf(correctAnsMap));

                Log.i(TAG, "No of questions is " + quesNo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userAnswers.size() != allQues.size()){
                    Log.i(TAG, "All questions are mandatory");
                    Toast.makeText(A7_QuizActivity.this, "Please answer all the questions", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.i(TAG, "Your answers: " + String.valueOf(userAnswers));
                    for(int i = 0;i<allQues.size(); i++){
                        Log.i(TAG, allQues.get(i));
                    }
                    Intent qIntent = new Intent(getApplicationContext(), A9_QuizResult.class);
                    qIntent.putStringArrayListExtra("CorrectAnswers", correctAnswers);
                    qIntent.putStringArrayListExtra("UsersAnswers", usersAnswers);
                    qIntent.putStringArrayListExtra("AllQues", allQues);
                    startActivity(qIntent);
                }
            }
        });
    }
}
