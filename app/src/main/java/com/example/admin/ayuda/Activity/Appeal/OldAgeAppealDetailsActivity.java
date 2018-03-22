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
import com.example.admin.ayuda.Data.AppealAdapters.OldAgeHomeAppealAdapter;
import com.example.admin.ayuda.Model.OldAgeHomeAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class OldAgeAppealDetailsActivity extends AppCompatActivity{

    private ImageView oldAgeHomePicProofImageView;
    private TextView oldAgeHomeDescriptionPlainText;
    private TextView oldAgeHomeAddressPlainText;
    private CheckBox oldAgeHomeFinancialNeeds;
    private CheckBox oldAgeHomeMedicalNeeds;
    private CheckBox oldAgeHomeLivelihoodNeeds;
    private Button oldAgeHomeAcceptButton;
    private Button oldAgeHomeRejectButton;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private OldAgeHomeAppealAdapter oldAgeHomeAppealAdapter;
    private List<OldAgeHomeAppeal> oldAgeHomeAppealList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old_age_appeal_details);

        oldAgeHomePicProofImageView = findViewById(R.id.OldAgeHomePicProofImageView);
        oldAgeHomeDescriptionPlainText = findViewById(R.id.OldAgeHomeDescriptionPlainText);
        oldAgeHomeAddressPlainText = findViewById(R.id.OldAgeHomeAddressPlainText);
        oldAgeHomeFinancialNeeds = findViewById(R.id.OldAgeHomeFinancialNeeds);
        oldAgeHomeMedicalNeeds = findViewById(R.id.OldAgeHomeMedicalNeeds);
        oldAgeHomeLivelihoodNeeds = findViewById(R.id.OldAgeHomeLivelihoodNeeds);
        oldAgeHomeAcceptButton = findViewById(R.id.OldAgeHomeAcceptButton);
        oldAgeHomeRejectButton = findViewById(R.id.OldAgeHomeRejectButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("OldAgeHomeAppeal");
        mDatabaseReference.keepSynced(true);

        String imageUrl = getIntent().getStringExtra("appealPic");
        Picasso.with(getApplicationContext()).load(imageUrl).into(oldAgeHomePicProofImageView);

        if (getIntent().getStringExtra("financialNeeds").equals("Yes"))
        {
            oldAgeHomeFinancialNeeds.setChecked(true);
            oldAgeHomeFinancialNeeds.setEnabled(false);
        }
        else
        {
            oldAgeHomeFinancialNeeds.setEnabled(false);
        }
        if (getIntent().getStringExtra("medicalNeeds").equals("Yes"))
        {
            oldAgeHomeMedicalNeeds.setChecked(true);
            oldAgeHomeMedicalNeeds.setEnabled(false);
        }
        else
        {
            oldAgeHomeMedicalNeeds.setEnabled(false);
        }
        if (getIntent().getStringExtra("livelihoodNeeds").equals("Yes"))
        {
            oldAgeHomeLivelihoodNeeds.setChecked(true);
            oldAgeHomeLivelihoodNeeds.setEnabled(false);
        }
        else
        {
            oldAgeHomeLivelihoodNeeds.setEnabled(false);
        }
        oldAgeHomeDescriptionPlainText.setText(String.format("Title: %s" , getIntent().getStringExtra("appealTitle")));

        oldAgeHomeRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OldAgeAppealDetailsActivity.this, MainNavigationActivity.class));
            }
        });


    }
}
