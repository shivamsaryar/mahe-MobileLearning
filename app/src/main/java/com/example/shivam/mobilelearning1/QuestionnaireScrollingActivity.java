package com.example.shivam.mobilelearning1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import static android.R.attr.checkedButton;
import static android.R.attr.onClick;

public class QuestionnaireScrollingActivity extends BaseActivity{

    RadioButton ans1a;
    RadioButton ans2a;
    RadioButton ans3a;
    RadioButton ans4a;
    RadioButton ans5a;
    RadioButton ans6a;
    RadioButton ans7a;
    RadioButton ans8a;
    RadioButton ans9a;
    RadioButton ans10a;
    RadioButton ans11a;
    RadioButton ans12a;
    RadioButton ans13a;
    RadioButton ans14a;
    RadioButton ans15a;
    RadioButton ans16a;
    RadioButton ans17a;
    RadioButton ans18a;
    RadioButton ans19a;
    RadioButton ans20a;
    RadioButton ans21a;
    RadioButton ans22a;
    RadioButton ans23a;
    RadioButton ans24a;
    RadioButton ans25a;
    RadioButton ans26a;
    RadioButton ans27a;
    RadioButton ans28a;
    RadioButton ans29a;
    RadioButton ans30a;
    RadioButton ans31a;
    RadioButton ans32a;
    RadioButton ans33a;
    RadioButton ans34a;
    RadioButton ans35a;
    RadioButton ans36a;
    RadioButton ans37a;
    RadioButton ans38a;
    RadioButton ans39a;
    RadioButton ans40a;
    RadioButton ans41a;
    RadioButton ans42a;
    RadioButton ans43a;
    RadioButton ans44a;

    RadioButton ans1b;
    RadioButton ans2b;
    RadioButton ans3b;
    RadioButton ans4b;
    RadioButton ans5b;
    RadioButton ans6b;
    RadioButton ans7b;
    RadioButton ans8b;
    RadioButton ans9b;
    RadioButton ans10b;
    RadioButton ans11b;
    RadioButton ans12b;
    RadioButton ans13b;
    RadioButton ans14b;
    RadioButton ans15b;
    RadioButton ans16b;
    RadioButton ans17b;
    RadioButton ans18b;
    RadioButton ans19b;
    RadioButton ans20b;
    RadioButton ans21b;
    RadioButton ans22b;
    RadioButton ans23b;
    RadioButton ans24b;
    RadioButton ans25b;
    RadioButton ans26b;
    RadioButton ans27b;
    RadioButton ans28b;
    RadioButton ans29b;
    RadioButton ans30b;
    RadioButton ans31b;
    RadioButton ans32b;
    RadioButton ans33b;
    RadioButton ans34b;
    RadioButton ans35b;
    RadioButton ans36b;
    RadioButton ans37b;
    RadioButton ans38b;
    RadioButton ans39b;
    RadioButton ans40b;
    RadioButton ans41b;
    RadioButton ans42b;
    RadioButton ans43b;
    RadioButton ans44b;

    //INITIALIZING RADIO GROUPS
    RadioGroup ans1RadioGroup;
    RadioGroup ans2RadioGroup;
    RadioGroup ans3RadioGroup;
    RadioGroup ans4RadioGroup;
    RadioGroup ans5RadioGroup;
    RadioGroup ans6RadioGroup;
    RadioGroup ans7RadioGroup;
    RadioGroup ans8RadioGroup;
    RadioGroup ans9RadioGroup;
    RadioGroup ans10RadioGroup;
    RadioGroup ans11RadioGroup;
    RadioGroup ans12RadioGroup;
    RadioGroup ans13RadioGroup;
    RadioGroup ans14RadioGroup;
    RadioGroup ans15RadioGroup;
    RadioGroup ans16RadioGroup;
    RadioGroup ans17RadioGroup;
    RadioGroup ans18RadioGroup;
    RadioGroup ans19RadioGroup;
    RadioGroup ans20RadioGroup;
    RadioGroup ans21RadioGroup;
    RadioGroup ans22RadioGroup;
    RadioGroup ans23RadioGroup;
    RadioGroup ans24RadioGroup;
    RadioGroup ans25RadioGroup;
    RadioGroup ans26RadioGroup;
    RadioGroup ans27RadioGroup;
    RadioGroup ans28RadioGroup;
    RadioGroup ans29RadioGroup;
    RadioGroup ans30RadioGroup;
    RadioGroup ans31RadioGroup;
    RadioGroup ans32RadioGroup;
    RadioGroup ans33RadioGroup;
    RadioGroup ans34RadioGroup;
    RadioGroup ans35RadioGroup;
    RadioGroup ans36RadioGroup;
    RadioGroup ans37RadioGroup;
    RadioGroup ans38RadioGroup;
    RadioGroup ans39RadioGroup;
    RadioGroup ans40RadioGroup;
    RadioGroup ans41RadioGroup;
    RadioGroup ans42RadioGroup;
    RadioGroup ans43RadioGroup;
    RadioGroup ans44RadioGroup;

