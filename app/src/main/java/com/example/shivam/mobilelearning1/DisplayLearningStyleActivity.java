package com.example.shivam.mobilelearning1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DisplayLearningStyleActivity extends AppCompatActivity {

    Button updateButton;
    Bundle questionnaireData;
    ArrayList<String> app_user_learning_style;

    String app_user_id = "99z2LbpqPeNWwucP9UAsnlw9ZtG3";
    String app_user_name = "Shivam Saryar";
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

    //GET REFERENCES TO THE CHILDREN OF EACH USER
    DatabaseReference mUserIdRef = mUserNodeRef.child(node_id);
    DatabaseReference mUserName = mUserIdRef.child(node_name); //referring node 'name'
    DatabaseReference mUserLearningStyle1 = mUserIdRef.child(node_learning_style_1);
    DatabaseReference mUserLearningStyle2 = mUserIdRef.child(node_learning_style_2);
    DatabaseReference mUserLearningStyle3 = mUserIdRef.child(node_learning_style_3);
    DatabaseReference mUserLearningStyle4 = mUserIdRef.child(node_learning_style_4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_learning_style);
        app_user_learning_style = new ArrayList<String>();
        questionnaireData = getIntent().getExtras();
        app_user_learning_style = questionnaireData.getStringArrayList("UserLearningStyle");

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
}
