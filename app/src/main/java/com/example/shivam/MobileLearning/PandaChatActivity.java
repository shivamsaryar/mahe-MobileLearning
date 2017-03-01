package com.example.shivam.MobileLearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PandaChatActivity extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference chatRef = mRootRef.child("chat").child("Friends").child("Upasana");
    EditText edit_chat_text;
    Button buttonSend;
    TextView inboxText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_0_panda_chat);


        edit_chat_text = (EditText) findViewById(R.id.editText_chat);
        inboxText = (TextView) findViewById(R.id.inbox_text_view);
        buttonSend = (Button) findViewById(R.id.send_button);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootRef.child("chat").child("Friends").child("Upasana").setValue("You" + edit_chat_text.getText().toString());
                //mRootRef.child("chat").child("Friends").child("Upasana").push(edit_chat_text.getText().toString());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String chat_msg_rcv = dataSnapshot.getValue(String.class);
                inboxText.setText(chat_msg_rcv);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
