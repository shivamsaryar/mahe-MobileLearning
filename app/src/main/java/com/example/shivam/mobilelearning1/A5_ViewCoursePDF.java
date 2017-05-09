package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class A5_ViewCoursePDF extends BaseActivity {

    Toolbar pdfViewToolbar;
    private static final String TAG = "ViewPdfActivity";
    WebView myPdfWebView;
    Bundle pdfPathBundle;
    String pdfUrl;
    String videoUrl;
    LinearLayout pdfViewLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    TextView nextPdfTxt;
    TextView videoSuggTxt;
    Button viewCurrentPdfBtn;
    Button nextPdfBtn;
    Button viewTopicVideoBtn;
    Boolean isLastPdf;
    String courseName;
    String topicName;
    int topicIndex;
    ArrayList<String> topicList;
    ArrayList<String> otherTopicsList;
    String nextTopic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_view_course_pdf);
        pdfViewToolbar = (Toolbar) findViewById(R.id.pdf_view_toolbar);
        setSupportActionBar(pdfViewToolbar);
        getSupportActionBar().setTitle("Topic Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pdfViewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        pdfPathBundle = getIntent().getExtras();
        pdfUrl = pdfPathBundle.getString("PdfDownloadPath");
        courseName = pdfPathBundle.getString("CourseName");
        topicName = pdfPathBundle.getString("TopicName");
        videoUrl = pdfPathBundle.getString("VideoDownloadPath");
        getSupportActionBar().setTitle(topicName);
        getSupportActionBar().setSubtitle(courseName);

        pdfViewLinearLayout = (LinearLayout) findViewById(R.id.pdf_view_linear_layout);
        nextPdfTxt = (TextView) findViewById(R.id.view_next_pdf_text);
        videoSuggTxt = (TextView) findViewById(R.id.video_sugg_btn);

        viewCurrentPdfBtn = (Button) findViewById(R.id.view_pdf_button);
        viewCurrentPdfBtn.setBackgroundColor(Color.RED);
        viewCurrentPdfBtn.setTextColor(Color.WHITE);

        nextPdfBtn = (Button) findViewById(R.id.next_pdf_button);
        nextPdfBtn.setBackgroundColor(Color.BLUE);
        nextPdfBtn.setTextColor(Color.WHITE);

        viewTopicVideoBtn = (Button) findViewById(R.id.view_topic_video_button);
        viewTopicVideoBtn.setBackgroundColor(Color.MAGENTA);
        viewTopicVideoBtn.setTextColor(Color.WHITE);

        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);

        viewCurrentPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set pdf into google pdf viewer
                myPdfWebView = (WebView) findViewById(R.id.pdf_web_view);
                myPdfWebView.getSettings().setJavaScriptEnabled(true);
                myPdfWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdfUrl);
                myPdfWebView.zoomOut();
            }
        });

        topicList = new ArrayList<>();
        otherTopicsList = new ArrayList<>();
        isLastPdf = false;

        //get list of all topics
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child("CourseTopics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    Log.i(TAG, child.getKey());
                    topicList.add(child.getKey());

                }
                getTopicIndex();
                getLearningStyle();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //view pdf of the same course
        viewTopicVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pdfIntent = new Intent(getApplicationContext(), A6_ViewCourseVideo.class);
                pdfIntent.putExtra("PdfDownloadPath", videoUrl);
                pdfIntent.putExtra("CourseName", courseName);
                pdfIntent.putExtra("TopicName", topicName);
                startActivity(pdfIntent);
            }
        });

        //view video of next topic in this same current activity
        nextPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLastPdf == false){
                    Intent restartPdfIntent = new Intent(A5_ViewCoursePDF.this, A5_ViewCoursePDF.class);
                    restartPdfIntent.putExtra("VideoDownloadPath", videoUrl);
                    restartPdfIntent.putExtra("PdfDownloadPath", pdfUrl);
                    restartPdfIntent.putExtra("CourseName", courseName);
                    restartPdfIntent.putExtra("TopicName", nextTopic);
                    A5_ViewCoursePDF.this.finish();
                    startActivity(restartPdfIntent);
                }
                else {
                    Intent doneIntent = new Intent(getApplicationContext(), A3_CourseTopicsActivity.class);
                    doneIntent.putExtra("CourseName", courseName);
                    startActivity(doneIntent);
                }
            }
        });

    }

    private void getTopicIndex() {
        //get index of topic selected
        topicIndex = topicList.indexOf(topicName);
        Log.i(TAG, "topic index" + ": " + topicIndex);
        if(topicIndex == topicList.size()-1){
            nextTopic = "No next topics, this is the last topic video of the course";
            //nextVideoButton.setVisibility(View.INVISIBLE);
            nextPdfTxt.setVisibility(View.INVISIBLE);
            isLastPdf = true;
        }
        else{
            nextTopic = topicList.get(topicIndex+1);
        }
        Log.i(TAG, "next topic: " + nextTopic);
        nextPdfBtn.setText(nextTopic + " (PDF)");
        viewTopicVideoBtn.setText(topicName + " (Video)");

        setOtherVideoSuggestions(); //for global learners
        if(isLastPdf == true){
            nextPdfBtn.setText("DONE");
        }
    }

    private void getLearningStyle() {
        //get user learning style
        mRootRef.child("users").child(mUser.getUid()).child("Learning Styles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                String learning_style_3 = map.get("learning_style_3");
                String learning_style_4 = map.get("learning_style_4");
                if(learning_style_3.equals("3_Visual Learner")){
                    viewTopicVideoBtn.setVisibility(View.INVISIBLE);
                    videoSuggTxt.setVisibility(View.INVISIBLE);
                    Log.i(TAG, "visual learner, no video recommendation needed");
                }
                if(learning_style_4.equals("4_Global Learner")){
                    nextPdfTxt.setVisibility(View.INVISIBLE);
                    nextPdfBtn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setOtherVideoSuggestions() {
        otherTopicsList = topicList;
        otherTopicsList.remove(topicIndex);
        for (int i = 0; i < otherTopicsList.size(); i++) {
            final Button mButton = new Button(getApplicationContext());
            mButton.setText(otherTopicsList.get(i));
            mButton.setBackgroundColor(Color.DKGRAY);
            mButton.setTextColor(Color.WHITE);
            mButton.setLayoutParams(layoutParams);
            pdfViewLinearLayout.addView(mButton, layoutParams);
            Log.i(TAG, "button " + otherTopicsList.get(i) + " added");
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, mButton.getText().toString());
                    Intent courseDetailsIntent = new Intent(getApplicationContext(), A4_TopicFiles.class);
                    courseDetailsIntent.putExtra("TopicName", mButton.getText().toString());
                    courseDetailsIntent.putExtra("CourseName", courseName);
                    startActivity(courseDetailsIntent);
                }
            });
        }
    }
}
