package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.storage.StorageReference;

public class A4_TopicFiles extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9_topic_files);

        Intent mIntent = new Intent(this, A6_ViewCourseVideo.class);
        startActivity(mIntent);
    }
}