    Button submitButton;
    Integer[] final_result;

    ArrayList<String> user_learning_style; //To be sent inside Intent Bundle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Questionnaire");

        //INITIALIZE THE FINAL_RESULT INTEGER CLASS ARRAY
        final_result = new Integer[44];
        for(int i=0; i<44; i++){
            final_result[i] = 0;
        }

        //Store learning style and send in bundle
        user_learning_style = new ArrayList<String>();

        //INITIALIZE RADIO BUTTONS FOR OPTION 'A'
        ans1a = (RadioButton) findViewById(R.id.ans1a_radioButton);
        ans2a = (RadioButton) findViewById(R.id.ans2a_radioButton);
        ans3a = (RadioButton) findViewById(R.id.ans3a_radioButton);
        ans4a = (RadioButton) findViewById(R.id.ans4a_radioButton);
        ans5a = (RadioButton) findViewById(R.id.ans5a_radioButton);
        ans6a = (RadioButton) findViewById(R.id.ans6a_radioButton);
        ans7a = (RadioButton) findViewById(R.id.ans7a_radioButton);
        ans8a = (RadioButton) findViewById(R.id.ans8a_radioButton);
        ans9a = (RadioButton) findViewById(R.id.ans9a_radioButton);
        ans10a = (RadioButton) findViewById(R.id.ans10a_radioButton);
        ans11a = (RadioButton) findViewById(R.id.ans11a_radioButton);
        ans12a = (RadioButton) findViewById(R.id.ans12a_radioButton);
        ans13a = (RadioButton) findViewById(R.id.ans13a_radioButton);
        ans14a = (RadioButton) findViewById(R.id.ans14a_radioButton);
        ans15a = (RadioButton) findViewById(R.id.ans15a_radioButton);
        ans16a = (RadioButton) findViewById(R.id.ans16a_radioButton);
        ans17a = (RadioButton) findViewById(R.id.ans17a_radioButton);
        ans18a = (RadioButton) findViewById(R.id.ans18a_radioButton);
        ans19a = (RadioButton) findViewById(R.id.ans19a_radioButton);
        ans20a = (RadioButton) findViewById(R.id.ans20a_radioButton);
        ans21a = (RadioButton) findViewById(R.id.ans21a_radioButton);
        ans22a = (RadioButton) findViewById(R.id.ans22a_radioButton);
        ans23a = (RadioButton) findViewById(R.id.ans23a_radioButton);
        ans24a = (RadioButton) findViewById(R.id.ans24a_radioButton);
        ans25a = (RadioButton) findViewById(R.id.ans25a_radioButton);
        ans26a = (RadioButton) findViewById(R.id.ans26a_radioButton);
        ans27a = (RadioButton) findViewById(R.id.ans27a_radioButton);
        ans28a = (RadioButton) findViewById(R.id.ans28a_radioButton);
        ans29a = (RadioButton) findViewById(R.id.ans29a_radioButton);
        ans30a = (RadioButton) findViewById(R.id.ans30a_radioButton);
        ans31a = (RadioButton) findViewById(R.id.ans31a_radioButton);
        ans32a = (RadioButton) findViewById(R.id.ans32a_radioButton);
        ans33a = (RadioButton) findViewById(R.id.ans33a_radioButton);
        ans34a = (RadioButton) findViewById(R.id.ans34a_radioButton);
        ans35a = (RadioButton) findViewById(R.id.ans35a_radioButton);
        ans36a = (RadioButton) findViewById(R.id.ans36a_radioButton);
        ans37a = (RadioButton) findViewById(R.id.ans37a_radioButton);
        ans38a = (RadioButton) findViewById(R.id.ans38a_radioButton);
        ans39a = (RadioButton) findViewById(R.id.ans39a_radioButton);
        ans40a = (RadioButton) findViewById(R.id.ans40a_radioButton);
        ans41a = (RadioButton) findViewById(R.id.ans41a_radioButton);
        ans42a = (RadioButton) findViewById(R.id.ans42a_radioButton);
        ans43a = (RadioButton) findViewById(R.id.ans43a_radioButton);
        ans44a = (RadioButton) findViewById(R.id.ans44a_radioButton);

