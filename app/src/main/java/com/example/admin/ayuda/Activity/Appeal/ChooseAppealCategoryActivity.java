package com.example.admin.ayuda.Activity.Appeal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.admin.ayuda.R;

public class ChooseAppealCategoryActivity extends AppCompatActivity{

    private RadioButton  chooseAppealRadioBloodBank;
    private RadioButton chooseAppealRadioDisaster;
    private  RadioButton chooseAppealRadioChild;
    private RadioButton chooseAppealRadioOld;
    private RadioButton chooseAppealRadioCommunity;
    private Button chooseAppealRadioSubmitButton;

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

    }
}
