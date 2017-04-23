package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class A2b_DashProfileActivity extends BaseActivity {

    private ImageView userProfPic;
    private TextView userProfName;
    private TextView ls1TextView;
    private TextView ls2TextView;
    private TextView ls3TextView;
    private TextView ls4TextView;
    Toolbar profileToolbar;
    RelativeLayout profileRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6_2_dash_profile);
        profileToolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(profileToolbar);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        profileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        profileRelativeLayout = (RelativeLayout) findViewById(R.id.profile_relative_layout);
        profileRelativeLayout.setVisibility(View.INVISIBLE);

        userProfName = (TextView) findViewById(R.id.tv_user_profile_name);
        userProfPic = (ImageView) findViewById(R.id.user_profile_image_2);
        ls1TextView = (TextView) findViewById(R.id.text_user_ls1);
        ls2TextView = (TextView) findViewById(R.id.text_user_ls2);
        ls3TextView = (TextView) findViewById(R.id.text_user_ls3);
        ls4TextView = (TextView) findViewById(R.id.text_user_ls4);

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
                profileRelativeLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_get_help:
                Intent mIntent = new Intent(getApplicationContext(), A2b_HelpInfoActivity.class);
                startActivity(mIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}