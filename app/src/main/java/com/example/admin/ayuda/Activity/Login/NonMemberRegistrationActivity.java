package com.example.admin.ayuda.Activity.Login;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.admin.ayuda.R;

public class NonMemberRegistrationActivity extends AppCompatActivity {

    private ImageButton nonMemRegDpImageButton;
    private EditText nonMemRegFirstNameTextBox;
    private EditText nonMemRegLastNameTextBox;
    private RadioButton nonMemRegRadioMale;
    private EditText nonMemRegContactNoTextBox;
    private  RadioButton nonMemRegRadioFemale;
    private  EditText nonMemRegEmailTextBox;
    private  EditText nonMemRegPasswordTextBox;
    private  EditText nonMemRegConfirmPassTextBox;
    private Button nonMemberSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_member_registration_activity);

        //Variable used in this java file
        nonMemRegDpImageButton = findViewById(R.id.NonMemRegDpImageButton);
        nonMemRegFirstNameTextBox = findViewById(R.id.NonMemRegFirstNameTextBox);
        nonMemRegLastNameTextBox = findViewById(R.id.NonMemRegLastNameTextBox);
        nonMemRegRadioMale = findViewById(R.id.NonMemRegRadioMale);
        nonMemRegRadioFemale = findViewById(R.id.NonMemRegRadioFemale);
        nonMemRegContactNoTextBox = findViewById(R.id.NonMemRegContactNoTextBox);
        nonMemRegEmailTextBox = findViewById(R.id.NonMemRegEmailTextBox);
        nonMemRegPasswordTextBox = findViewById(R.id.NonMemRegPasswordTextBox);
        nonMemRegConfirmPassTextBox = findViewById(R.id.NonMemRegConfirmPassTextBox);
        nonMemberSubmitButton = findViewById(R.id.NonMemberSubmitButton);


    }
}
