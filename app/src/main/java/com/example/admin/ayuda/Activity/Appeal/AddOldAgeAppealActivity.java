package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.admin.ayuda.R;

public class AddOldAgeAppealActivity extends AppCompatActivity {

    private ImageButton addOldAgeHomePicProofImageButton;
    private EditText addOldAgeHomeDescriptionTextBox;
    private EditText addOldAgeHomeAddressTextBox;
    private CheckBox addOldAgeHomeFinancialNeeds;
    private  CheckBox addOldAgeHomeMedicalNeeds;
    private  CheckBox addOldAgeHomeLivelihoodNeeds;
    private Button addOldAgeHomeSubmitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_old_age_appeal_activity);

        addOldAgeHomePicProofImageButton = findViewById(R.id.AddOldAgeHomePicProofImageButton);
        addOldAgeHomeDescriptionTextBox = findViewById(R.id.AddOldAgeHomeDescriptionTextBox);
        addOldAgeHomeAddressTextBox = findViewById(R.id.AddOldAgeHomeAddressTextBox);
        addOldAgeHomeFinancialNeeds = findViewById(R.id.AddOldAgeHomeFinancialNeeds);
        addOldAgeHomeMedicalNeeds = findViewById(R.id.AddOldAgeHomeMedicalNeeds);
        addOldAgeHomeLivelihoodNeeds = findViewById(R.id.AddOldAgeHomeLivelihoodNeeds);
        addOldAgeHomeSubmitButton = findViewById(R.id.AddOldAgeHomeSubmitButton);

    }
}
