package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class A2b_DashProfileActivity extends BaseActivity {

    private static final String TAG = "A2b_DashProfileActivity";

    private ImageView userProfPic;
    private TextView userProfName;
    private TextView ls1TextView;
    private TextView ls2TextView;
    private TextView ls3TextView;
    private TextView ls4TextView;
    Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6_2_dash_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Profile");

        userProfName = (TextView) findViewById(R.id.tv_user_profile_name);
        userProfPic = (ImageView) findViewById(R.id.user_profile_image_2);
        ls1TextView = (TextView) findViewById(R.id.text_user_ls1);
        ls2TextView = (TextView) findViewById(R.id.text_user_ls2);
        ls3TextView = (TextView) findViewById(R.id.text_user_ls3);
        ls4TextView = (TextView) findViewById(R.id.text_user_ls4);
        helpButton = (Button) findViewById(R.id.button_help);

        userProfName.setText(mUser.getDisplayName());
        Picasso.with(getApplicationContext()).load(mUser.getPhotoUrl()).into(userProfPic);

        mRootRef.child("users").child(mUser.getUid()).child("Learning Styles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                ls1TextView.setText(map.get("learning_style_1").substring(2)); //Trim 1st 2 characters from the string.
                ls2TextView.setText(map.get("learning_style_2").substring(2));
                ls3TextView.setText(map.get("learning_style_3").substring(2));
                ls4TextView.setText(map.get("learning_style_4").substring(2));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), A2b_HelpInfoActivity.class);
                startActivity(mIntent);
            }
        });
    }
}