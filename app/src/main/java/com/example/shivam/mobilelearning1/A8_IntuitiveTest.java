package com.example.shivam.mobilelearning1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class A8_IntuitiveTest extends AppCompatActivity {

    private static final String TAG = "Intuitive Test";
    Toolbar intuitiveTestToolbar;
    String courseName;
    String topicName;
    Bundle intTestBundle;
    String correctAnswer = "double";
    EditText learnerAnswer;
    Button submitIntuitiveAnsBtn;
    TextView systemMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a8__intuitive_test);
        intuitiveTestToolbar = (Toolbar) findViewById(R.id.intuitive_test_toolbar);
        setSupportActionBar(intuitiveTestToolbar);
        getSupportActionBar().setTitle("Intuitive Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        learnerAnswer = (EditText) findViewById(R.id.intuitive_answer_edit_text);
        submitIntuitiveAnsBtn = (Button) findViewById(R.id.submit_intuitive_answer_button);
        systemMessageText = (TextView) findViewById(R.id.system_message_text);
        systemMessageText.setVisibility(View.INVISIBLE);

        intTestBundle = getIntent().getExtras();
        courseName = intTestBundle.getString("CourseName");
        topicName = intTestBundle.getString("TopicName");
        getSupportActionBar().setTitle("Intuitive Quiz");
        getSupportActionBar().setSubtitle(topicName);

        intuitiveTestToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submitIntuitiveAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                systemMessageText.setVisibility(View.VISIBLE);
                if(learnerAnswer.getText().toString().trim().length() == 0){
                    Toast.makeText(A8_IntuitiveTest.this, "Please submit an answer", Toast.LENGTH_SHORT).show();
                }
                else{
                    submitIntuitiveAnsBtn.setText("DONE");
                    submitIntuitiveAnsBtn.setEnabled(false);
                    submitIntuitiveAnsBtn.setBackgroundColor(Color.MAGENTA);
                    String mAnswer = learnerAnswer.getText().toString().toLowerCase();
                    if(mAnswer.equals(correctAnswer)){
                        systemMessageText.setText("Great job! \n'float' data type is a single-precision 32-bit IEEE 754 floating point. " +
                                " \n'double' data type is a double-precision 64-bit IEEE 754 floating point");
                        submitIntuitiveAnsBtn.setText("DONE");
                    }
                    else{
                        systemMessageText.setText("Sorry. The answer is 'double'. \ndouble data type is a double-precision 64-bit IEEE 754 floating point." +
                                "\nThis data type is generally used as the default data type for decimal values, generally the default choice. " +
                                "\nExample: double d1 = 123.4");
                    }
                }
            }
        });

    }
}
