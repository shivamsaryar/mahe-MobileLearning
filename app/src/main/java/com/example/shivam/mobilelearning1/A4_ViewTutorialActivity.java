package com.example.shivam.mobilelearning1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class A4_ViewTutorialActivity extends AppCompatActivity {

    VideoView mVideoView;
    private StorageReference mStorageRef;
    String videoURL = "https://firebasestorage.googleapis.com/v0/b/mobilelearning1-576b3.appspot.com/o/Videos%2FJava%20Programming%20Tutorial%20-%202%20-%20Running%20a%20%20Java%20Program.mp4?alt=media&token=07ab6488-50f7-4a11-981a-06f27df29a49";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_view_tutorial);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mVideoView = (VideoView) findViewById(R.id.videoView);
        StorageReference videoRef = mStorageRef.child("Videos/Java Programming Tutorial - 2 - Running a Java Program.mp4");

        videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //mVideoView.setVideoURI(uri);
                //mVideoView.start();

                MediaController mc = new MediaController(getApplicationContext());
                mc.setAnchorView(mVideoView);
                mc.setMediaPlayer(mVideoView);
                mVideoView.setMediaController(mc);
                mVideoView.setVideoURI(Uri.parse(videoURL));
                mVideoView.start();
            }
        });
    }
}
