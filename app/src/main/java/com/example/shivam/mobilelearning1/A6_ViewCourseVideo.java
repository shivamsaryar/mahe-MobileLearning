package com.example.shivam.mobilelearning1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;

public class A6_ViewCourseVideo extends BaseActivity {

    Button playButton;
    VideoView videoView;
    android.widget.MediaController mediaController;
    String videoDownloadURL = "https://firebasestorage.googleapis.com/v0/b/mobilelearning1-576b3.appspot.com/o/Courses%2FJava%20-%20Computer%20Science%2F1-Introduction%2FJava%20Programming%20Tutorial%20-%201%20-%20Installing%20the%20JDK.3gp?alt=media&token=c798fe3c-0017-44b4-9dc5-77d868eaf989";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_view_course_video);

        videoView = (VideoView) findViewById(R.id.videoView);
        mediaController = new android.widget.MediaController(this);
        playButton = (Button) findViewById(R.id.button_play_video);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(A6_ViewCourseVideo.this, "Now playing...", Toast.LENGTH_SHORT).show();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoPath(videoDownloadURL);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);
                videoView.requestFocus();
                videoView.start();
            }
        });
    }
}
