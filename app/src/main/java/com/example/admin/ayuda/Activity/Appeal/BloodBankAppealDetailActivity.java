package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.ayuda.Activity.Login.MainActivity;
import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Data.AppealAdapters.AppealAcceptedByNgoAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.BloodBankAppealAdapter;
import com.example.admin.ayuda.Model.BloodBankAppeal;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static android.icu.lang.UProperty.INT_START;

public class BloodBankAppealDetailActivity extends AppCompatActivity {

    private ImageView bloodPicProofImageView;
    private TextView bloodBankFamilyMemberNamePlainText;
    private TextView bloodBankFamilyMemberContactNoPlainText;
    private TextView bloodBankFamilyMemberAltContactNoPlainText;
    private TextView bloodBankHospitalNamePlainText;
    private TextView bloodBankHospitalContactNoPlainText;
    private TextView bloodBankHospitalAddressPlainText;
    private TextView bloodBankPlateletsCountPlainText;
    private TextView bloodBankGroup;
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
    String type=" ";
    String adminEmail = " ";
    String adminOrgName = " ";
    String adminContactNo=" ";
    String isAppealAccepted = "No";

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
        bloodBankGroup = findViewById(R.id.BloodBankGroupPlainText);
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
        //bloodBankPatientName.setText(String.format("Patient Name : %s", getIntent().getStringExtra("patientName")));
        //bloodBankFamilyMemberNamePlainText.setText(String.format("Family Member : %s", getIntent().getStringExtra("familyMemberName")));
        //bloodBankFamilyMemberContactNoPlainText.setText(String.format("Contact Number: %s", getIntent().getStringExtra("familyMemberContact")));
        //bloodBankFamilyMemberAltContactNoPlainText.setText(String.format("Alternate Number: %s", getIntent().getStringExtra("familyMemberAltContact")));
        //bloodBankHospitalNamePlainText.setText(String.format("Hospital Name: %s", getIntent().getStringExtra("hospitalName")));
        //bloodBankHospitalContactNoPlainText.setText(String.format("Hospital Contact Number : %s", getIntent().getStringExtra("hospitalContactNo")));
        //bloodBankPlateletsCountPlainText.setText(String.format("Platelets Count: %s", getIntent().getStringExtra("plateletsCount")));

        //bloodBankAmountNeededPlainText.setText(String.format("Amount Needed : %s", getIntent().getStringExtra("bloodAmountNeeded")));
        //bloodBankHospitalAddressPlainText.setText(String.format("Hospital Address : %s", getIntent().getStringExtra("hospitalAddress")));
        String boldText1 = "Patient Name : ";
        String normalText1 = (String.format(getIntent().getStringExtra("patientName")));
        SpannableStringBuilder str1 = new SpannableStringBuilder(boldText1 + normalText1);
        str1.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankPatientName.setText(str1);
        String boldText2 = "Family Member Name : ";
        String normalText2 = (String.format(getIntent().getStringExtra("familyMemberName")));
        SpannableStringBuilder str2 = new SpannableStringBuilder(boldText2 + normalText2);
        str2.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankFamilyMemberNamePlainText.setText(str2);
        String boldText3 = "Contact No : ";
        String normalText3 = (String.format(getIntent().getStringExtra("familyMemberContact")));
        SpannableStringBuilder str3 = new SpannableStringBuilder(boldText3 + normalText3);
        str3.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankFamilyMemberContactNoPlainText.setText(str3);
        String boldText4 = "Alt Contact No : ";
        String normalText4 = (String.format(getIntent().getStringExtra("familyMemberAltContact")));
        SpannableStringBuilder str4 = new SpannableStringBuilder(boldText4 + normalText4);
        str4.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText4.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankFamilyMemberAltContactNoPlainText.setText(str4);
        String boldText5 = "Hospital Name : ";
        String normalText5 = (String.format(getIntent().getStringExtra("hospitalName")));
        SpannableStringBuilder str5 = new SpannableStringBuilder(boldText5 + normalText5);
        str5.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText5.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankHospitalNamePlainText.setText(str5);
        String boldText6 = "Hospital Contact No : ";
        String normalText6 = (String.format(getIntent().getStringExtra("hospitalContactNo")));
        SpannableStringBuilder str6 = new SpannableStringBuilder(boldText6 + normalText6);
        str6.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText6.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankHospitalContactNoPlainText.setText(str6);
        String boldText7 = "Platelets Count : ";
        String normalText7 = (String.format(getIntent().getStringExtra("plateletsCount")));
        SpannableStringBuilder str7 = new SpannableStringBuilder(boldText7 + normalText7);
        str2.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText7.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankPlateletsCountPlainText.setText(str7);
        String boldText8 = "Amount Needed :";
        String normalText8 = (String.format(getIntent().getStringExtra("bloodAmountNeeded")));
        SpannableStringBuilder str8 = new SpannableStringBuilder(boldText8 + normalText8);
        str8.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText8.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankFamilyMemberNamePlainText.setText(str8);
        String boldText9 = "Hospital Address :";
        String normalText9 = (String.format(getIntent().getStringExtra("hospitalAddress")));
        SpannableStringBuilder str9 = new SpannableStringBuilder(boldText9 + normalText9);
        str9.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText9.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        bloodBankHospitalAddressPlainText.setText(str9);
        bloodBankGroup.setText(String.format(": %s" , getIntent().getStringExtra("bloodGroup")));
        //String boldText10 = "Blood Group : ";
        //String normalText10 = (String.format(getIntent().getStringExtra("bloodGroup")));
        //SpannableStringBuilder str10 = new SpannableStringBuilder(boldText10 + normalText10);
        //str10.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText10.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //bloodBankGroup.setText(str10);









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
                    bloodBankAcceptButton.setEnabled(false);
                    bloodBankRejectButton.setEnabled(false);
                }
                else if(type.equals("NgoAdmin"))
                {
                    if (getIntent().getStringExtra("isAccepted").equals("Yes"))
                    {
                        bloodBankAcceptButton.setEnabled(false);
                        bloodBankRejectButton.setEnabled(false);
                    }
                    else
                    {
                        bloodBankRejectButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(BloodBankAppealDetailActivity.this, MainNavigationActivity.class));
                            }
                        });

                        bloodBankAcceptButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new MaterialDialog.Builder(BloodBankAppealDetailActivity.this)
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
                                dataToSave.put("appealName", getIntent().getStringExtra("patientName"));
                                appealAcceptByNgo.setValue(dataToSave);

                                final DatabaseReference isAccepted = FirebaseDatabase.getInstance().getReference().child("BloodBankAppeals");
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
                                startActivity(new Intent(BloodBankAppealDetailActivity.this, MainNavigationActivity.class));
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
