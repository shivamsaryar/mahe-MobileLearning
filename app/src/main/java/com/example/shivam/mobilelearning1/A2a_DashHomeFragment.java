package com.example.shivam.mobilelearning1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class A2a_DashHomeFragment extends Fragment {

    private static final String TAG = "A2_DashboardActivity";
    private OnFragmentInteractionListener mListener;
    View view;
    LinearLayout homeLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRootRef;
    ArrayList<String> enrolledCourses;
    ProgressBar homeProgressBar;
    String courseName;
    TextView noCoursesText;

    public A2a_DashHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        enrolledCourses = new ArrayList<>();

        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Show progressBar while loading the buttons
                homeProgressBar.setVisibility(View.VISIBLE);

                //refresh initial layout
                homeLinearLayout.removeAllViews();

                //Get snapshot of all the children of users/enrolled_courses/Ongoing
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    enrolledCourses.add(child.getKey());
                    Log.i(TAG, child.getKey());
                    courseName = child.getKey();
                    //Call createButton function for each of the children
                    createCourseButtons(courseName);
                }

                if(enrolledCourses.size() == 0){
                    noCoursesText.setVisibility(View.VISIBLE);
                }
                //Hide progress bar after the creation of all buttons
                homeProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_1_dash_home, container, false);
        noCoursesText = (TextView) view.findViewById(R.id.textViewNoCourses);
        noCoursesText.setVisibility(View.INVISIBLE);
        homeProgressBar = (ProgressBar) view.findViewById(R.id.dashHome_progressBar);
        homeLinearLayout = (LinearLayout) view.findViewById(R.id.home_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);
        return view;
    }

    private void createCourseButtons(final String courseName) {

        final Button myButton = new Button(getActivity());
        myButton.setText(courseName);
        myButton.setBackgroundColor(Color.RED);
        myButton.setTextColor(Color.WHITE);
        myButton.setLayoutParams(layoutParams);
        homeLinearLayout.addView(myButton, layoutParams);
        Log.i(TAG, "button '" + courseName + "' added");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), A3_CourseTopicsActivity.class);
                mIntent.putExtra("CourseName", myButton.getText().toString());
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}