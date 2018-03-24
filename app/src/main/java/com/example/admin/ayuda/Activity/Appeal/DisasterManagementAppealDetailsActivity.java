package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Data.AppealAdapters.ChildLabourAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.CommunityDevelopmentAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.DisasterManagementAppealAdapter;
import com.example.admin.ayuda.Model.ChildAbuseAppeals;
import com.example.admin.ayuda.Model.CommunityAppeal;
import com.example.admin.ayuda.Model.DisasterAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class DisasterManagementAppealDetailsActivity extends AppCompatActivity {

    private ImageView disasterMgmtPicProofImageView;
    private TextView disasterMgmtTypeOfDisasterPlainText;
    private TextView disasterMgmtDescPlainText;
    private CheckBox disasterMgmtNeedWater;
    private CheckBox disasterMgmtNeedFood;
    private CheckBox disasterMgmtNeedShelter;
    private CheckBox disasterMgmtNeedMedical;
    private CheckBox disasterMgmtNeedClothing;
    private CheckBox disasterMgmtNeedRehab;
    private TextView disasterMgmtTypeOfDisaster;
    private TextView disasterMgmtContactNoPlainText;
    private TextView disasterMgmtAltContactNoPlainText;
    private Button disasterMgmtSafetyButton;
    private Button disasterMgmtAcceptButton;
    private Button disasterMgmtRejectButton;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DisasterManagementAppealAdapter disasterManagementAppealAdapter;
    private List<DisasterAppeal> disasterAppealList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disaster_management_appeal_details);

        disasterMgmtPicProofImageView = findViewById(R.id.DisasterMgmtPicProofImageView);
        disasterMgmtTypeOfDisasterPlainText = findViewById(R.id.DisasterMgmtTypeOfDisasterPlainText);
        disasterMgmtDescPlainText = findViewById(R.id.DisasterMgmtDescPlainText);
        disasterMgmtNeedFood = findViewById(R.id.DisasterMgmtNeedFood);
        disasterMgmtNeedWater = findViewById(R.id.DisasterMgmtNeedWater);
        disasterMgmtNeedShelter = findViewById(R.id.DisasterMgmtNeedShelter);
        disasterMgmtNeedClothing = findViewById(R.id.DisasterMgmtNeedClothing);
        disasterMgmtNeedMedical = findViewById(R.id.DisasterMgmtNeedMedical);
        disasterMgmtNeedRehab = findViewById(R.id.DisasterMgmtNeedRehab);
        disasterMgmtTypeOfDisaster = findViewById(R.id.AddDisasterAltContactNoTextBox);
        disasterMgmtContactNoPlainText = findViewById(R.id.DisasterMgmtContactNoPlainText);
        disasterMgmtAltContactNoPlainText = findViewById(R.id.DisasterMgmtAltContactNoPlainText);
        disasterMgmtSafetyButton = findViewById(R.id.DisasterMgmtSafetyButton);
        disasterMgmtAcceptButton = findViewById(R.id.DisasterMgmtAcceptButton);
        disasterMgmtRejectButton = findViewById(R.id.DisasterMgmtRejectButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("DisasterAppeal");
        mDatabaseReference.keepSynced(true);

        String imageUrl = getIntent().getStringExtra("appealPic");
        Picasso.with(getApplicationContext()).load(imageUrl).into(disasterMgmtPicProofImageView);
        disasterMgmtDescPlainText.setText(String.format("Description: %s " , getIntent().getStringExtra("appealTitle")));
        if (getIntent().getStringExtra("needFood").equals("Yes"))
        {
            disasterMgmtNeedFood.setChecked(true);
            disasterMgmtNeedFood.setEnabled(false);
        }
        else {
            disasterMgmtNeedFood.setEnabled(false);
        }
        if (getIntent().getStringExtra("needWater").equals("Yes"))
        {
            disasterMgmtNeedWater.setChecked(true);
            disasterMgmtNeedWater.setEnabled(false);
        }
        else {
            disasterMgmtNeedWater.setEnabled(false);
        }
        if (getIntent().getStringExtra("needShelter").equals("Yes"))
        {
            disasterMgmtNeedShelter.setChecked(true);
            disasterMgmtNeedShelter.setEnabled(false);
        }
        else {
            disasterMgmtNeedShelter.setEnabled(false);
        }
                if (getIntent().getStringExtra("needMedicalFacilities").equals("Yes"))
        {
            disasterMgmtNeedMedical.setChecked(true);
            disasterMgmtNeedMedical.setEnabled(false);
        }
        else {
            disasterMgmtNeedMedical.setEnabled(false);
        }
        if (getIntent().getStringExtra("needClothing").equals("Yes"))
        {
            disasterMgmtNeedClothing.setChecked(true);
            disasterMgmtNeedClothing.setEnabled(false);
        }
        else {
            disasterMgmtNeedClothing.setEnabled(false);
        }
        if(getIntent().getStringExtra("needRehabilitation").equals("Yes"))
        {
            disasterMgmtNeedRehab.setChecked(true);
            disasterMgmtNeedRehab.setEnabled(false);
        }
        else
        {
            disasterMgmtNeedRehab.setEnabled(false);
        }
        disasterMgmtTypeOfDisaster.setText(String.format(" : %s", getIntent().getStringExtra("typeOfDisaster")));
        disasterMgmtContactNoPlainText.setText(String.format("Contact Number : %s", getIntent().getStringExtra("contactNo")));
        disasterMgmtAltContactNoPlainText.setText(String.format("Alternate Number: %s", getIntent().getStringExtra("altContactNo")));


        disasterMgmtRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisasterManagementAppealDetailsActivity.this, MainNavigationActivity.class));
            }
        });


    }
}

