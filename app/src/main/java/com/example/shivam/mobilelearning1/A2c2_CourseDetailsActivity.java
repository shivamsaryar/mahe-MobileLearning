package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class A2c2_CourseDetailsActivity extends AppCompatActivity {

    private static final String TAG = "A2c2_CourseDetailsActivity";
    DatabaseReference mRootRef;
    Bundle courseBundle;
    String courseID;
    String courseName;
    String courseStartDate;
    String courseEndDate;
    String courseInstructor;
    String courseLevel;
    String courseDuration;
    TextView textCourseNameTitle;
    TextView textStartDate;
    TextView textEndDate;
    TextView textDuration;
    TextView textInstructor;
    TextView textLevel;
    Button enrollButton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7_course_details);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        textCourseNameTitle = (TextView) findViewById(R.id.text_course_name_title);
        textStartDate = (TextView) findViewById(R.id.textView_start_date);
        textEndDate = (TextView) findViewById(R.id.textView_end_date);
        textLevel = (TextView) findViewById(R.id.textView_course_level);
        textInstructor = (TextView) findViewById(R.id.textView_course_instructor);
        textDuration = (TextView) findViewById(R.id.textView_course_duration);
        enrollButton = (Button) findViewById(R.id.course_enroll_button);

        courseBundle = getIntent().getExtras();
        if(courseBundle != null){
            courseName = courseBundle.getString("CourseName");
            courseID = courseBundle.getString("CourseID");
        }

        Log.i(TAG, courseName);

        mRootRef.child("courses").child(courseName).child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Map<String, String> map = dataSnapshot.getValue(Map.class);
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

        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Enroll clicked");
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put(courseName,"ongoing");
                mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").updateChildren(childUpdates);
                Intent mIntent = new Intent(getApplicationContext(), A2_DashboardActivity.class);
                Toast.makeText(A2c2_CourseDetailsActivity.this, "You have successfully enrolled for the course!", Toast.LENGTH_SHORT).show();
                startActivity(mIntent);
            }
        });

    }

    private void updateUI(String courseStartDate, String courseEndDate, String courseInstructor, String courseLevel, String courseDuration) {

        textCourseNameTitle.setText(courseName);
        textStartDate.setText(courseStartDate);
        textEndDate.setText(courseEndDate);
        textDuration.setText(courseDuration);
        textLevel.setText(courseLevel);
        textInstructor.setText(courseInstructor);

    }
}
