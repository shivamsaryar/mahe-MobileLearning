package com.example.shivam.mobilelearning1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class CourseDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CourseDetailsActivity";
    Bundle courseNameBundle;
    String courseName;
    DatabaseReference mRootRef;
    String courseStartDate;
    String courseEndDate;
    String courseInstructor;
    String courseLevel;
    String courseDuration;
    TextView textStartDate;
    TextView textEndDate;
    TextView textDuration;
    TextView textInstructor;
    TextView textLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        mRootRef = FirebaseDatabase.getInstance().getReference();

        textStartDate = (TextView) findViewById(R.id.textView_start_date);
        textEndDate = (TextView) findViewById(R.id.textView_end_date);
        textLevel = (TextView) findViewById(R.id.textView_course_level);
        textInstructor = (TextView) findViewById(R.id.textView_course_instructor);
        textDuration = (TextView) findViewById(R.id.textView_course_duration);

        courseNameBundle = getIntent().getExtras();
        if(courseNameBundle != null){
            courseName = courseNameBundle.getString("CourseName");
        }

        Log.i(TAG, courseName);

        mRootRef.child("courses").child(courseName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Map<String, String> map = dataSnapshot.getValue(Map.clas  s);
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                courseStartDate = map.get("StartDate");
                courseEndDate = map.get("EndDate");
                courseInstructor = map.get("Instructor");
                courseLevel = map.get("Level");
                courseDuration = map.get("Duration");

                updateUI(courseStartDate, courseEndDate, courseInstructor, courseLevel, courseDuration);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateUI(String courseStartDate, String courseEndDate, String courseInstructor, String courseLevel, String courseDuration) {
        textStartDate.setText(courseStartDate);
        textEndDate.setText(courseEndDate);
        textDuration.setText(courseDuration);
        textLevel.setText(courseLevel);
        textInstructor.setText(courseInstructor);

    }
}
