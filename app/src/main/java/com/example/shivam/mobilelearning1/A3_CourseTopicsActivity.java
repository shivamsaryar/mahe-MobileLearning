package com.example.shivam.mobilelearning1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class A3_CourseTopicsActivity extends AppCompatActivity {

    DatabaseReference mRootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8_course_topics);
        mRootRef = FirebaseDatabase.getInstance().getReference();

    }
}
