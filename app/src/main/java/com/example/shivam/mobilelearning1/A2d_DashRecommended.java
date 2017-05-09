package com.example.shivam.mobilelearning1;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class A2d_DashRecommended extends BaseActivity {

    private static final String TAG = "DashRecommended";
    Toolbar recToolbar;
    String courseTitle;
    String courseTopic;
    ArrayList<String> countList;
    String vid_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2d_dash_recommended);
        recToolbar = (Toolbar) findViewById(R.id.recommendations_toolbar);
        setSupportActionBar(recToolbar);
        getSupportActionBar().setTitle("Recommendations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        countList = new ArrayList<String>();

        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    courseTitle = child.getKey();
                    //Display the Event name (parent/key) node
                    Log.i(TAG, "1st listener for courses enrolled: " + child.getKey());

                    //Create buttons of the list of courses
                    addListenerForChildren(courseTitle);

                    //get learning style of user
                    getUserLearningStyle();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getUserLearningStyle() {
        mRootRef.child("users").child(mUser.getUid()).child("Learning Styles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                String learning_style_3 = map.get("learning_style_3");
                Log.i(TAG, "User learning style 3: " + learning_style_3.substring(2));
                if(learning_style_3 == "3_Visual Learner")
                    vid_pdf = "visual";
                else if(learning_style_3 == "3_Verbal Learner")
                    vid_pdf = "verbal";
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addListenerForChildren(final String courseTitle2) {
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseTitle2).child("CourseTopics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //iterating through list of course topics
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    courseTopic = child.getKey();
                    Log.i(TAG, "2nd listener for course topics: " + child.getKey());

                    //iterating through all children of each course topic
                    for (DataSnapshot child2:child.getChildren()){
                        String a = child2.getKey();
                        String a_val = child2.getValue().toString();
                        Log.i(TAG, a + ": " + a_val);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
