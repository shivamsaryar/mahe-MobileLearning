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
    ArrayList<String> user_learning_style;
    String user_id = "99z2LbpqPeNWwucP9UAsnlw9ZtG3";
    String user = "user";
    String node_id = "user_id";
    String node_name = "name";
    String node_learning_style1 = "learning_style_1";
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUserNodeRef = mRootRef.child(user);
    DatabaseReference mUserIdRef = mUserNodeRef.child(node_id);
    DatabaseReference mUserName = mUserNodeRef.child(node_name);
    DatabaseReference mUserLearningStyle = mUserNodeRef.child(node_learning_style1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_learning_style);

        updateButton = (Button) findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserIdRef.setValue(user_id);
                mUserName.setValue("Shivam");
                mUserLearningStyle.setValue(user_learning_style.get(0).toString());
            }
        });
        user_learning_style = new ArrayList<String>();
        questionnaireData = getIntent().getExtras();
        user_learning_style = questionnaireData.getStringArrayList("UserLearningStyle");
        //Toast.makeText(this, "Learning style:\n"+ user_learning_style.get(0) + "\n" + user_learning_style.get(1)+"\n"+ user_learning_style.get(2) +"\n"+ user_learning_style.get(3), Toast.LENGTH_SHORT).show();

    }
}
