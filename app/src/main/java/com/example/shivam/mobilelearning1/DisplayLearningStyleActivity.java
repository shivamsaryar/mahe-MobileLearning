package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DisplayLearningStyleActivity extends BaseActivity{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Bundle questionnaireData;
    ArrayList<String> app_user_learning_style;

    DatabaseReference mRootRef;
    String app_user_name;
    String app_user_email;
    String app_user_id;

    TextView textViewResult1;
    TextView textViewResult2;
    Button toDashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_learning_style);
        textViewResult1 = (TextView) findViewById(R.id.text_view_result_1);
        textViewResult2 = (TextView) findViewById(R.id.text_view_result_2);
        toDashboardButton = (Button) findViewById(R.id.button_to_dashboard);
        toDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDashIntent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(toDashIntent);
            }
        });

        mRootRef = FirebaseDatabase.getInstance().getReference();

        //GET LEARNING STYLE DATA FROM BUNDLE
        app_user_learning_style = new ArrayList<String>();
        questionnaireData = getIntent().getExtras();
        app_user_learning_style = questionnaireData.getStringArrayList("UserLearningStyle");
        //END GET BUNDLE DATA

        //DISPLAY LEARNING STYLE TO THE USER
        textViewResult2.setText(app_user_learning_style.get(0) + "\n" + app_user_learning_style.get(1) + "\n" + app_user_learning_style.get(2) + "\n" + app_user_learning_style.get(3));

        //[START get current Firebase User details]
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    app_user_name = user.getDisplayName().toString();
                    app_user_id = user.getUid().toString();
                    app_user_email = user.getEmail().toString();
                } else {
                    Toast.makeText(DisplayLearningStyleActivity.this, "Please Sign In to save this information.", Toast.LENGTH_SHORT).show();
                }
                pushDataToFirebase(user);
            }
        };
        // [END get current Firebase User details]
    }

    ////PUSH DATA TO FIREBASE DATABASE
    public void pushDataToFirebase(FirebaseUser user) {
        if(user != null){
            mRootRef.child("users").child(app_user_id).child("name").setValue(app_user_name);
            mRootRef.child("users").child(app_user_id).child("email").setValue(app_user_email);
            mRootRef.child("users").child(app_user_id).child("learning_style_1").setValue(app_user_learning_style.get(0));
            mRootRef.child("users").child(app_user_id).child("learning_style_2").setValue(app_user_learning_style.get(1));
            mRootRef.child("users").child(app_user_id).child("learning_style_3").setValue(app_user_learning_style.get(2));
            mRootRef.child("users").child(app_user_id).child("learning_style_4").setValue(app_user_learning_style.get(3));
        }
        else{
            Toast.makeText(this, "Learning Style data not available. Sign in to view.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
