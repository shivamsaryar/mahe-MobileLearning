package com.example.shivam.mobilelearning1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DisplayLearningStyleActivity extends BaseActivity {

    private static final String TAG = "DisplayLearningStyleActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button updateButton;
    Bundle questionnaireData;
    ArrayList<String> app_user_learning_style;

    String app_user_id = "99z2LbpqPeNWwucP9UAsnlw9ZtG3";
    String app_user_name = "Shivam Saryar";
    String app_user_email = "";
    String user = "user_details";
    String node_id = app_user_id;

    //name the child nodes of 'user_details' node
    String node_name = "name";
    String node_learning_style_1 = "learning_style_1";
    String node_learning_style_2 = "learning_style_2";
    String node_learning_style_3 = "learning_style_3";
    String node_learning_style_4 = "learning_style_4";

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(); //Referring root_node
    DatabaseReference mUserNodeRef = mRootRef.child(user); //Referring node 'user'

    //DECLARE REFERENCES TO THE CHILDREN OF EACH USER
    DatabaseReference mUserIdRef = mUserNodeRef.child(node_id);
    DatabaseReference mUserName = mUserIdRef.child(node_name); //referring node 'name'
    DatabaseReference mUserLearningStyle1 = mUserIdRef.child(node_learning_style_1);
    DatabaseReference mUserLearningStyle2 = mUserIdRef.child(node_learning_style_2);
    DatabaseReference mUserLearningStyle3 = mUserIdRef.child(node_learning_style_3);
    DatabaseReference mUserLearningStyle4 = mUserIdRef.child(node_learning_style_4);
    //[END declare references, now go to DEFINE REFERENCES inside onCreate]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_learning_style);
        app_user_learning_style = new ArrayList<String>();
        questionnaireData = getIntent().getExtras();
        app_user_learning_style = questionnaireData.getStringArrayList("UserLearningStyle");

        //[START get current Firebase User details]
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    app_user_name = user.getDisplayName().toString();
                    app_user_id = user.getUid();
                    app_user_email = user.getEmail();

                } else {
                    Toast.makeText(DisplayLearningStyleActivity.this, "Please Sign In to save this information.", Toast.LENGTH_SHORT).show();
                }
                // [START_EXCLUDE]
                updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END get current Firebase User details]

        //[START DEFINE REFERENCES FOR THE NODES IN FIREBASE DATABASE]


        //INITIALIZE THE VALUES TO BE PUSHED TO THE NODES
        updateButton = (Button) findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mUserNodeRef.setValue(app_user_id);
                mUserName.setValue(app_user_name);
                mUserLearningStyle1.setValue(app_user_learning_style.get(0));
                mUserLearningStyle2.setValue(app_user_learning_style.get(1));
                mUserLearningStyle3.setValue(app_user_learning_style.get(2));
                mUserLearningStyle4.setValue(app_user_learning_style.get(3));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            //statusTextView.setText("Email:" + user.getEmail());
            //detailTextView.setText("User ID:" + user.getUid());
            //Toast.makeText(this, "Hello, " + user.getDisplayName(), Toast.LENGTH_SHORT).show();

        } else {
            //statusTextView.setText("Signed Out");
            //detailTextView.setText(null);
            //Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
        }
    }

}
