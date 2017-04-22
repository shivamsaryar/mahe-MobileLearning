package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class A1c_DisplayLearningStyleActivity extends BaseActivity{

    Bundle questionnaireData;
    ArrayList<String> app_user_learning_style;
    String app_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5_display_learning_style);

        mUser = mAuth.getCurrentUser();
        app_user_id = mUser.getUid();

        //GET LEARNING STYLE DATA FROM BUNDLE
        app_user_learning_style = new ArrayList<String>();
        questionnaireData = getIntent().getExtras();
        app_user_learning_style = questionnaireData.getStringArrayList("UserLearningStyle");
        pushDataToFirebase(mUser);
        Intent dashIntent = new Intent(getApplicationContext(), A2_DashboardActivity.class);
        startActivity(dashIntent);
    }

    ////PUSH DATA TO FIREBASE DATABASE
    public void pushDataToFirebase(FirebaseUser user) {
        if(user != null){
            mRootRef.child("users").child(app_user_id).child("Learning Styles").child("learning_style_1").setValue(app_user_learning_style.get(0));
            mRootRef.child("users").child(app_user_id).child("Learning Styles").child("learning_style_2").setValue(app_user_learning_style.get(1));
            mRootRef.child("users").child(app_user_id).child("Learning Styles").child("learning_style_3").setValue(app_user_learning_style.get(2));
            mRootRef.child("users").child(app_user_id).child("Learning Styles").child("learning_style_4").setValue(app_user_learning_style.get(3));
        }
        else{
            Toast.makeText(this, "Learning Style data not available. Sign in to view.", Toast.LENGTH_SHORT).show();
        }
    }
}
