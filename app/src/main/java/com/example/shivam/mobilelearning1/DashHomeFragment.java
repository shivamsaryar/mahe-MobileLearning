package com.example.shivam.mobilelearning1;

import android.content.Context;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashHomeFragment extends Fragment {

    private static final String TAG = "DashboardActivity";
    private OnFragmentInteractionListener mListener;
    View view;
    LinearLayout homeLinearLayout;
    LinearLayout.LayoutParams layoutParams;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRootRef;
    ArrayList<String> enrolledCourses = new ArrayList<String>();
    ProgressBar homeProgressBar;

    public DashHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mRootRef.child("users").child(mUser.getUid()).child("Enrolled_Courses").child("Ongoing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                homeProgressBar.setVisibility(View.VISIBLE);

                for(DataSnapshot child:dataSnapshot.getChildren()){
                    enrolledCourses.add(child.getKey());
                    Log.i(TAG, child.getKey());
                    String courseName = child.getKey();

                    //Create buttons of the list of courses
                    createCourseButtons(courseName);
                }
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
        homeProgressBar = (ProgressBar) view.findViewById(R.id.dashHome_progressBar);
        homeLinearLayout = (LinearLayout) view.findViewById(R.id.home_linear_layout);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);
        return view;
    }

    private void createCourseButtons(String courseName) {
        final Button myButton = new Button(getActivity());
        myButton.setText(courseName);
        myButton.setBackgroundColor(0xff0000ff);
        myButton.setTextColor(0xffffffff);
        myButton.setLayoutParams(layoutParams);
        homeLinearLayout.addView(myButton, layoutParams);
        Log.i(TAG, "button '" + courseName + "' added");
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