package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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

import es.dmoral.toasty.Toasty;

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
    String type=" ";
    String adminEmail = " ";
    String adminOrgName = " ";
    String isAppealAccepted = "No";
    String adminContactNo=" ";


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
       // disasterMgmtTypeOfDisaster = findViewById(R.id.DisasterMgmtTypeOfDisasterPlainText);
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

        String boldText1 = "Description :";
        String normalText1 = (String.format(getIntent().getStringExtra("appealTitle")));
        SpannableStringBuilder str1 = new SpannableStringBuilder(boldText1 + normalText1);
        str1.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        disasterMgmtDescPlainText.setText(str1);

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
        disasterMgmtTypeOfDisasterPlainText.setText(String.format(" : %s", getIntent().getStringExtra("typeOfDisaster")));

        String boldText2 = "Contact Number : ";
        String normalText2 = (String.format(getIntent().getStringExtra("contactNo")));
        SpannableStringBuilder str2 = new SpannableStringBuilder(boldText2 + normalText2);
        str2.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        disasterMgmtContactNoPlainText.setText(str2);

        String boldText3 = "Alternate Contact Number : ";
        String normalText3 = (String.format(getIntent().getStringExtra("altContactNo")));
        SpannableStringBuilder str3 = new SpannableStringBuilder(boldText3 + normalText3);
        str3.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        disasterMgmtAltContactNoPlainText.setText(str3);


        disasterMgmtRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisasterManagementAppealDetailsActivity.this, MainNavigationActivity.class));
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
                    disasterMgmtAcceptButton.setEnabled(false);
                    disasterMgmtRejectButton.setEnabled(false);
                }
                else if(type.equals("NgoAdmin"))
                {
                    if (getIntent().getStringExtra("isAccepted").equals("Yes"))
                    {
                        disasterMgmtAcceptButton.setEnabled(false);
                        disasterMgmtRejectButton.setEnabled(false);
                    }
                    else
                    {
                        disasterMgmtRejectButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(DisasterManagementAppealDetailsActivity.this, MainNavigationActivity.class));
                            }
                        });

                        disasterMgmtAcceptButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new MaterialDialog.Builder(DisasterManagementAppealDetailsActivity.this)
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

                                final DatabaseReference isAccepted = FirebaseDatabase.getInstance().getReference().child("DisasterManagementAppeals");
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
                                Toasty.info(getApplicationContext() , "Appeal Accepted." , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DisasterManagementAppealDetailsActivity.this, MainNavigationActivity.class));
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