        //INITIALIZE RADIO BUTTONS FOR OPTION 'B'
        ans1b = (RadioButton) findViewById(R.id.ans1b_radioButton);
        ans2b = (RadioButton) findViewById(R.id.ans2b_radioButton);
        ans3b = (RadioButton) findViewById(R.id.ans3b_radioButton);
        ans4b = (RadioButton) findViewById(R.id.ans4b_radioButton);
        ans5b = (RadioButton) findViewById(R.id.ans5b_radioButton);
        ans6b = (RadioButton) findViewById(R.id.ans6b_radioButton);
        ans7b = (RadioButton) findViewById(R.id.ans7b_radioButton);
        ans8b = (RadioButton) findViewById(R.id.ans8b_radioButton);
        ans9b = (RadioButton) findViewById(R.id.ans9b_radioButton);
        ans10b = (RadioButton) findViewById(R.id.ans10b_radioButton);
        ans11b = (RadioButton) findViewById(R.id.ans11b_radioButton);
        ans12b = (RadioButton) findViewById(R.id.ans12b_radioButton);
        ans13b = (RadioButton) findViewById(R.id.ans13b_radioButton);
        ans14b = (RadioButton) findViewById(R.id.ans14b_radioButton);
        ans15b = (RadioButton) findViewById(R.id.ans15b_radioButton);
        ans16b = (RadioButton) findViewById(R.id.ans16b_radioButton);
        ans17b = (RadioButton) findViewById(R.id.ans17b_radioButton);
        ans18b = (RadioButton) findViewById(R.id.ans18b_radioButton);
        ans19b = (RadioButton) findViewById(R.id.ans19b_radioButton);
        ans20b = (RadioButton) findViewById(R.id.ans20b_radioButton);
        ans21b = (RadioButton) findViewById(R.id.ans21b_radioButton);
        ans22b = (RadioButton) findViewById(R.id.ans22b_radioButton);
        ans23b = (RadioButton) findViewById(R.id.ans23b_radioButton);
        ans24b = (RadioButton) findViewById(R.id.ans24b_radioButton);
        ans25b = (RadioButton) findViewById(R.id.ans25b_radioButton);
        ans26b = (RadioButton) findViewById(R.id.ans26b_radioButton);
        ans27b = (RadioButton) findViewById(R.id.ans27b_radioButton);
        ans28b = (RadioButton) findViewById(R.id.ans28b_radioButton);
        ans29b = (RadioButton) findViewById(R.id.ans29b_radioButton);
        ans30b = (RadioButton) findViewById(R.id.ans30b_radioButton);
        ans31b = (RadioButton) findViewById(R.id.ans31b_radioButton);
        ans32b = (RadioButton) findViewById(R.id.ans32b_radioButton);
        ans33b = (RadioButton) findViewById(R.id.ans33b_radioButton);
        ans34b = (RadioButton) findViewById(R.id.ans34b_radioButton);
        ans35b = (RadioButton) findViewById(R.id.ans35b_radioButton);
        ans36b = (RadioButton) findViewById(R.id.ans36b_radioButton);
        ans37b = (RadioButton) findViewById(R.id.ans37b_radioButton);
        ans38b = (RadioButton) findViewById(R.id.ans38b_radioButton);
        ans39b = (RadioButton) findViewById(R.id.ans39b_radioButton);
        ans40b = (RadioButton) findViewById(R.id.ans40b_radioButton);
        ans41b = (RadioButton) findViewById(R.id.ans41b_radioButton);
        ans42b = (RadioButton) findViewById(R.id.ans42b_radioButton);
        ans43b = (RadioButton) findViewById(R.id.ans43b_radioButton);
        ans44b = (RadioButton) findViewById(R.id.ans44b_radioButton);

