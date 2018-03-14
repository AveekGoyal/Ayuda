package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.R;

import org.w3c.dom.Text;

import java.util.concurrent.TimeoutException;

public class BloodBankAppealDetailActivity extends AppCompatActivity {

    private ImageView bloodPicProofImageView;
    private TextView bloodBankFamilyMemberNamePlainText;
    private TextView bloodBankFamilyMemberContactNoPlainText;
    private TextView bloodBankFamilyMemberAltContactNoPlainText;
    private TextView bloodBankHospitalNamePlainText;
    private TextView bloodBankHospitalContactNoPlainText;
    private TextView bloodBankHospitalAddressPlainText;
    private TextView bloodBankPlateletsCountPlainText;
    private TextView bloodBankAmountNeededPlainText;
    private Button bloodBankAcceptButton;
    private Button bloodBankRejectButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_bank_appeal_detail);

        bloodPicProofImageView = findViewById(R.id.BloodPicProofImageView);
        bloodBankFamilyMemberNamePlainText = findViewById(R.id.BloodBankFamilyMemberNamePlainText);
        bloodBankFamilyMemberContactNoPlainText = findViewById(R.id.BloodBankFamilyMemberContactNoPlainText);
        bloodBankFamilyMemberAltContactNoPlainText = findViewById(R.id.BloodBankFamilyMemberAltContactNoPlainText);
        bloodBankHospitalNamePlainText = findViewById(R.id.BloodBankHospitalNamePlainText);
        bloodBankHospitalContactNoPlainText = findViewById(R.id.BloodBankHospitalContactNoPlainText);
        bloodBankHospitalAddressPlainText = findViewById(R.id.BloodBankHospitalAddressPlainText);
        bloodBankPlateletsCountPlainText = findViewById(R.id.BloodBankPlateletsCountPlainText);
        bloodBankAmountNeededPlainText = findViewById(R.id.BloodBankAmountNeededPlainText);
        bloodBankAcceptButton = findViewById(R.id.BloodBankAcceptButton);
        bloodBankRejectButton = findViewById(R.id.BloodBankRejectButton);

    }
}
