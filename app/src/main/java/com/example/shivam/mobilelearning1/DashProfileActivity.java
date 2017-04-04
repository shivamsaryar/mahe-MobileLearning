package com.example.shivam.mobilelearning1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class DashProfileActivity extends AppCompatActivity {

    private static final String TAG = "DashProfileActivity";

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseAuth.AuthStateListener mAuthListener;

    private ImageView userProfPic;
    private TextView userProfName;
    private TextView learningStyleInfoText;
    DatabaseReference mRootRef;
    DatabaseReference mLSRef;
    String ls_1;
    String ls_2;
    String ls_3;
    String ls_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Profile");

        userProfName = (TextView) findViewById(R.id.tv_user_name_2);
        userProfPic = (ImageView) findViewById(R.id.user_profile_image_2);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        updateUI();
    }

    private void updateUI() {
        userProfName.setText(mUser.getDisplayName());
        Picasso.with(getApplicationContext()).load(mUser.getPhotoUrl()).into(userProfPic);
    }
}