package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Model.NonMember;
import com.example.admin.ayuda.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

public class AddOldAgeAppealActivity extends AppCompatActivity {

    private ImageButton addOldAgeHomePicProofImageButton;
    private EditText addOldAgeHomeDescriptionTextBox;
    private EditText addOldAgeHomeAddressTextBox;
    private CheckBox addOldAgeHomeFinancialNeeds;
    private  CheckBox addOldAgeHomeMedicalNeeds;
    private  CheckBox addOldAgeHomeLivelihoodNeeds;
    private Button addOldAgeHomeSubmitButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private StorageReference mStorage;
    private static final int GALLERY_CODE =1;
    private Uri mImageUri;
    private Uri resultUri;
    private String userId;
    private String financialNeeds = "No ";
    private String medicalNeeds = "No";
    private String livelihoodNeeds = "No";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_old_age_appeal_activity);

        addOldAgeHomePicProofImageButton = findViewById(R.id.AddOldAgeHomePicProofImageButton);
        addOldAgeHomeDescriptionTextBox = findViewById(R.id.AddOldAgeHomeDescriptionTextBox);
        addOldAgeHomeAddressTextBox = findViewById(R.id.AddOldAgeHomeAddressTextBox);
        addOldAgeHomeFinancialNeeds = findViewById(R.id.AddOldAgeHomeFinancialNeeds);
        addOldAgeHomeMedicalNeeds = findViewById(R.id.AddOldAgeHomeMedicalNeeds);
        addOldAgeHomeLivelihoodNeeds = findViewById(R.id.AddOldAgeHomeLivelihoodNeeds);
        addOldAgeHomeSubmitButton = findViewById(R.id.AddOldAgeHomeSubmitButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId = mUser.getUid();

        mStorage = FirebaseStorage.getInstance().getReference();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("OldAgeAppeal");

        addOldAgeHomePicProofImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });

        addOldAgeHomeSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addOldAgeHomeFinancialNeeds.isChecked())
                    financialNeeds = "Yes";
                if(addOldAgeHomeMedicalNeeds.isChecked())
                    medicalNeeds = "Yes";
                if(addOldAgeHomeLivelihoodNeeds.isChecked())
                    livelihoodNeeds = "Yes";
                addOldAgeAppeal();
            }
        });


    }

    private void addOldAgeAppeal() {
        new MaterialDialog.Builder(this)
                .title("Uploading Appeal")
                .content("Please Wait")
                .progress(true, 0)
                .show();


        final String homeDescription = addOldAgeHomeDescriptionTextBox.getText().toString().trim();
        final String homeAddress = addOldAgeHomeDescriptionTextBox.getText().toString().trim();
        final String financial = financialNeeds;
        final String medical = medicalNeeds;
        final String livelihood = livelihoodNeeds;

        if(!TextUtils.isEmpty(homeAddress) && !TextUtils.isEmpty(homeDescription))
        {
            StorageReference filePath   = mStorage.child("OldAgeAppeal_Pics").child(resultUri.getLastPathSegment());
            filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference getUserDetails = FirebaseDatabase.getInstance().getReference().child("NonMember");
                    getUserDetails.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String fName = dataSnapshot.child(userId).child("firstName").getValue(String.class);
                            String lname = dataSnapshot.child(userId).child("lastName").getValue(String.class);
                            String imageUrl = dataSnapshot.child(userId).child("imageDp").getValue(String.class);

                            NonMember userDetail = new NonMember(fName,lname,imageUrl);


                            DatabaseReference newOldAgeAppeal = mDatabaseReference.push();
                            Map<String , String > dataToSave = new HashMap<>();

                            dataToSave.put("appealFirstName" , userDetail.getAppealFirstName());
                            dataToSave.put("appealLastName" , userDetail.getAppealLastName());
                            dataToSave.put("appealImageDp" , userDetail.getAppealImageDp());
                            dataToSave.put("description" , homeDescription);
                            dataToSave.put("address" , homeAddress);
                            dataToSave.put("financialNeeds" ,financial);
                            dataToSave.put("medicalNeeds" , medical);
                            dataToSave.put("livelihoodNeeds" , livelihood);
                            dataToSave.put("timestamp",String.valueOf(java.lang.System.currentTimeMillis()));
                            dataToSave.put("picProof",downloadUrl.toString());

                            newOldAgeAppeal.setValue(dataToSave);

                            startActivity(new Intent(AddOldAgeAppealActivity.this , MainNavigationActivity.class));
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            });
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            mImageUri = data.getData();
            CropImage.activity(mImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON).start(this);

        }
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
            {
                resultUri = result.getUri();
                addOldAgeHomePicProofImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }

}