        //INITIALIZE RADIO GROUPS
        ans1RadioGroup = (RadioGroup) findViewById(R.id.ans1_radioGroup);
        ans2RadioGroup = (RadioGroup) findViewById(R.id.ans2_radioGroup);
        ans3RadioGroup = (RadioGroup) findViewById(R.id.ans3_radioGroup);
        ans4RadioGroup = (RadioGroup) findViewById(R.id.ans4_radioGroup);
        ans5RadioGroup = (RadioGroup) findViewById(R.id.ans5_radioGroup);
        ans6RadioGroup = (RadioGroup) findViewById(R.id.ans6_radioGroup);
        ans7RadioGroup = (RadioGroup) findViewById(R.id.ans7_radioGroup);
        ans8RadioGroup = (RadioGroup) findViewById(R.id.ans8_radioGroup);
        ans9RadioGroup = (RadioGroup) findViewById(R.id.ans9_radioGroup);
        ans10RadioGroup = (RadioGroup) findViewById(R.id.ans10_radioGroup);
        ans11RadioGroup = (RadioGroup) findViewById(R.id.ans11_radioGroup);
        ans12RadioGroup = (RadioGroup) findViewById(R.id.ans12_radioGroup);
        ans13RadioGroup = (RadioGroup) findViewById(R.id.ans13_radioGroup);
        ans14RadioGroup = (RadioGroup) findViewById(R.id.ans14_radioGroup);
        ans15RadioGroup = (RadioGroup) findViewById(R.id.ans15_radioGroup);
        ans16RadioGroup = (RadioGroup) findViewById(R.id.ans16_radioGroup);
        ans17RadioGroup = (RadioGroup) findViewById(R.id.ans17_radioGroup);
        ans18RadioGroup = (RadioGroup) findViewById(R.id.ans18_radioGroup);
        ans19RadioGroup = (RadioGroup) findViewById(R.id.ans19_radioGroup);
        ans20RadioGroup = (RadioGroup) findViewById(R.id.ans20_radioGroup);
        ans21RadioGroup = (RadioGroup) findViewById(R.id.ans21_radioGroup);
        ans22RadioGroup = (RadioGroup) findViewById(R.id.ans22_radioGroup);
        ans23RadioGroup = (RadioGroup) findViewById(R.id.ans23_radioGroup);
        ans24RadioGroup = (RadioGroup) findViewById(R.id.ans24_radioGroup);
        ans25RadioGroup = (RadioGroup) findViewById(R.id.ans25_radioGroup);
        ans26RadioGroup = (RadioGroup) findViewById(R.id.ans26_radioGroup);
        ans27RadioGroup = (RadioGroup) findViewById(R.id.ans27_radioGroup);
        ans28RadioGroup = (RadioGroup) findViewById(R.id.ans28_radioGroup);
        ans29RadioGroup = (RadioGroup) findViewById(R.id.ans29_radioGroup);
        ans30RadioGroup = (RadioGroup) findViewById(R.id.ans30_radioGroup);
        ans31RadioGroup = (RadioGroup) findViewById(R.id.ans31_radioGroup);
        ans32RadioGroup = (RadioGroup) findViewById(R.id.ans32_radioGroup);
        ans33RadioGroup = (RadioGroup) findViewById(R.id.ans33_radioGroup);
        ans34RadioGroup = (RadioGroup) findViewById(R.id.ans34_radioGroup);
        ans35RadioGroup = (RadioGroup) findViewById(R.id.ans35_radioGroup);
        ans36RadioGroup = (RadioGroup) findViewById(R.id.ans36_radioGroup);
        ans37RadioGroup = (RadioGroup) findViewById(R.id.ans37_radioGroup);
        ans38RadioGroup = (RadioGroup) findViewById(R.id.ans38_radioGroup);
        ans39RadioGroup = (RadioGroup) findViewById(R.id.ans39_radioGroup);
        ans40RadioGroup = (RadioGroup) findViewById(R.id.ans40_radioGroup);
        ans41RadioGroup = (RadioGroup) findViewById(R.id.ans41_radioGroup);
        ans42RadioGroup = (RadioGroup) findViewById(R.id.ans42_radioGroup);
        ans43RadioGroup = (RadioGroup) findViewById(R.id.ans43_radioGroup);
        ans44RadioGroup = (RadioGroup) findViewById(R.id.ans44_radioGroup);

