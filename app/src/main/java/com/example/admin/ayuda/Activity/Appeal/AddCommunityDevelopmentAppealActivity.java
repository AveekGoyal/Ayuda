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

public class AddCommunityDevelopmentAppealActivity extends AppCompatActivity {

    private ImageButton addCommunityDevPicProofImageButton;
    private CheckBox addCommunityDevCleaningCheckBox;
    private CheckBox addCommunityDevHungerCheckBox;
    private CheckBox addCommunityDevHealthIssuesCheckBox;
    private CheckBox addCommunityDevPovertyCheckBox;
    private EditText addCommunityDevDescTextBox;
    private Button addCommunityDevPreviousComplaintsButton;
    private EditText addCommunityDevContactNoTextBox;
    private Button addCommunityDevSubmitButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private StorageReference mStorage;
    private static final int GALLERY_CODE = 1;
    private Uri mImageUri;
    private Uri resultUri;
    private String userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_community_development_appeal_activity);

        addCommunityDevPicProofImageButton = findViewById(R.id.AddCommunityDevPicProofImageButton);
        addCommunityDevCleaningCheckBox = findViewById(R.id.AddCommunityDevCleaningCheckBox);
        addCommunityDevHungerCheckBox = findViewById(R.id.AddCommunityDevHungerCheckBox);
        addCommunityDevHealthIssuesCheckBox = findViewById(R.id.AddCommunityDevHealthIssuesCheckBox);
        addCommunityDevPovertyCheckBox = findViewById(R.id.AddCommunityDevPovertyCheckBox);
        addCommunityDevDescTextBox = findViewById(R.id.AddCommunityDevDescTextBox);
        addCommunityDevPreviousComplaintsButton = findViewById(R.id.AddCommunityDevPreviousComplaintsButton);
        addCommunityDevContactNoTextBox = findViewById(R.id.AddCommunityDevContactNoTextBox);
        addCommunityDevSubmitButton = findViewById(R.id.AddCommunityDevSubmitButton);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId = mUser.getUid();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("CommunityDevelopmentAppeals");


        addCommunityDevPicProofImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });

        addCommunityDevSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCommunityDevelopmentAppeal();
            }
        });
    }


    private void addCommunityDevelopmentAppeal() {
        new MaterialDialog.Builder(this)
                .title("Uploading Appeal")
                .content("Please Wait")
                .progress(true, 0)
                .show();

        final String description = addCommunityDevDescTextBox.getText().toString().trim();
        final String ContactNo = addCommunityDevContactNoTextBox.getText().toString().trim();

        if (!TextUtils.isEmpty(description) && !TextUtils.isEmpty(ContactNo)) {
            StorageReference filePath = mStorage.child("CommunityDevelopmentAppeal_Images").child(resultUri.getLastPathSegment());
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


                                        DatabaseReference newCommunityAppeal = mDatabaseReference.push();
                                        Map<String, String> dataToSave = new HashMap<>();

                                        dataToSave.put("appealFirstName", memberDetails.getAppealFirstName());
                                        dataToSave.put("appealLastName", memberDetails.getAppealLastName());
                                        dataToSave.put("appealImageDp", memberDetails.getAppealImageDp());
                                        dataToSave.put("description", description);
                                        if (addCommunityDevCleaningCheckBox.isChecked()) {
                                            dataToSave.put("cleaning", "Yes");
                                        } else {
                                            dataToSave.put("cleaning", "No");
                                        }
                                        if (addCommunityDevHungerCheckBox.isChecked()) {
                                            dataToSave.put("hunger", "Yes");
                                        } else {
                                            dataToSave.put("hunger", "No");
                                        }
                                        if (addCommunityDevHealthIssuesCheckBox.isChecked()) {
                                            dataToSave.put("healthIssues", "Yes");
                                        } else {
                                            dataToSave.put("healthIssues", "No");
                                        }
                                        if (addCommunityDevPovertyCheckBox.isChecked()) {
                                            dataToSave.put("poverty", "Yes");
                                        } else {
                                            dataToSave.put("poverty", "No");
                                        }

                                        dataToSave.put("contactNo", ContactNo);
                                        dataToSave.put("picProof", downloadUrl.toString());
                                        dataToSave.put("timestamp", String.valueOf(System.currentTimeMillis()));
                                        newCommunityAppeal.setValue(dataToSave);
                                        startActivity(new Intent(AddCommunityDevelopmentAppealActivity.this, MainNavigationActivity.class));
                                        finish();

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }

                                });
                            } else {
                                DatabaseReference newCommunityAppeal = mDatabaseReference.push();
                                Map<String, String> dataToSave = new HashMap<>();

                                dataToSave.put("appealFirstName", userDetail.getAppealFirstName());
                                dataToSave.put("appealLastName", userDetail.getAppealLastName());
                                dataToSave.put("appealImageDp", userDetail.getAppealImageDp());
                                dataToSave.put("description", description);
                                if (addCommunityDevCleaningCheckBox.isChecked()) {
                                    dataToSave.put("cleaning", "Yes");
                                } else {
                                    dataToSave.put("cleaning", "No");
                                }
                                if (addCommunityDevHungerCheckBox.isChecked()) {
                                    dataToSave.put("hunger", "Yes");
                                } else {
                                    dataToSave.put("hunger", "No");
                                }
                                if (addCommunityDevHealthIssuesCheckBox.isChecked()) {
                                    dataToSave.put("healthIssues", "Yes");
                                } else {
                                    dataToSave.put("healthIssues", "No");
                                }
                                if (addCommunityDevPovertyCheckBox.isChecked()) {
                                    dataToSave.put("poverty", "Yes");
                                } else {
                                    dataToSave.put("poverty", "No");
                                }

                                dataToSave.put("contactNo", ContactNo);
                                dataToSave.put("picProof", downloadUrl.toString());
                                dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                                newCommunityAppeal.setValue(dataToSave);
                                startActivity(new Intent(AddCommunityDevelopmentAppealActivity.this, MainNavigationActivity.class));
                                finish();

                            }
                        }

                            @Override
                            public void onCancelled (DatabaseError databaseError){

                            }
                    });


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
                addCommunityDevPicProofImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }
}


