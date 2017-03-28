package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class DashDiscoverActivity extends AppCompatActivity {

    private static final String TAG = "DashDiscoverActivity";
    private StorageReference mStorageRef;
    private DatabaseReference mRootRef;
    private ImageView mImage;
    private ArrayList <String> courseList;
    LinearLayout myLinearLayout;
    Button myButton;
    LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_discover);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        courseList = new ArrayList<String>();
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
                removeAllButtons();

                for(DataSnapshot child:dataSnapshot.getChildren()){
                    courseList.add(child.getKey());
                    Log.i(TAG, child.getKey());
                    String str = child.getKey();

                    //Create buttons of the list of courses
                    addCourseButtons(str);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void removeAllButtons() {
        myLinearLayout.removeAllViews();
    }

    private void addCourseButtons(String str) {
        //get the list of children at the 'courses' node
        //use the list to add buttons

        myButton = new Button(getApplicationContext());
        myButton.setText(str);
        myButton.setBackgroundColor(0xff0000ff);
        myButton.setTextColor(0xffffffff);
        myButton.setLayoutParams(layoutParams);
        myLinearLayout.addView(myButton, layoutParams);
        Log.i(TAG, "button '" + str + "' added");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent courseDetailsIntent = new Intent(getApplicationContext(), CourseDetailsActivity.class);
                startActivity(courseDetailsIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setInitialNodes() {

        courseList.add("Arts and Humanities");
        courseList.add("Business");
        courseList.add("Computer Science");
        courseList.add("Mathematics");

        mRootRef.child("courses").child("Business").child("Finance").setValue("abc1");
        mRootRef.child("courses").child("Computer Science").child("Java").setValue("java course for beginners");
        mRootRef.child("courses").child("Computer Science").child("Python").setValue("Python course for beginners");
        mRootRef.child("courses").child("Computer Science").child("GitHub").setValue("GitHub course for beginners");
    }
}
