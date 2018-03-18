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
import com.example.admin.ayuda.Data.AppealAdapters.CommunityDevelopmentAppealAdapter;
import com.example.admin.ayuda.Model.CommunityAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

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
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;
    private CommunityDevelopmentAppealAdapter communityDevelopmentAppealAdapter;
    private List<CommunityAppeal> communityAppealList;

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

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("DisasterAppeal");
        mDatabaseReference.keepSynced(true);


        String imageUrl = getIntent().getStringExtra("appealPic");
        Picasso.with(getApplicationContext()).load(imageUrl).into(communityDevPicProofImageView);

        if (getIntent().getStringExtra("Cleaning").equals("Yes")) {
            communityDevCleaningCheckBox.setChecked(true);
            communityDevCleaningCheckBox.setEnabled(false);
        }
        else
        {
            communityDevCleaningCheckBox.setEnabled(false);
        }

        if (getIntent().getStringExtra("Hunger").equals("Yes")) {
            communityDevHungerCheckBox.setChecked(true);
            communityDevHungerCheckBox.setEnabled(false);
        }
        else
        {
            communityDevHungerCheckBox.setEnabled(false);
        }
        if (getIntent().getStringExtra("Health Issues").equals("Yes")) {
            communityDevHealthIssuesCheckBox.setChecked(true);
            communityDevHealthIssuesCheckBox.setEnabled(false);
        }
        else
        {
            communityDevHealthIssuesCheckBox.setEnabled(false);
        }
        if (getIntent().getStringExtra("Poverty").equals("Yes")) {
            communityDevPovertyCheckBox.setChecked(true);
            communityDevPovertyCheckBox.setEnabled(false);
        }
        else
        {
            communityDevPovertyCheckBox.setEnabled(false);
        }
        communityDevDescPlainText.setText(String.format("Description: %s" , getIntent().getStringExtra("description")));
        communityDevContactNoPlainText.setText(String.format("Contact Number : %s", getIntent().getStringExtra("ContactNo")));

        communityDevRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CommunityDevelopmentDetailsActivity.this, MainNavigationActivity.class));
            }
        });


    }
}
