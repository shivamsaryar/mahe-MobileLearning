package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class A3_CourseTopicsActivity extends BaseActivity {

    private static final String TAG = "CourseTopics";
    Bundle courseBundle;
    String courseName;
    String topicName;
    String topicType;
    LinearLayout courseTopicsLinearLayout;
    ProgressBar courseTopicsProgressBar;
    LinearLayout.LayoutParams layoutParams;
    TextView courseHeader;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8_course_topics);

        toolbar = (Toolbar) findViewById(R.id.course_topics_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Course Topics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        courseHeader = (TextView) findViewById(R.id.course_header);
        courseTopicsProgressBar = (ProgressBar) findViewById(R.id.course_topics_progress_bar);
        courseTopicsLinearLayout = (LinearLayout) findViewById(R.id.course_topics_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);

        //Get the name of the course that the user wishes to view
        courseBundle = getIntent().getExtras();
        if(courseBundle != null){
            courseName = courseBundle.getString("CourseName");
        }

        courseHeader.setText(courseName);

        //get the list of video topics under that particular course topic
        mRootRef.child("courses").child(courseName).child("CourseTopics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                courseTopicsProgressBar.setVisibility(View.VISIBLE);
                //Remove all previously created button views (Refresh the layout)
                courseTopicsLinearLayout.removeAllViews();
                try{
                    for(DataSnapshot child:dataSnapshot.getChildren()){
                        topicName = child.getKey();
                        topicType = child.getValue().toString();
                        //Display the key value pairs of the Course topic node and all it's sub-nodes
                        Log.i(TAG, child.toString());
                        //Display the topic name (parent/key) node
                        Log.i(TAG, "Topic: " + topicName);
                        Log.i(TAG, "Type: " + topicType);

                        //Create buttons of the list of course topics
                        createTopicButtons(topicName);
                    }
                    courseTopicsProgressBar.setVisibility(View.INVISIBLE);
                }
                catch(Exception e){
                    Log.i(TAG, "Unable to fetch nodes");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //get user learning style
        mRootRef.child("users").child(mUser.getUid()).child("Learning Styles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                String learning_style_3 = map.get("learning_style_3");
                global_learningStyle_1 = map.get("learning_style_1");
                global_learningStyle_2 = map.get("learning_style_2");
                global_learningStyle_3 = map.get("learning_style_3");
                global_learningStyle_4 = map.get("learning_style_4");

                Log.i(TAG, learning_style_3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void createTopicButtons(String str) {
        final Button myButton = new Button(getApplicationContext());
        myButton.setText(str);
        myButton.setBackgroundColor(Color.DKGRAY);
        myButton.setTextColor(Color.WHITE);
        myButton.setLayoutParams(layoutParams);
        courseTopicsLinearLayout.addView(myButton, layoutParams);
        Log.i(TAG, "button '" + str + "' added");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, myButton.getText().toString());
                Intent courseDetailsIntent = new Intent(getApplicationContext(), A4_TopicFiles.class);
                courseDetailsIntent.putExtra("TopicName", myButton.getText().toString());
                courseDetailsIntent.putExtra("CourseName", courseName);
                startActivity(courseDetailsIntent);
            }
        });
    }

}
