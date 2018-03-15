package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.R;

public class CommunityDevelopmentDetailsActivity extends AppCompatActivity {

    private ImageView communityDevPicProofImageView;
    private CheckBox communityDevCleaningCheckBox;
    private CheckBox communityDevHungerCheckBox;
    private  CheckBox communityDevHealthIssuesCheckBox;
    private  CheckBox communityDevPovertyCheckBox;
    private TextView communityDevDescPlainText;
    private Button communityDevPreviousComplaintsDownloadButton;
    private TextView communityDevContactNoPlainText;
    private Button communityDevAcceptButton;
    private Button communityDevRejectButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_development_details);

        communityDevPicProofImageView = findViewById(R.id.CommunityDevPicProofImageView);
        communityDevCleaningCheckBox = findViewById(R.id.CommunityDevCleaningCheckBox);
        communityDevHungerCheckBox = findViewById(R.id.CommunityDevHungerCheckBox);
        communityDevHealthIssuesCheckBox = findViewById(R.id.CommunityDevHealthIssuesCheckBox);
        communityDevPovertyCheckBox = findViewById(R.id.CommunityDevPovertyCheckBox);
        communityDevDescPlainText =findViewById(R.id.CommunityDevDescPlainText);
        communityDevPreviousComplaintsDownloadButton = findViewById(R.id.CommunityDevPreviousComplaintsDownloadButton);
        communityDevContactNoPlainText = findViewById(R.id.CommunityDevContactNoPlainText);
        communityDevAcceptButton = findViewById(R.id.CommunityDevAcceptButton);
        communityDevRejectButton = findViewById(R.id.CommunityDevRejectButton);


    }
}
