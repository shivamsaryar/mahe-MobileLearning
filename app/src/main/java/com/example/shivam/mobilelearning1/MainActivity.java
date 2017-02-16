package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends BaseActivity{
    /*
    APPLICATION ACTIVITY SEQUENCE:
    BaseActivity
    MainActivity
    QuestionnaireScrollingActivity
    DisplayLearningStyleActivity
    Dashboard
    */
    Button proceedToGoogleSignIn;
    Button proceedToQuestionnaire;
    ImageView profilePic;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    Uri imgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_main);

        profilePic = (ImageView) findViewById(R.id.imageView_profile_pic);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if(mUser != null){

            imgUri = Uri.parse(String.valueOf(mUser.getPhotoUrl()));

            /*
            InputStream image_stream = null;
            try {
                image_stream = getContentResolver().openInputStream(IMAGE_URI);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap= BitmapFactory.decodeStream(image_stream );
            profilePic.setImageBitmap(bitmap);
            */
            profilePic.setImageURI(null);
            profilePic.setImageURI(imgUri);


        }
        else{
        }

        proceedToQuestionnaire = (Button) findViewById(R.id.proceed_to_questionnaire_button);
        final Intent proceedToQuestionnaireIntent = new Intent(this, QuestionnaireScrollingActivity.class);
        proceedToQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(proceedToQuestionnaireIntent);
            }
        });

        proceedToGoogleSignIn = (Button) findViewById(R.id.proceed_to_google_signin);
        proceedToGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mGoogleSignInIntent = new Intent(getApplicationContext(), GoogleSignInActivity.class);
                startActivity(mGoogleSignInIntent);
            }
        });
    }
}
