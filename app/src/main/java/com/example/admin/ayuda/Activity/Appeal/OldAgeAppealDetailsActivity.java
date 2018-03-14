package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.R;

import org.w3c.dom.Text;

public class OldAgeAppealDetailsActivity extends AppCompatActivity{

    private ImageView oldAgeHomePicProofImageView;
    private TextView oldAgeHomeDescriptionPlainText;
    private TextView oldAgeHomeAddressPlainText;
    private CheckBox oldAgeHomeFinancialNeeds;
    private CheckBox oldAgeHomeMedicalNeeds;
    private CheckBox oldAgeHomeLivelihoodNeeds;
    private Button oldAgeHomeAcceptButton;
    private Button oldAgeHomeRejectButton;

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

    }
}
