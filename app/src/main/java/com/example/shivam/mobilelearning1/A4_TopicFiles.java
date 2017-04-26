package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class A4_TopicFiles extends BaseActivity {

    private static final String TAG = "TopicFiles";
    TextView topicHeader;
    Button btnViewPdf;
    Button btnViewVideo;
    Bundle topicBundle;

    String topicName;
    String courseName;

    Toolbar topicsToolbar;

    String pdfViewCount;
    String videoViewCount;

    String pdfPath;
    String videoPath;

    Integer pdfCounter;
    Integer videoCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_topic_files);

        topicsToolbar = (Toolbar) findViewById(R.id.topics_toolbar);
        setSupportActionBar(topicsToolbar);
        getSupportActionBar().setTitle("Topic Materials");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        topicsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        topicBundle = getIntent().getExtras();
        if(topicBundle != null){
            topicName = topicBundle.getString("TopicName");
            courseName = topicBundle.getString("CourseName");
        }

        topicHeader = (TextView) findViewById(R.id.textView_course_topic);
        btnViewPdf = (Button) findViewById(R.id.button_view_pdf);
        btnViewVideo = (Button) findViewById(R.id.button_view_video);

        topicHeader.setText(topicName);

        //Read current pdfCounter of pdf views for the current topic
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child(topicName).child("pdf").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> pdfMap = dataSnapshot.getValue(genericTypeIndicator );
                pdfViewCount = pdfMap.get("clicks");
                pdfPath = pdfMap.get("path");
                pdfCounter = Integer.parseInt(pdfViewCount);
                pdfCounter++;
                Log.i(TAG, pdfCounter.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Read current pdfCounter of video views for the current topic
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child(topicName).child("video").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> videoMap = dataSnapshot.getValue(genericTypeIndicator );
                videoViewCount = videoMap.get("clicks");
                videoPath = videoMap.get("path");
                videoCounter = Integer.parseInt(videoViewCount);
                videoCounter++;
                Log.i(TAG, videoCounter.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Add listeners for buttons
        btnViewPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> pdfChildUpdates = new HashMap<>();
                pdfChildUpdates.put("clicks", pdfCounter.toString());
                pdfChildUpdates.put("path", pdfPath);
                mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child(topicName).child("pdf").updateChildren(pdfChildUpdates);
                startActivity(new Intent(getApplicationContext(), A5_ViewCoursePDF.class));
            }
        });
        btnViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> videoChildUpdates = new HashMap<>();
                videoChildUpdates.put("clicks", videoCounter.toString());
                videoChildUpdates.put("path", videoPath);
                mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child(topicName).child("video").updateChildren(videoChildUpdates);
                startActivity(new Intent(getApplicationContext(), A6_ViewCourseVideo.class));
            }
        });
    }
}
