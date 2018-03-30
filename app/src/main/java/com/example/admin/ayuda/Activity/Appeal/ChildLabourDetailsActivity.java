package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Data.AppealAdapters.ChildLabourAppealAdapter;
import com.example.admin.ayuda.Model.ChildAbuseAppeals;
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

import es.dmoral.toasty.Toasty;


public class ChildLabourDetailsActivity extends AppCompatActivity {

    private ImageView childLabourPicProofImageView;
    private TextView childLabourDescPlainText;
    private CheckBox childLabourPhysicalAbuse;
    private CheckBox childLabourSexualAbuse;
    private CheckBox childLabourPsychologicalAbuse;
    private CheckBox childLabourAbandon;
    private CheckBox childLabourChildMarriage;
    private CheckBox childLabourChildLabour;
    private TextView childLabourAproxAge;
    private RadioButton childLabourMale;
    private RadioButton childLabourFemale;
    private Button childLabourAcceptButton;
    private Button childLabourRejectButton;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private ChildLabourAppealAdapter childLabourAppealAdapter;
    private List<ChildAbuseAppeals> childAbuseAppealsList;
    String type=" ";
    String adminEmail = " ";
    String adminOrgName = " ";
    String isAppealAccepted = "No";
    String adminContactNo=" ";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_labour_details_activity);

        childLabourPicProofImageView = findViewById(R.id.ChildLabourPicProofImageView);
        childLabourDescPlainText = findViewById(R.id.ChildLabourDescPlainText);
        childLabourPhysicalAbuse = findViewById(R.id.ChildLabourPhysicalAbuse);
        childLabourSexualAbuse = findViewById(R.id.ChildLabourSexualAbuse);
        childLabourPsychologicalAbuse = findViewById(R.id.ChildLabourPsychologicalAbuse);
        childLabourAbandon = findViewById(R.id.ChildLabourAbandon);
        childLabourChildLabour = findViewById(R.id.ChildLabourChildLabour);
        childLabourChildMarriage = findViewById(R.id.ChildLabourChildMarriage);
        childLabourAproxAge = findViewById(R.id.ChildLabourApproxAgePlainText);
        childLabourMale = findViewById(R.id.ChildLabourRadioMale);
        childLabourFemale = findViewById(R.id.ChildLabourRadioFemale);
        childLabourAcceptButton = findViewById(R.id.ChildLabourAcceptButton);
        childLabourRejectButton = findViewById(R.id.ChildLabourRejectButton);
        
        //Setting CheckBox Uneditable so tha in view option user cannot edit them
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("ChildLabourAppeal");
        mDatabaseReference.keepSynced(true);

        String imageUrl = getIntent().getStringExtra("appealPic");
        Picasso.with(getApplicationContext()).load(imageUrl).into(childLabourPicProofImageView);

        String boldText1 = "Description :";
        String normalText1 = (String.format(getIntent().getStringExtra("appealTitle")));
        SpannableStringBuilder str1 = new SpannableStringBuilder(boldText1 + normalText1);
        str1.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        childLabourDescPlainText.setText(str1);


        if (getIntent().getStringExtra("physicalAbuse").equals("Yes")){
            childLabourPhysicalAbuse.setChecked(true);
            childLabourPhysicalAbuse.setEnabled(false);

        }
        else
        {
            childLabourPhysicalAbuse.setEnabled(false);
        }
        if (getIntent().getStringExtra("sexualAbuse").equals("Yes")){
            childLabourSexualAbuse.setChecked(true);
            childLabourSexualAbuse.setEnabled(false);
        }
        else
        {
            childLabourSexualAbuse.setEnabled(false);
        }
        if (getIntent().getStringExtra("psychologicalAbuse").equals("Yes")){
            childLabourPsychologicalAbuse.setChecked(true);
            childLabourPsychologicalAbuse.setEnabled(false);
        }
        else
        {
            childLabourPsychologicalAbuse.setEnabled(false);
        }
        if (getIntent().getStringExtra("abandon").equals("Yes")){
            childLabourAbandon.setChecked(true);
            childLabourAbandon.setEnabled(false);
        }
        else
        {
            childLabourAbandon.setEnabled(false);
        }
        if (getIntent().getStringExtra("childMarriage").equals("Yes")){
            childLabourChildMarriage.setChecked(true);
            childLabourChildMarriage.setEnabled(false);
        }
        else
        {
            childLabourChildMarriage.setEnabled(false);
        }
        if (getIntent().getStringExtra("childLabour").equals("Yes")){
            childLabourChildLabour.setChecked(true);
            childLabourChildLabour.setEnabled(false);
        }
        else
        {
            childLabourChildLabour.setEnabled(false);
        }
        childLabourAproxAge.setText(String.format(" : %s", getIntent().getStringExtra("appoxAge")));
        if(getIntent().getStringExtra("gender").equals("Male"))
        {
            childLabourMale.setChecked(true);
            childLabourMale.setEnabled(false);
            childLabourFemale.setEnabled(false);
        }
        else
        {
            childLabourFemale.setChecked(true);
            childLabourFemale.setEnabled(false);
            childLabourMale.setEnabled(false);
        }



        final String userId = mUser.getUid();
        DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child("NgoAdmin").child(userId);
        getType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                type = dataSnapshot.child("type").getValue(String.class);
                adminEmail = dataSnapshot.child("email").getValue(String.class);
                adminOrgName = dataSnapshot.child("orgName").getValue(String.class);
                adminContactNo = dataSnapshot.child("mobileNumber").getValue(String.class);
                if(type == null)
                {
                    childLabourAcceptButton.setEnabled(false);
                    childLabourRejectButton.setEnabled(false);
                }
                else if(type.equals("NgoAdmin"))
                {
                    if (getIntent().getStringExtra("isAccepted").equals("Yes"))
                    {
                        childLabourRejectButton.setEnabled(false);
                        childLabourAcceptButton.setEnabled(false);
                    }
                    else
                    {
                        childLabourRejectButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(ChildLabourDetailsActivity.this, MainNavigationActivity.class));
                            }
                        });

                        childLabourAcceptButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new MaterialDialog.Builder(ChildLabourDetailsActivity.this)
                                        .title("Accepting Appeal")
                                        .content("Please Wait")
                                        .progress(true,0)
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
                                FirebaseDatabase.getInstance().getReference().child("ChildLabourAppeal").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot item : dataSnapshot.getChildren())
                                        {
                                            String timestamp = item.child("timestamp").getValue(String.class);
                                            if (timestamp.equals(getIntent().getStringExtra("timestamp")))
                                            {
                                                item.getRef().child("isAccepted").setValue("Yes");
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                Toasty.info(getApplicationContext() , "Appeal Accepted." , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChildLabourDetailsActivity.this, MainNavigationActivity.class));
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
