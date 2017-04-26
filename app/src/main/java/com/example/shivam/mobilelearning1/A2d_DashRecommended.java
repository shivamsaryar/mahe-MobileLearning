package com.example.shivam.mobilelearning1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class A2d_DashRecommended extends BaseActivity {

    private static final String TAG = "DashRecommended";
    Toolbar recToolbar;
    String courseTitle;
    String courseTopic;

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

        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    courseTitle = child.getKey();

                    //Display the Event name (parent/key) node
                    Log.i(TAG, "1st listener: " + child.getKey());


                    //Create buttons of the list of courses
                    addListenerForChildren(courseTitle);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addListenerForChildren(final String courseTitle2) {
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseTitle2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    courseTopic = child.getKey();

                    //Display the Event name (parent/key) node
                    Log.i(TAG, "second listener: " + child.getKey());


                    //Create buttons of the list of courses
                    getClickVideoCount(courseTitle2,courseTopic);
                    getPdfClickCount(courseTitle2, courseTopic);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    private void getClickVideoCount(final String courseTitle2, final String courseTopic) {
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseTitle2).child(courseTopic).child("video").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Map<String, String> map = dataSnapshot.getValue(Map.class);
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                String count = map.get("clicks");
                Log.i(TAG, "Count for video clicks for " + courseTitle2 + ": " + courseTopic + " = "  + count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getPdfClickCount(final String courseTitle2, final String courseTopic) {
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseTitle2).child(courseTopic).child("pdf").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Map<String, String> map = dataSnapshot.getValue(Map.class);
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                String count = map.get("clicks");
                Log.i(TAG, "Count for pdf clicks for " + courseTitle2 + ": " + courseTopic + " = "  + count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
