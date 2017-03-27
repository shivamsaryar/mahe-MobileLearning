package com.example.shivam.mobilelearning1;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class DashDiscoverActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_discover);

        mImage = (ImageView) findViewById(R.id.img_test);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference sr = mStorageRef.child("Wallpapers/abstract_wallpaper_1.jpg");
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(sr)
                .into(mImage);
    }
}
