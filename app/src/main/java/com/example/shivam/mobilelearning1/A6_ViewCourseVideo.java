package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class A6_ViewCourseVideo extends BaseActivity {

    private static final String TAG = "ViewCourseVideoActivity";
    Button playButton;
    VideoView videoView;
    android.widget.MediaController mediaController;
    String videoDownloadURL = "https://firebasestorage.googleapis.com/v0/b/mobilelearning1-576b3.appspot.com/o/Courses%2FJava%20-%20Computer%20Science%2F1-Introduction%2FJava%20Programming%20Tutorial%20-%201%20-%20Installing%20the%20JDK.3gp?alt=media&token=c798fe3c-0017-44b4-9dc5-77d868eaf989";
    Toolbar videoToolbar;
    Bundle videoBundle;
    String videoPath;
    String courseName;
    String topicName;
    ArrayList<String> topicList;
    ArrayList<String> otherTopicsList;
    String nextTopic;
    Button nextVideoButton;
    LinearLayout videoLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    int topicIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_view_course_video);
        videoToolbar = (Toolbar) findViewById(R.id.video_toolbar);
        setSupportActionBar(videoToolbar);
        getSupportActionBar().setTitle("Topic Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        videoToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        topicList = new ArrayList<>();
        otherTopicsList = new ArrayList<>();

        videoBundle = getIntent().getExtras();
        videoPath = videoBundle.getString("VideoDownloadPath");
        courseName = videoBundle.getString("CourseName");
        topicName = videoBundle.getString("TopicName");
        getSupportActionBar().setTitle(topicName);
        getSupportActionBar().setSubtitle(courseName);
        Log.i(TAG, "Video download path from bundle: " + videoPath);

        videoView = (VideoView) findViewById(R.id.videoView);
        mediaController = new android.widget.MediaController(this);
        playButton = (Button) findViewById(R.id.button_play_video);
        nextVideoButton = (Button) findViewById(R.id.next_video_suggestion_button);
        videoLinearLayout = (LinearLayout) findViewById(R.id.video_activity_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);

        //get list of all topics
        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").child(courseName).child("CourseTopics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    Log.i(TAG, child.getKey());
                    topicList.add(child.getKey());

                }
                getTopicIndex();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //listener for when video is ready to play
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(A6_ViewCourseVideo.this, "Now playing...", Toast.LENGTH_SHORT).show();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    videoView.stopPlayback();
                    playButton.setText("Play");
                }
                else{
                    playButton.setText("Stop");
                    videoView.setVideoPath(videoPath);
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    videoView.requestFocus();
                    videoView.start();
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
        }
        else{
            nextTopic = topicList.get(topicIndex+1);
        }
        Log.i(TAG, "next topic: " + nextTopic);

        //setNextVideoSuggestion(); //for sequential learners
        setOtherVideoSuggestions(); //for global learners
    }

    //for global learners
    private void setOtherVideoSuggestions(){
        nextVideoButton.setVisibility(View.INVISIBLE);
        otherTopicsList = topicList;
        otherTopicsList.remove(topicIndex);
        for(int i=0; i<otherTopicsList.size();i++){
            final Button mButton = new Button(getApplicationContext());
            mButton.setText(otherTopicsList.get(i));
            mButton.setBackgroundColor(Color.RED);
            mButton.setTextColor(Color.WHITE);
            mButton.setLayoutParams(layoutParams);
            videoLinearLayout.addView(mButton, layoutParams);
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

    //for sequential learners
    private void setNextVideoSuggestion() {
        nextVideoButton.setText(nextTopic);
        nextVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, nextTopic);
                Intent courseDetailsIntent = new Intent(getApplicationContext(), A4_TopicFiles.class);
                courseDetailsIntent.putExtra("TopicName", nextTopic);
                courseDetailsIntent.putExtra("CourseName", courseName);
                startActivity(courseDetailsIntent);
            }
        });
    }

    private void createTopicButtons(String str) {
        final Button myButton = new Button(getApplicationContext());
        myButton.setText(str);
        myButton.setBackgroundColor(Color.BLUE);
        myButton.setTextColor(Color.WHITE);
        myButton.setLayoutParams(layoutParams);
        videoLinearLayout.addView(myButton, layoutParams);
        Log.i(TAG, "button '" + str + "' added");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, myButton.getText().toString());
                Intent courseDetailsIntent = new Intent(getApplicationContext(), A4_TopicFiles.class);
                courseDetailsIntent.putExtra("TopicName", myButton.getText().toString());
                courseDetailsIntent.putExtra("CourseName", courseName);
                startActivity(courseDetailsIntent);
            }
        });
    }
}
