package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.Map;

public class A2c1_DashDiscoverActivity extends BaseActivity{

    private static final String TAG = "DashDiscoverActivity";
    private DatabaseReference mRootRef;
    LinearLayout myLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    private ProgressBar myProgressBar;
    String courseName;
    Boolean courseAlreadyEnrolled;
    Toolbar discToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6_3_dash_discover);
        discToolbar = (Toolbar) findViewById(R.id.discover_toolbar);
        setSupportActionBar(discToolbar);
        getSupportActionBar().setTitle("Discover Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        discToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRootRef = FirebaseDatabase.getInstance().getReference();
        myProgressBar = (ProgressBar) findViewById(R.id.progressBar2);
        myLinearLayout = (LinearLayout) findViewById(R.id.my_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);

        //Add listener to read all the node values
        mRootRef.child("courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onDataChanged called");
                myProgressBar.setVisibility(View.VISIBLE);
                //Remove all previously created button views (Refresh the layout)
                myLinearLayout.removeAllViews();
                try{
                    for(DataSnapshot child:dataSnapshot.getChildren()){
                        courseName = child.getKey();
                        //Display the key value pairs of the Event name node and all it's sub-nodes
                        Log.i(TAG, child.toString());
                        //Display the Event name (parent/key) node
                        Log.i(TAG, child.getKey());

                        //Create buttons of the list of courses
                        createCourseButtons(courseName);
                    }
                    myProgressBar.setVisibility(View.INVISIBLE);
                }
                catch(Exception e){
                    Log.i(TAG, "Unable to fetch nodes");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    private void createCourseButtons(String str) {

        final Button myButton = new Button(getApplicationContext());
        myButton.setText(str);
        myButton.setBackgroundColor(Color.BLUE);
        myButton.setTextColor(Color.WHITE);
        myButton.setLayoutParams(layoutParams);
        myLinearLayout.addView(myButton, layoutParams);
        Log.i(TAG, "button '" + str + "' added");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, myButton.getText().toString());
                Intent courseDetailsIntent = new Intent(getApplicationContext(), A2c2_CourseDetailsActivity.class);
                courseDetailsIntent.putExtra("CourseName", myButton.getText().toString());
                startActivity(courseDetailsIntent);
            }
        });
    }
}
