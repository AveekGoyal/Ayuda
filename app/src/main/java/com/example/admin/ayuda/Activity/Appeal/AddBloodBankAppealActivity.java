package com.example.admin.ayuda.Activity.Appeal;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Data.AppealAdapters.BloodBankAppealAdapter;
import com.example.admin.ayuda.Model.Members;
import com.example.admin.ayuda.Model.NgoAdmin;
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

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.egl.EGLDisplay;

public class AddBloodBankAppealActivity extends AppCompatActivity {

    private ImageButton addBloodPicProofImageButton;
    private EditText addBloodPatientNameTextBox;
    private EditText addBloodFamilyMemberNameTextBox;
    private EditText addBloodFamilyMemberContactNoTextBox;
    private EditText addBloodFamilyMemberAltContactNoTextBox;
    private  EditText addBloodHospitalNameTextBox;
    private EditText addBloodHospitalContactNoTextBox;
    private EditText addBloodHospitalAddressTextBox;
    private EditText addBloodPlateletsCountTextBox;
    private EditText addBloodAmountNeededTextBox;
    private Spinner addBloodGroupSpinner;
    private Button  addBloodSubmitButton;
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
        setContentView(R.layout.add_blood_bank_appeal_activity);

        addBloodPicProofImageButton = findViewById(R.id.AddBloodPicProofImageButton);
        addBloodPatientNameTextBox = findViewById(R.id.AddBloodPatientNameTextBox);
        addBloodFamilyMemberNameTextBox =findViewById(R.id.AddBloodFamilyMemberNameTextBox);
        addBloodFamilyMemberContactNoTextBox = findViewById(R.id.AddBloodFamilyMemberContactNoTextBox);
        addBloodFamilyMemberAltContactNoTextBox =findViewById(R.id.AddBloodFamilyMemberAltContactNoTextBox);
        addBloodHospitalNameTextBox = findViewById(R.id.AddBloodHospitalNameTextBox);
        addBloodHospitalContactNoTextBox = findViewById(R.id.AddBloodHospitalContactNoTextBox);
        addBloodHospitalAddressTextBox = findViewById(R.id.AddBloodHospitalAddressTextBox);
        addBloodPlateletsCountTextBox = findViewById(R.id.AddBloodPlateletsCountTextBox);
        addBloodGroupSpinner = findViewById(R.id.AddBloodGroupSpinner);
        addBloodAmountNeededTextBox = findViewById(R.id.AddBloodAmountNeededTextBox);
        addBloodSubmitButton = findViewById(R.id.AddBloodSubmitButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId=mUser.getUid();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("BloodBankAppeals");


        addBloodPicProofImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });

        addBloodSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBloodBankAppeal();
            }
        });

        // Add values in Blood Group Spinner
        String[] bloodgroup = {"O+" , "A+" , "B+" , "AB+" , "A-" , "B-" , "AB-"};
        ArrayAdapter<String> adapterbloodgroup = new ArrayAdapter<>(this , android.R.layout.simple_spinner_dropdown_item , bloodgroup);
        addBloodGroupSpinner.setAdapter(adapterbloodgroup);


    }

    private void addBloodBankAppeal() {

        final String patientName = addBloodPatientNameTextBox.getText().toString().trim();
        final String familyMemName = addBloodFamilyMemberNameTextBox.getText().toString().trim();
        final String familyMemContactNo = addBloodFamilyMemberContactNoTextBox.getText().toString().trim();
        final String familyMemAltContactNo = addBloodFamilyMemberAltContactNoTextBox.getText().toString().trim();
        final String hospitalName = addBloodHospitalNameTextBox.getText().toString().trim();
        final String hospitalContactNo = addBloodHospitalContactNoTextBox.getText().toString().trim();
        final String hospitalAddress = addBloodHospitalAddressTextBox.getText().toString().trim();
        final String plateletsCount = addBloodPlateletsCountTextBox.getText().toString().trim();
        final String bloodGroup = addBloodGroupSpinner.getSelectedItem().toString().trim();
        final String bloodAmountNeeded = addBloodAmountNeededTextBox.getText().toString().trim();


        if(!TextUtils.isEmpty(familyMemName) && !TextUtils.isEmpty(familyMemContactNo) &&
                !TextUtils.isEmpty(hospitalName) &&
                !TextUtils.isEmpty(hospitalContactNo) &&
                !TextUtils.isEmpty(hospitalAddress) &&
                !TextUtils.isEmpty(bloodAmountNeeded) && !TextUtils.isEmpty(bloodGroup) )
        {

            StorageReference filePath = mStorage.child("BloodBankAppeal_Images").child(resultUri.getLastPathSegment());
            filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference getUserDetails= FirebaseDatabase.getInstance().getReference().child("NonMember");
                    getUserDetails.addValueEventListener(new ValueEventListener() {
                        @Override
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

                                        DatabaseReference newBloodAppeal = mDatabaseReference.push();
                                        Map<String, String> dataToSave = new HashMap<>();

                                        dataToSave.put("appealFirstName", memberDetails.getAppealFirstName());
                                        dataToSave.put("appealLastName", memberDetails.getAppealLastName());
                                        dataToSave.put("appealImageDp", memberDetails.getAppealImageDp());
                                        dataToSave.put("patientName", patientName);
                                        dataToSave.put("familyMemberName", familyMemName);
                                        dataToSave.put("familyMemberContactNo", familyMemContactNo);
                                        dataToSave.put("familyMemberAltContactNo", familyMemAltContactNo);
                                        dataToSave.put("hospitalName", hospitalName);
                                        dataToSave.put("hospitalContactNo", hospitalContactNo);
                                        dataToSave.put("hospitalAddress", hospitalAddress);
                                        dataToSave.put("plateletsCount", plateletsCount);
                                        dataToSave.put("bloodGroup", bloodGroup);
                                        dataToSave.put("amountNeeded", bloodAmountNeeded);
                                        dataToSave.put("picProof", downloadUrl.toString());
                                        dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                                        newBloodAppeal.setValue(dataToSave);
                                        startActivity(new Intent(AddBloodBankAppealActivity.this, MainNavigationActivity.class));
                                        finish();


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            } else {


                                DatabaseReference newBloodAppeal = mDatabaseReference.push();
                                Map<String, String> dataToSave = new HashMap<>();

                                dataToSave.put("appealFirstName", userDetail.getAppealFirstName());
                                dataToSave.put("appealLastName", userDetail.getAppealLastName());
                                dataToSave.put("appealImageDp", userDetail.getAppealImageDp());
                                dataToSave.put("patientName", patientName);
                                dataToSave.put("familyMemberName", familyMemName);
                                dataToSave.put("familyMemberContactNo", familyMemContactNo);
                                dataToSave.put("familyMemberAltContactNo", familyMemAltContactNo);
                                dataToSave.put("hospitalName", hospitalName);
                                dataToSave.put("hospitalContactNo", hospitalContactNo);
                                dataToSave.put("hospitalAddress", hospitalAddress);
                                dataToSave.put("plateletsCount", plateletsCount);
                                dataToSave.put("bloodGroup", bloodGroup);
                                dataToSave.put("amountNeeded", bloodAmountNeeded);
                                dataToSave.put("picProof", downloadUrl.toString());
                                dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                                newBloodAppeal.setValue(dataToSave);
                                startActivity(new Intent(AddBloodBankAppealActivity.this, MainNavigationActivity.class));
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
                addBloodPicProofImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }
}
