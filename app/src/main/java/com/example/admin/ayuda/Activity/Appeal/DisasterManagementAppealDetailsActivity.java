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

public class DisasterManagementAppealDetailsActivity extends AppCompatActivity {

    private ImageView disasterMgmtPicProofImageView;
    private TextView disasterMgmtTypeOfDisasterPlainText;
    private TextView disasterMgmtDescPlainText;
    private CheckBox disasterMgmtNeedWater;
    private CheckBox disasterMgmtNeedFood;
    private CheckBox disasterMgmtNeedShelter;
    private CheckBox disasterMgmtNeedMedical;
    private CheckBox disasterMgmtNeedClothing;
    private TextView disasterMgmtNeedRehab;
    private TextView disasterMgmtContactNoPlainText;
    private Button disasterMgmtSafetyButton;
    private Button disasterMgmtAcceptButton;
    private Button disasterMgmtRejectButton;

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
        disasterMgmtNeedShelter = findViewById(R.id.DisasterMgmtNeedShelter);
        disasterMgmtNeedClothing = findViewById(R.id.DisasterMgmtNeedClothing);
        disasterMgmtNeedMedical = findViewById(R.id.DisasterMgmtNeedMedical);
        disasterMgmtNeedRehab = findViewById(R.id.DisasterMgmtNeedRehab);
        disasterMgmtContactNoPlainText = findViewById(R.id.DisasterMgmtContactNoPlainText);
        disasterMgmtSafetyButton = findViewById(R.id.DisasterMgmtSafetyButton);
        disasterMgmtAcceptButton = findViewById(R.id.DisasterMgmtAcceptButton);
        disasterMgmtRejectButton = findViewById(R.id.DisasterMgmtRejectButton);

    }
}
