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
import com.example.admin.ayuda.Model.Members;
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

public class AddDisasterManagementAppeal extends AppCompatActivity {

    private ImageButton addDisasterPicProofImageButton;
    private EditText addDisasterDescTextBox;
    private CheckBox addDisasterNeedFood;
    private CheckBox addDisasterNeedWater;
    private CheckBox addDisasterNeedShelter;
    private CheckBox addDisasterNeedClothing;
    private CheckBox addDisasterNeedMedical;
    private CheckBox addDisasterNeedRehab;
    private  EditText addDisasterContactNoTextBox;
    private EditText addDisasterAltContactNoTextBox;
    private Button addDisasterSafetyButton;
    private Button addDisasterSubmitButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private StorageReference mStorage;
    private static final int GALLERY_CODE =1;
    private Uri mImageUri;
    private Uri resultUri;
    private String userId;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_disaster_management_appeal);

        addDisasterPicProofImageButton = findViewById(R.id.AddDisasterPicProofImageButton);
        addDisasterDescTextBox = findViewById(R.id.AddDisasterDescTextBox);
        addDisasterNeedFood = findViewById(R.id.AddDisasterNeedFood);
        addDisasterNeedWater = findViewById(R.id.AddDisasterNeedWater);
        addDisasterNeedShelter = findViewById(R.id.AddDisasterNeedShelter);
        addDisasterNeedClothing = findViewById(R.id.AddDisasterNeedClothing);
        addDisasterNeedMedical = findViewById(R.id.AddDisasterNeedMedical);
        addDisasterNeedRehab = findViewById(R.id.AddDisasterNeedRehab);
        addDisasterContactNoTextBox = findViewById(R.id.AddDisasterContactNoTextBox);
        addDisasterAltContactNoTextBox = findViewById(R.id.AddDisasterAltContactNoTextBox);
        addDisasterSafetyButton = findViewById(R.id.AddDisasterSafetyButton);
        addDisasterSubmitButton = findViewById(R.id.AddDisasterSubmitButton);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId=mUser.getUid();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("DisasterManagementAppeals");

        addDisasterPicProofImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });

        addDisasterSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDisasterManagementAppeal();
            }
        });


    }

    private void addDisasterManagementAppeal() {
        new MaterialDialog.Builder(this)
                .title("Uploading Appeal")
                .content("Please Wait")
                .progress(true, 0)
                .show();

        final String description = addDisasterDescTextBox.getText().toString().trim();
        final String contactNo = addDisasterContactNoTextBox.getText().toString().trim();
        final String altContactNo = addDisasterAltContactNoTextBox.getText().toString().trim();

        if (!TextUtils.isEmpty(description) && !TextUtils.isEmpty(contactNo)) {
            StorageReference filePath = mStorage.child("DisasterManagementAppeal_Images").child(resultUri.getLastPathSegment());
            filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference getUserDetails = FirebaseDatabase.getInstance().getReference().child("NonMember");
                    getUserDetails.addValueEventListener(new ValueEventListener() {

                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String fName = dataSnapshot.child(userId).child("firstName").getValue(String.class);
                            String lname = dataSnapshot.child(userId).child("lastName").getValue(String.class);
                            String imageUrl = dataSnapshot.child(userId).child("imageDp").getValue(String.class);
                            NonMember userDetail = new NonMember(fName, lname, imageUrl);

                            if (fName == null && lname == null && imageUrl == null) {
                                DatabaseReference getMemberDetails = FirebaseDatabase.getInstance().getReference().child("Member");
                                getMemberDetails.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String fName = dataSnapshot.child(userId).child("firstName").getValue(String.class);
                                        String lname = dataSnapshot.child(userId).child("lastName").getValue(String.class);
                                        String imageUrl = dataSnapshot.child(userId).child("imageDp").getValue(String.class);
                                        Members memberDetails = new Members(fName, lname, imageUrl);

                                        DatabaseReference newDisasterAppeal = mDatabaseReference.push();
                            Map<String, String> dataToSave = new HashMap<>();

                            dataToSave.put("appealFirstName", memberDetails.getAppealFirstName());
                            dataToSave.put("appealLastName", memberDetails.getAppealLastName());
                            dataToSave.put("appealImageDp", memberDetails.getAppealImageDp());
                            dataToSave.put("description", description);

                            if (addDisasterNeedFood.isChecked()) {
                                dataToSave.put("needFood", "Yes");
                            } else {
                                dataToSave.put("needFood", "No");
                            }
                            if (addDisasterNeedWater.isChecked()) {
                                dataToSave.put("needWater", "Yes");
                            } else {
                                dataToSave.put("needWater", "No");
                            }
                            if (addDisasterNeedShelter.isChecked()) {
                                dataToSave.put("needShelter", "Yes");
                            } else {
                                dataToSave.put("needShelter", "No");
                            }
                            if (addDisasterNeedClothing.isChecked()) {
                                dataToSave.put("needClothing", "Yes");
                            } else {
                                dataToSave.put("needClothing", "No");
                            }
                            if (addDisasterNeedMedical.isChecked()) {
                                dataToSave.put("needMedical", "Yes");
                            } else {
                                dataToSave.put("needMedical", "No");
                            }
                            if (addDisasterNeedRehab.isChecked()) {
                                dataToSave.put("needRehab", "Yes");
                            } else {
                                dataToSave.put("needRehab", "No");
                            }
                            dataToSave.put("contactNo", contactNo);
                            dataToSave.put("altContactNo", altContactNo);
                            dataToSave.put("picProof", downloadUrl.toString());
                            dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                            newDisasterAppeal.setValue(dataToSave);
                            startActivity(new Intent(AddDisasterManagementAppeal.this, MainNavigationActivity.class));
                            finish();

                        }

                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                }
                else {
                                DatabaseReference newDisasterAppeal = mDatabaseReference.push();
                                Map<String, String> dataToSave = new HashMap<>();

                                dataToSave.put("appealFirstName", userDetail.getAppealFirstName());
                                dataToSave.put("appealLastName", userDetail.getAppealLastName());
                                dataToSave.put("appealImageDp", userDetail.getAppealImageDp());
                                dataToSave.put("description", description);

                                if (addDisasterNeedFood.isChecked()) {
                                    dataToSave.put("needFood", "Yes");
                                } else {
                                    dataToSave.put("needFood", "No");
                                }
                                if (addDisasterNeedWater.isChecked()) {
                                    dataToSave.put("needWater", "Yes");
                                } else {
                                    dataToSave.put("needWater", "No");
                                }
                                if (addDisasterNeedShelter.isChecked()) {
                                    dataToSave.put("needShelter", "Yes");
                                } else {
                                    dataToSave.put("needShelter", "No");
                                }
                                if (addDisasterNeedClothing.isChecked()) {
                                    dataToSave.put("needClothing", "Yes");
                                } else {
                                    dataToSave.put("needClothing", "No");
                                }
                                if (addDisasterNeedMedical.isChecked()) {
                                    dataToSave.put("needMedical", "Yes");
                                } else {
                                    dataToSave.put("needMedical", "No");
                                }
                                if (addDisasterNeedRehab.isChecked()) {
                                    dataToSave.put("needRehab", "Yes");
                                } else {
                                    dataToSave.put("needRehab", "No");
                                }
                                dataToSave.put("contactNo", contactNo);
                                dataToSave.put("altContactNo", altContactNo);
                                dataToSave.put("picProof", downloadUrl.toString());
                                dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                                newDisasterAppeal.setValue(dataToSave);
                                startActivity(new Intent(AddDisasterManagementAppeal.this, MainNavigationActivity.class));
                                finish();


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    //String userId = mAuth.getCurrentUser().getUid();

                }
            });

        }



    }
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
                addDisasterPicProofImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }
}
