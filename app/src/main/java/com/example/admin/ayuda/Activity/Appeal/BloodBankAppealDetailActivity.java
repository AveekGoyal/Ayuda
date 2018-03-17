package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Data.AppealAdapters.BloodBankAppealAdapter;
import com.example.admin.ayuda.Model.BloodBankAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
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
    private TextView bloodBankPatientName;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private BloodBankAppealAdapter bloodBankAppealAdapter;
    private List<BloodBankAppeal> bloodBankAppealList;

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
        bloodBankPatientName = findViewById(R.id.BloodPatientNamePlainText);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("BloodBankAppeals");
        mDatabaseReference.keepSynced(true);

        String imageUrl = getIntent().getStringExtra("appealPic");
        Picasso.with(getApplicationContext()).load(imageUrl).into(bloodPicProofImageView);
        bloodBankPatientName.setText(String.format("Patient Name : %s", getIntent().getStringExtra("patientName")));
        bloodBankFamilyMemberNamePlainText.setText(String.format("Family Member : %s", getIntent().getStringExtra("familyMemberName")));
        bloodBankFamilyMemberContactNoPlainText.setText(String.format("Contact Number: %s", getIntent().getStringExtra("familyMemberContact")));
        bloodBankFamilyMemberAltContactNoPlainText.setText(String.format("Alternate Number: %s", getIntent().getStringExtra("familyMemberAltContact")));
        bloodBankHospitalNamePlainText.setText(String.format("Hospital Name: %s", getIntent().getStringExtra("hospitalName")));
        bloodBankHospitalContactNoPlainText.setText(String.format("Hospital Contact Number : %s", getIntent().getStringExtra("hospitalContactNo")));
        bloodBankPlateletsCountPlainText.setText(String.format("Platelets Count: %s", getIntent().getStringExtra("plateletsCount")));
        bloodBankAmountNeededPlainText.setText(String.format("Amount Needed : %s", getIntent().getStringExtra("bloodAmountNeeded")));
        bloodBankHospitalAddressPlainText.setText(String.format("Hospital Address : %s", getIntent().getStringExtra("hospitalAddress")));


        bloodBankRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BloodBankAppealDetailActivity.this, MainNavigationActivity.class));
            }
        });


    }
}
