package com.example.shivam.mobilelearning1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ViewTutorialActivity extends AppCompatActivity {

    VideoView mVideoView;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8_view_tutorial);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mVideoView = (VideoView) findViewById(R.id.videoView);
        StorageReference videoRef = mStorageRef.child("Videos/Java Programming Tutorial - 2 - Running a Java Program.mp4");

        videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                mVideoView.setVideoURI(uri);
            }
        });
    }
}
