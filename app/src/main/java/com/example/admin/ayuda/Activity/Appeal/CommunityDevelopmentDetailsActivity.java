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

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Data.AppealAdapters.CommunityDevelopmentAppealAdapter;
import com.example.admin.ayuda.Model.CommunityAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String type=" ";
    String adminEmail = " ";
    String adminOrgName = " ";
    String isAppealAccepted = "No";
    String adminContactNo=" ";

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
        if (getIntent().getStringExtra("HealthIssues").equals("Yes")) {
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
        communityDevDescPlainText.setText(String.format("Description: %s" , getIntent().getStringExtra("appealTitle")));
        communityDevContactNoPlainText.setText(String.format("Contact Number : %s", getIntent().getStringExtra("ContactNo")));

        communityDevRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CommunityDevelopmentDetailsActivity.this, MainNavigationActivity.class));
            }
        });

        final String userId = mUser.getUid();
        DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child("NgoAdmin").child(userId);
        getType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                type =  dataSnapshot.child("type").getValue(String.class);
                adminEmail = dataSnapshot.child("email").getValue(String.class);
                adminOrgName = dataSnapshot.child("orgName").getValue(String.class);
                adminContactNo = dataSnapshot.child("mobileNumber").getValue(String.class);
                if(type == null)
                {
                    communityDevAcceptButton.setEnabled(false);
                    communityDevRejectButton.setEnabled(false);
                }
                else if(type.equals("NgoAdmin"))
                {
                    if (getIntent().getStringExtra("isAccepted").equals("Yes"))
                    {
                        communityDevAcceptButton.setEnabled(false);
                        communityDevRejectButton.setEnabled(false);
                    }
                    else
                    {
                        communityDevRejectButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(CommunityDevelopmentDetailsActivity.this, MainNavigationActivity.class));
                            }
                        });

                        communityDevAcceptButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new MaterialDialog.Builder(CommunityDevelopmentDetailsActivity.this)
                                        .title("Accepting Appeal")
                                        .content("Please Wait")
                                        .progress(true, 0)
                                        .show();
                                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Ngo_Appeals");
                                DatabaseReference appealAcceptByNgo = mDatabaseReference.push();
                                Map<String, String> dataToSave = new HashMap<>();
                                dataToSave.put("adminOrgName", adminOrgName);
                                dataToSave.put("adminEmail", adminEmail);
                                dataToSave.put("adminUserId", userId);
                                dataToSave.put("adminContactNo", adminContactNo);
                                dataToSave.put("appealTimestamp",getIntent().getStringExtra("timestamp") );
                                dataToSave.put("appealImageDp", getIntent().getStringExtra("appealPic") );
                                dataToSave.put("appealName", getIntent().getStringExtra("appealTitle"));
                                appealAcceptByNgo.setValue(dataToSave);

                                final DatabaseReference isAccepted = FirebaseDatabase.getInstance().getReference().child("CommunityDevelopmentAppeals");
                                isAccepted.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot item : dataSnapshot.getChildren()) {
                                            String timestamp = item.child("timestamp").getValue(String.class);
                                            if (timestamp.equals(getIntent().getStringExtra("timestamp"))) {
                                                item.getRef().child("isAccepted").setValue("Yes");

                                            }

                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                startActivity(new Intent(CommunityDevelopmentDetailsActivity.this, MainNavigationActivity.class));
                                finish();

                            }
                        });

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
