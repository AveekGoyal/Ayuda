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
import com.example.admin.ayuda.Data.AppealAdapters.OldAgeHomeAppealAdapter;
import com.example.admin.ayuda.Model.OldAgeHomeAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String type=" ";
    String adminEmail = " ";
    String adminOrgName = " ";
    String isAppealAccepted = "No";
    String adminContactNo=" ";

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
                    oldAgeHomeAcceptButton.setEnabled(false);
                    oldAgeHomeRejectButton.setEnabled(false);
                }
                else if(type.equals("NgoAdmin"))
                {
                    if (getIntent().getStringExtra("isAccepted").equals("Yes"))
                    {
                        oldAgeHomeAcceptButton.setEnabled(false);
                        oldAgeHomeRejectButton.setEnabled(false);
                    }
                    else
                    {
                        oldAgeHomeRejectButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(OldAgeAppealDetailsActivity.this, MainNavigationActivity.class));
                            }
                        });

                        oldAgeHomeAcceptButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new MaterialDialog.Builder(OldAgeAppealDetailsActivity.this)
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

                                final DatabaseReference isAccepted = FirebaseDatabase.getInstance().getReference().child("OldAgeAppeal");
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
                                startActivity(new Intent(OldAgeAppealDetailsActivity.this, MainNavigationActivity.class));
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
