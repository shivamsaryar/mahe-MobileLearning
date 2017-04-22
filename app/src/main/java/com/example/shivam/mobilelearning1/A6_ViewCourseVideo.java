package com.example.shivam.mobilelearning1;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class A6_ViewCourseVideo extends BaseActivity {

    private static final String TAG = "PlayVideoActivity";
    String vidAddress = "https://firebasestorage.googleapis.com/v0/b/mobilelearning1-576b3.appspot.com/o/Courses%2FJava%20-%20Computer%20Science%2F1-Introduction%2F1-Introduction.mp4?alt=media&token=f1648fdf-43cb-4589-8b64-7a76253ba069";
    StorageReference pathVideoRef;
    VideoView courseVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_view_course_video);
        pathVideoRef = mStorageRef.child("Courses/Java - Computer Science/1-Introduction/1-Introduction.mp4");

        courseVideoView = (VideoView) findViewById(R.id.videoView);

        mStorageRef.child("Courses/Java - Computer Science/1-Introduction/1-Introduction.mp4").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                android.widget.MediaController mediaController = new android.widget.MediaController(getApplicationContext());
                mediaController.setAnchorView(courseVideoView);
                String url2 = "https://firebasestorage.googleapis.com/v0/b/mobilelearning1-576b3.appspot.com/o/Courses%2FJava+-+Computer+Science%2F1-Introduction%2F1-Introduction.mp4?alt=media&token=f1648fdf-43cb-4589-8b64-7a76253ba069";
                courseVideoView.setMediaController(mediaController);
                courseVideoView.setVideoURI(Uri.parse(url2));
                courseVideoView.requestFocus();
                courseVideoView.start();
            }
        });
    }
}
