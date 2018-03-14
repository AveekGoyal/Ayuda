package com.example.admin.ayuda.Activity.Login;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.admin.ayuda.R;

public class MemberRegistrationActivity extends AppCompatActivity{

    private ImageButton memRegDpImageButton;
    private EditText memRegFirstNameTextBox;
    private EditText memRegLastNameTextBox;
    private RadioButton memRegRadioMale;
    private RadioButton memRegRadioFemale;
    private  EditText memRegEmailTextBox;
    private  EditText memRegPasswordTextBox;
    private  EditText memRegConfirmPassTextBox;
    private  EditText memRegMobileNoTextBox;
    private Spinner memRegOrgNameSpinner;
    private EditText memRegMemberIdTextBox;
    private EditText memRegAddressTextBox;
    private  Spinner memRegCitySpinner;
    private Spinner memRegStateSpinner;
    private EditText memRegPincodeTextBox;
    private Button memRegSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_registration_activity);

        memRegDpImageButton = findViewById(R.id.MemRegDpImageButton);
        memRegFirstNameTextBox= findViewById(R.id.MemRegFirstNameTextBox);
        memRegLastNameTextBox = findViewById(R.id.MemRegLastNameTextBox);
        memRegRadioMale = findViewById(R.id.MemRegRadioMale);
        memRegRadioFemale = findViewById(R.id.MemRegRadioFemale);
        memRegEmailTextBox = findViewById(R.id.MemRegEmailTextBox);
        memRegPasswordTextBox =findViewById(R.id.MemRegPasswordTextBox);
        memRegConfirmPassTextBox = findViewById(R.id.MemRegConfirmPassTextBox);
        memRegMobileNoTextBox =findViewById(R.id.MemRegMobileNoTextBox);
        memRegOrgNameSpinner = findViewById(R.id.MemRegOrgNameSpinner);
        memRegMemberIdTextBox =findViewById(R.id.MemRegMemberIdTextBox);
        memRegAddressTextBox = findViewById(R.id.MemRegAddressTextBox);
        memRegCitySpinner = findViewById(R.id.MemRegCitySpinner);
        memRegStateSpinner = findViewById(R.id.MemRegStateSpinner);
        memRegSubmitButton = findViewById(R.id.MemRegSubmitButton);
        memRegPincodeTextBox = findViewById(R.id.MemRegPincodeTextBox);

    }
}