        //INITIALIZE THE FINAL SUBMIT BUTTON
        submitButton = (Button) findViewById(R.id.SubmitAnswerButton);

        //SET CHECKED CHANGE LISTENERS FOR ALL RADIO GROUPS

        //Q1RADIO GROUP
        ans1RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans1a.isChecked()){
                    final_result[0] = 1;
                }
                else if(ans1b.isChecked()){
                    final_result[0] = -1;
                }
            }
        });

        //Q2RADIO GROUP
        ans2RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans2a.isChecked()){
                    final_result[1] = 1;
                }
                else if(ans2b.isChecked()){
                    final_result[1] = -1;
                }
            }
        });

        //Q3RADIO GROUP
        ans3RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans3a.isChecked()){
                    final_result[2] = 1;
                }
                else if(ans3b.isChecked()){
                    final_result[2] = -1;
                }
            }
        });

        //Q4RADIO GROUP
        ans4RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans4a.isChecked()){
                    final_result[3] = 1;
                }
                else if(ans4b.isChecked()){
                    final_result[3] = -1;
                }
            }
        });

        //Q5RADIO GROUP
        ans5RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans5a.isChecked()){
                    final_result[4] = 1;
                }
                else if(ans5b.isChecked()){
                    final_result[4] = -1;
                }
            }
        });

        //Q6RADIO GROUP
        ans6RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans6a.isChecked()){
                    final_result[5] = 1;
                }
                else if(ans6b.isChecked()){
                    final_result[5] = -1;
                }
            }
        });

        //Q7RADIO GROUP
        ans7RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans7a.isChecked()){
                    final_result[6] = 1;
                }
                else if(ans7b.isChecked()){
                    final_result[6] = -1;
                }
            }
        });

        //Q8RADIO GROUP
        ans8RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans8a.isChecked()){
                    final_result[7] = 1;
                }
                else if(ans8b.isChecked()){
                    final_result[7] = -1;
                }
            }
        });

        //Q9RADIO GROUP
        ans9RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans9a.isChecked()){
                    final_result[8] = 1;
                }
                else if(ans9b.isChecked()){
                    final_result[8] = -1;
                }
            }
        });

        //Q10RADIO GROUP
        ans10RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans10a.isChecked()){
                    final_result[9] = 1;
                }
                else if(ans10b.isChecked()){
                    final_result[9] = -1;
                }
            }
        });

        //Q11RADIO GROUP
        ans11RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans11a.isChecked()){
                    final_result[10] = 1;
                }
                else if(ans11b.isChecked()){
                    final_result[10] = -1;
                }
            }
        });

        //Q12RADIO GROUP
        ans12RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans12a.isChecked()){
                    final_result[11] = 1;
                }
                else if(ans12b.isChecked()){
                    final_result[11] = -1;
                }
            }
        });

        //Q13RADIO GROUP
        ans13RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans13a.isChecked()){
                    final_result[12] = 1;
                }
                else if(ans13b.isChecked()){
                    final_result[12] = -1;
                }
            }
        });

        //Q14RADIO GROUP
        ans14RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans14a.isChecked()){
                    final_result[13] = 1;
                }
                else if(ans14b.isChecked()){
                    final_result[13] = -1;
                }
            }
        });

        //Q15RADIO GROUP
        ans15RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans15a.isChecked()){
                    final_result[14] = 1;
                }
                else if(ans15b.isChecked()){
                    final_result[14] = -1;
                }
            }
        });

        //Q16RADIO GROUP
        ans16RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans16a.isChecked()){
                    final_result[15] = 1;
                }
                else if(ans16b.isChecked()){
                    final_result[15] = -1;
                }
            }
        });

        //Q17RADIO GROUP
        ans17RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans17a.isChecked()){
                    final_result[16] = 1;
                }
                else if(ans17b.isChecked()){
                    final_result[16] = -1;
                }
            }
        });

        //Q18RADIO GROUP
        ans18RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans18a.isChecked()){
                    final_result[17] = 1;
                }
                else if(ans18b.isChecked()){
                    final_result[17] = -1;
                }
            }
        });

        //Q19RADIO GROUP
        ans19RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans19a.isChecked()){
                    final_result[18] = 1;
                }
                else if(ans19b.isChecked()){
                    final_result[18] = -1;
                }
            }
        });

        //Q20RADIO GROUP
        ans20RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans20a.isChecked()){
                    final_result[19] = 1;
                }
                else if(ans20b.isChecked()){
                    final_result[19] = -1;
                }
            }
        });

        //Q21RADIO GROUP
        ans21RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans21a.isChecked()){
                    final_result[20] = 1;
                }
                else if(ans21b.isChecked()){
                    final_result[20] = -1;
                }
            }
        });

        //Q22RADIO GROUP
        ans22RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans22a.isChecked()){
                    final_result[21] = 1;
                }
                else if(ans22b.isChecked()){
                    final_result[21] = -1;
                }
            }
        });

        //Q23RADIO GROUP
        ans23RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans23a.isChecked()){
                    final_result[22] = 1;
                }
                else if(ans23b.isChecked()){
                    final_result[22] = -1;
                }
            }
        });

        //Q24RADIO GROUP
        ans24RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans24a.isChecked()){
                    final_result[23] = 1;
                }
                else if(ans24b.isChecked()){
                    final_result[23] = -1;
                }
            }
        });

        //Q25RADIO GROUP
        ans25RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans25a.isChecked()){
                    final_result[24] = 1;
                }
                else if(ans25b.isChecked()){
                    final_result[24] = -1;
                }
            }
        });

        //Q26RADIO GROUP
        ans26RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans26a.isChecked()){
                    final_result[25] = 1;
                }
                else if(ans26b.isChecked()){
                    final_result[25] = -1;
                }
            }
        });

        //Q27RADIO GROUP
        ans27RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans27a.isChecked()){
                    final_result[26] = 1;
                }
                else if(ans27b.isChecked()){
                    final_result[26] = -1;
                }
            }
        });

        //Q28RADIO GROUP
        ans28RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans28a.isChecked()){
                    final_result[27] = 1;
                }
                else if(ans28b.isChecked()){
                    final_result[27] = -1;
                }
            }
        });

        //Q29RADIO GROUP
        ans29RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans29a.isChecked()){
                    final_result[28] = 1;
                }
                else if(ans29b.isChecked()){
                    final_result[28] = -1;
                }
            }
        });

        //Q30RADIO GROUP
        ans30RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans30a.isChecked()){
                    final_result[29] = 1;
                }
                else if(ans30b.isChecked()){
                    final_result[29] = -1;
                }
            }
        });

        //Q31RADIO GROUP
        ans31RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans31a.isChecked()){
                    final_result[30] = 1;
                }
                else if(ans31b.isChecked()){
                    final_result[30] = -1;
                }
            }
        });

        //Q32RADIO GROUP
        ans32RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans32a.isChecked()){
                    final_result[31] = 1;
                }
                else if(ans32b.isChecked()){
                    final_result[31] = -1;
                }
            }
        });

        //Q33RADIO GROUP
        ans33RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans33a.isChecked()){
                    final_result[32] = 1;
                }
                else if(ans33b.isChecked()){
                    final_result[32] = -1;
                }
            }
        });

        //Q34RADIO GROUP
        ans34RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans34a.isChecked()){
                    final_result[33] = 1;
                }
                else if(ans34b.isChecked()){
                    final_result[33] = -1;
                }
            }
        });

        //Q35RADIO GROUP
        ans35RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans35a.isChecked()){
                    final_result[34] = 1;
                }
                else if(ans35b.isChecked()){
                    final_result[34] = -1;
                }
            }
        });

        //Q36RADIO GROUP
        ans36RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans36a.isChecked()){
                    final_result[35] = 1;
                }
                else if(ans36b.isChecked()){
                    final_result[35] = -1;
                }
            }
        });

        //Q37RADIO GROUP
        ans37RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans37a.isChecked()){
                    final_result[36] = 1;
                }
                else if(ans37b.isChecked()){
                    final_result[36] = -1;
                }
            }
        });

        //Q38RADIO GROUP
        ans38RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans38a.isChecked()){
                    final_result[37] = 1;
                }
                else if(ans38b.isChecked()){
                    final_result[37] = -1;
                }
            }
        });

        //Q39RADIO GROUP
        ans39RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans39a.isChecked()){
                    final_result[38] = 1;
                }
                else if(ans39b.isChecked()){
                    final_result[38] = -1;
                }
            }
        });

        //Q40RADIO GROUP
        ans40RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans40a.isChecked()){
                    final_result[39] = 1;
                }
                else if(ans40b.isChecked()){
                    final_result[39] = -1;
                }
            }
        });

        //Q41RADIO GROUP
        ans41RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans41a.isChecked()){
                    final_result[40] = 1;
                }
                else if(ans41b.isChecked()){
                    final_result[40] = -1;
                }
            }
        });

        //Q42RADIO GROUP
        ans42RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans42a.isChecked()){
                    final_result[41] = 1;
                }
                else if(ans42b.isChecked()){
                    final_result[41] = -1;
                }
            }
        });

        //Q43RADIO GROUP
        ans43RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans43a.isChecked()){
                    final_result[42] = 1;
                }
                else if(ans43b.isChecked()){
                    final_result[42] = -1;
                }
            }
        });

        //Q44RADIO GROUP
        ans44RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(ans44a.isChecked()){
                    final_result[43] = 1;
                }
                else if(ans44b.isChecked()){
                    final_result[43] = -1;
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //START for simulation, delete later
                for(int i =0; i<44; i++){
                    final_result[i] = 1;
                }
                //END for simulation

                if(Arrays.asList(final_result).contains(0)){
                    Toast.makeText(QuestionnaireScrollingActivity.this, "All questions are mandatory.", Toast.LENGTH_SHORT).show();
                }
                else{
                    showProgressDialog();
                    //CALL FUNCTION TO COMPUTE LEARNING STYLE
                    computeLearningStyle();
                    Intent mNextIntent = new Intent(getApplicationContext(), DisplayLearningStyleActivity.class);
                    mNextIntent.putStringArrayListExtra("UserLearningStyle", user_learning_style);
                    hideProgressDialog();
                    startActivity(mNextIntent);
                    //hideProgressDialog();
                }
            }
        });

    }

    public void computeLearningStyle(){

        int active_reflective = 0;
        int sensing_intuitive = 0;
        int visual_verbal = 0;
        int sequential_global = 0;

        //COMPUTATION OF LEARNING PATTERN
        for(int i = 0; i < 44; i+=4){
            active_reflective += final_result[i];
        }
        for(int i = 1; i < 44; i+=4){
            sensing_intuitive += final_result[i];
        }
        for(int i = 2; i < 44; i+=4){
            visual_verbal += final_result[i];
        }
        for(int i = 3; i < 44; i+=4){
            sequential_global += final_result[i];
        }

        //CHECK FOR ACTIVE-REFLECTIVE
        if(active_reflective > 3){
            user_learning_style.add(0, "Active");
        }
        else if(active_reflective < -3){
            user_learning_style.add(0, "Reflective");
        }
        else if(active_reflective <= 3 && active_reflective >=-3){
            user_learning_style.add(0, "Active_Reflective_Balanced");
        }

        //CHECK FOR SENSING-INTUITIVE
        if(sensing_intuitive > 3){
            user_learning_style.add(1, "Sensing");
        }
        else if(sensing_intuitive < -3){
            user_learning_style.add(1, "Intuitive");
        }
        else if(sensing_intuitive <= 3 && sensing_intuitive >= -3){
            user_learning_style.add(1, "Sensing_Intuitive_Balanced");
        }

        //CHECK FOR VISUAL-VERBAL
        if(visual_verbal > 3){
            user_learning_style.add(2, "Visual");
        }
        else if(visual_verbal < -3){
            user_learning_style.add(2, "Verbal");
        }
        else if(visual_verbal <=3 && visual_verbal >= -3){
            user_learning_style.add(2, "Visual_Verbal_Balanced");
        }

        //CHECK FOR SEQUENTIAL-GLOBAL
        if(sequential_global > 3){
            user_learning_style.add(3, "Sequential");
        }
        else if(sequential_global < -3){
            user_learning_style.add(3, "Global");
        }
        else if(sequential_global <=3 && sequential_global >= -3){
            user_learning_style.add("Sequential_Global_Balanced");
        }
    }
}
