package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.admin.ayuda.R;

import javax.microedition.khronos.egl.EGLDisplay;

public class AddBloodBankAppealActivity extends AppCompatActivity {

    private ImageButton addBloodPicProofImageButton;
    private EditText addBloodPatientNameTextBox;
    private EditText addBloodFamilyMemberNameTextBox;
    private EditText addBloodFamilyMemberContactNoTextBox;
    private EditText addBloodFamilyMemberAltContactNoTextBox;
    private  EditText addBloodHospitalNameTextBox;
    private EditText addBloodHospitalContactNoTextBox;
    private EditText addBloodHospitalAddressTextBox;
    private EditText addBloodPlateletsCountTextBox;
    private EditText addBloodAmountNeededTextBox;
    private Button  addBloodSubmitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_blood_bank_appeal_activity);

        addBloodPicProofImageButton = findViewById(R.id.AddBloodPicProofImageButton);
        addBloodPatientNameTextBox = findViewById(R.id.AddBloodPatientNameTextBox);
        addBloodFamilyMemberNameTextBox =findViewById(R.id.AddBloodFamilyMemberNameTextBox);
        addBloodFamilyMemberContactNoTextBox = findViewById(R.id.AddBloodFamilyMemberContactNoTextBox);
        addBloodFamilyMemberAltContactNoTextBox =findViewById(R.id.AddBloodFamilyMemberAltContactNoTextBox);
        addBloodHospitalNameTextBox = findViewById(R.id.AddBloodHospitalNameTextBox);
        addBloodHospitalContactNoTextBox = findViewById(R.id.AddBloodHospitalContactNoTextBox);
        addBloodHospitalAddressTextBox = findViewById(R.id.AddBloodHospitalAddressTextBox);
        addBloodPlateletsCountTextBox = findViewById(R.id.AddBloodPlateletsCountTextBox);
        addBloodAmountNeededTextBox = findViewById(R.id.AddBloodAmountNeededTextBox);
        addBloodSubmitButton = findViewById(R.id.AddBloodSubmitButton);
        

    }
}
