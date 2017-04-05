package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.Map;

public class DashDiscoverActivity extends BaseActivity{

    private static final String TAG = "DashDiscoverActivity";
    private StorageReference mStorageRef;
    private DatabaseReference mRootRef;
    private ImageView mImage;
    private ArrayList <String> courseList;
    LinearLayout myLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    private ProgressBar myProgressBar;
    String courseID;
    String courseName;
    private ArrayList<Integer> courseIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6_3_dash_discover);

        myProgressBar = (ProgressBar) findViewById(R.id.progressBar2);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        courseList = new ArrayList<String>();
        courseIdList = new ArrayList<Integer>();
        mImage = (ImageView) findViewById(R.id.img_test);
        myLinearLayout = (LinearLayout) findViewById(R.id.my_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);

        //set initial nodes in the database
        //setInitialNodes();

        //Get photo from online storage
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference sr = mStorageRef.child("Wallpapers/abstract_wallpaper_1.jpg");
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(sr)
                .into(mImage);

        //Add listener to read all the node values
        mRootRef.child("courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myProgressBar.setVisibility(View.VISIBLE);

                removeAllButtons();

                for(DataSnapshot child:dataSnapshot.getChildren()){
                    courseList.add(child.getKey());
                    Log.i(TAG, child.getKey());
                    courseName = child.getKey();

                    //Create buttons of the list of courses
                    createCourseButtons(courseName);
                }
                myProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void removeAllButtons() {
        myLinearLayout.removeAllViews();
    }

    private void createCourseButtons(String str) {

        final Button myButton = new Button(getApplicationContext());
        myButton.setText(str);
        myButton.setBackgroundColor(0xff0000ff);
        myButton.setTextColor(0xffffffff);
        myButton.setLayoutParams(layoutParams);
        myLinearLayout.addView(myButton, layoutParams);
        Log.i(TAG, "button '" + str + "' added");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get course ID
                mRootRef.child("courses").child(courseName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot2) {
                        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                        Map<String, String> map = dataSnapshot2.getValue(genericTypeIndicator );
                        courseID = map.get("CourseID").toString();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Log.i(TAG, myButton.getText().toString());
                Intent courseDetailsIntent = new Intent(getApplicationContext(), CourseDetailsActivity.class);
                courseDetailsIntent.putExtra("CourseName", myButton.getText().toString());
                courseDetailsIntent.putExtra("CourseID", courseID);
                startActivity(courseDetailsIntent);
            }
        });
    }
}
