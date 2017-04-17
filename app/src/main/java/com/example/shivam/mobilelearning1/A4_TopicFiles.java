package com.example.shivam.mobilelearning1;

import android.os.Bundle;

import com.google.firebase.storage.StorageReference;

public class A4_TopicFiles extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_topic_files);

        StorageReference sr = mStorageRef.child("Wallpapers/abstract_wallpaper_1.jpg");
        //Glide.with(this)
          //      .using(new FirebaseImageLoader())
            //    .load(sr)
              //  .into(mImage);
    }
}
