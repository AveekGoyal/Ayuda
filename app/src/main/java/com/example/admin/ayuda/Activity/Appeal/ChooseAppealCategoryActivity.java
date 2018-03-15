package com.example.admin.ayuda.Activity.Appeal;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseAppealCategoryActivity extends AppCompatActivity{

    private RadioButton  chooseAppealRadioBloodBank;
    private RadioButton chooseAppealRadioDisaster;
    private  RadioButton chooseAppealRadioChild;
    private RadioButton chooseAppealRadioOld;
    private RadioButton chooseAppealRadioCommunity;
    private Button chooseAppealRadioSubmitButton;
    private RadioGroup chooseAppealCategoryRadioGroup;
    private Button chooseButton;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_appeal_category_activity);

        chooseAppealRadioBloodBank = findViewById(R.id.ChooseAppealRadioBloodBank);
        chooseAppealRadioDisaster = findViewById(R.id.ChooseAppealRadioDisaster);
        chooseAppealRadioChild = findViewById(R.id.ChooseAppealRadioChild);
        chooseAppealRadioOld = findViewById(R.id.ChooseAppealRadioOld);
        chooseAppealRadioCommunity = findViewById(R.id.ChooseAppealRadioCommunity);
        chooseAppealRadioSubmitButton= findViewById(R.id.ChooseAppealRadioSubmitButton);
        chooseAppealCategoryRadioGroup = findViewById(R.id.chooseAppealCategoryRadioGroup);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        chooseAppealCategoryRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.ChooseAppealRadioBloodBank:
                        chooseAppealRadioSubmitButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mAuth != null && mUser != null) {
                                    startActivity(new Intent(ChooseAppealCategoryActivity.this, AddBloodBankAppealActivity.class));
                                    finish();
                                }

                            }
                        });

                        break;
                    case R.id.ChooseAppealRadioDisaster:
                        chooseAppealRadioSubmitButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mAuth != null && mUser != null) {
                                    startActivity(new Intent(ChooseAppealCategoryActivity.this, AddDisasterManagementAppeal.class));
                                    finish();
                                }

                            }
                        });
                        break;


                }
            }
        });


    }
}
