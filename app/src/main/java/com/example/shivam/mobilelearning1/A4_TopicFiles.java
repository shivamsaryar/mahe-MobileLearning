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
    Toolbar topicsToolbar;
    TextView topicHeader;
    Button btnViewPdf;
    Button btnViewVideo;
    Button btnViewQuiz;
    Bundle topicBundle;
    String topicName;
    String courseName;
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

        //set toolbar
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

        //get bundle extras
        topicBundle = getIntent().getExtras();
        if(topicBundle != null){
            topicName = topicBundle.getString("TopicName");
            courseName = topicBundle.getString("CourseName");
        }

        //set views
        topicHeader = (TextView) findViewById(R.id.textView_course_topic);
        btnViewPdf = (Button) findViewById(R.id.button_view_pdf);
        btnViewVideo = (Button) findViewById(R.id.button_view_video);
        btnViewQuiz = (Button) findViewById(R.id.quiz_button);
        topicHeader.setText(topicName);

        //Read current pdfCounter of pdf views for the current topic
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child("CourseTopics").child(topicName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                pdfViewCount = map.get("pdf_views");
                videoViewCount = map.get("video_views");
                pdfPath = map.get("pdf_path");
                videoPath = map.get("video_path");

                pdfCounter = Integer.parseInt(pdfViewCount);
                videoCounter = Integer.parseInt(videoViewCount);

                Log.i(TAG, "pdf_view count: " + pdfCounter.toString());
                Log.i(TAG, "video_view count: " + videoCounter.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Add listeners for buttons
        btnViewPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfCounter++;
                mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child("CourseTopics").child(topicName).child("pdf_views").setValue(pdfCounter.toString());
                Intent pdfIntent = new Intent(getApplicationContext(), A5_ViewCoursePDF.class);
                pdfIntent.putExtra("PdfDownloadPath", pdfPath);
                pdfIntent.putExtra("CourseName", courseName);
                pdfIntent.putExtra("TopicName", topicName);
                startActivity(pdfIntent);
            }
        });

        btnViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoCounter++;
                mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child("CourseTopics").child(topicName).child("video_views").setValue(videoCounter.toString());
                Intent vidIntent = new Intent(getApplicationContext(), A6_ViewCourseVideo.class);
                vidIntent.putExtra("VideoDownloadPath", videoPath);
                vidIntent.putExtra("CourseName", courseName);
                vidIntent.putExtra("TopicName", topicName);
                startActivity(vidIntent);
            }
        });

        btnViewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(getApplicationContext(), A7_QuizActivity.class);
                quizIntent.putExtra("CourseName", courseName);
                quizIntent.putExtra("TopicName", topicName);
                startActivity(quizIntent);
            }
        });
    }
}
