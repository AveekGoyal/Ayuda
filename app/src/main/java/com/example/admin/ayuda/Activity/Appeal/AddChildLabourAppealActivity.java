package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

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


public class AddChildLabourAppealActivity extends AppCompatActivity {

    private ImageButton addChildLabourPicProofImageButton;
    private EditText addChildLabourDescTextBox;
    private CheckBox addChildLabourPhysicalAbuse;
    private CheckBox addChildLabourSexualAbuse;
    private CheckBox addChildLabourPsychologicalAbuse;
    private CheckBox addChildLabourAbandon;
    private CheckBox addChildLabourChildLabour;
    private CheckBox addChildLabourChildMarriage;
    private RadioButton addChildLabourRadioMale;
    private RadioButton addChildLabourRadioFemale;
    private Button addChildLabourSubmitButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private StorageReference mStorageReference;
    private FirebaseUser mUser;
    private StorageReference mStorage;
    private FirebaseDatabase mDatabase;
    private static final int GALLERY_CODE =1;
    private Uri mImageUri;
    private Uri resultUri;
    private String userId;
    private String male = " ";
    private String female = " " ;
    private String physical = "No";
    private String sexual = "No";
    private String psyco = "No";
    private String abandon = "No";
    private String childLabour = "No";
    private String childMarriage = "No";
    private Spinner addChildLabourChildAgeSpinner;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_child_labour_appeal_activity);

        addChildLabourPicProofImageButton = findViewById(R.id.AddChildLabourPicProofImageButton);
        addChildLabourChildAgeSpinner = findViewById(R.id.AddChildLabourChildAgeSpinner);
        addChildLabourDescTextBox = findViewById(R.id.AddChildLabourDescTextBox);
        addChildLabourPhysicalAbuse = findViewById(R.id.AddChildLabourPhysicalAbuse);
        addChildLabourSexualAbuse = findViewById(R.id.AddChildLabourSexualAbuse);
        addChildLabourAbandon = findViewById(R.id.AddChildLabourAbandon);
        addChildLabourPsychologicalAbuse = findViewById(R.id.AddChildLabourPsychologicalAbuse);
        addChildLabourChildLabour = findViewById(R.id.AddChildLabourChildLabour);
        addChildLabourChildMarriage = findViewById(R.id.AddChildLabourChildMarriage);
        addChildLabourRadioMale = findViewById(R.id.AddChildLabourRadioMale);
        addChildLabourRadioFemale = findViewById(R.id.AddChildLabourRadioFemale);
        addChildLabourSubmitButton = findViewById(R.id.AddChildLabourSubmitButton);
        mStorage = FirebaseStorage.getInstance().getReference();
        String[] ageGrp = {"0-6" , "6-10" , "10-16"};
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item, ageGrp);
        addChildLabourChildAgeSpinner.setAdapter(adapterAge);


        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("ChildLabourAppeal");
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference().child("ChildLabourAppeal_Pics");
        mUser = mAuth.getCurrentUser();

        userId = mUser.getUid();

        addChildLabourPicProofImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });

        addChildLabourSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(addChildLabourSexualAbuse.isChecked())
//                    sexual = "Yes";
//                if(addChildLabourPhysicalAbuse.isChecked())
//                    physical = "Yes";
//                if(addChildLabourPsychologicalAbuse.isChecked())
//                    psyco = "Yes";
//                if(addChildLabourAbandon.isChecked())
//                    abandon = "Yes";
//                if(addChildLabourPhysicalAbuse.isChecked())
//                    childLabour = "Yes";
//                if(addChildLabourChildMarriage.isChecked())
//                    childMarriage = "Yes";
//                if(addChildLabourRadioMale.isSelected())
//                    male = "Male";
//                else if(addChildLabourRadioFemale.isSelected())
//                    female = "Female";


                addChildLAbourAppeal();
            }
        });

    }

    private void addChildLAbourAppeal() {

        final String desc = addChildLabourDescTextBox.getText().toString().trim();
//        final String physicalStr = physical;
//        final String sexualStr = sexual;
//        final String psycoStr = psyco;
//        final String abandonStr = abandon;
//        final String childLabourStr = childLabour;
//        final String childMarriageStr  = childMarriage;
        final String gender;
        final String approxAge = addChildLabourChildAgeSpinner.getSelectedItem().toString();
        if(male.equals("Male"))
            gender = male;
        else
            gender = female;

        if(!TextUtils.isEmpty(desc) && !TextUtils.isEmpty(gender) && !TextUtils.isEmpty(approxAge))
        {

            StorageReference filePath   = mStorage.child("ChildLabourAppeal_Pics").child(resultUri.getLastPathSegment());
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

                                        DatabaseReference newChildLabourAppeal = mDatabaseReference.push();
                                        Map<String, String> dataToSave = new HashMap<>();
                                        dataToSave.put("appealFirstName", memberDetails.getAppealFirstName());
                                        dataToSave.put("appealLastName", memberDetails.getAppealLastName());
                                        dataToSave.put("appealImageDp", memberDetails.getAppealImageDp());
                                        dataToSave.put("description", desc);
                                        dataToSave.put("ChildApproxAge", approxAge);
                                        dataToSave.put("gender", male);
                                        dataToSave.put("picProof", downloadUrl.toString());
                                        if (addChildLabourSexualAbuse.isChecked())
                                            dataToSave.put("SexualAbuse", "Yes");
                                        else
                                            dataToSave.put("SexualAbuse", "No");

                                        if (addChildLabourPhysicalAbuse.isSelected())
                                            dataToSave.put("PhysicalAbuse", "Yes");
                                        else
                                            dataToSave.put("PhysicalAbuse", "No");

                                        if (addChildLabourPsychologicalAbuse.isChecked())
                                            dataToSave.put("psycologicalAbuse", "Yes");
                                        else
                                            dataToSave.put("psycologicalAbuse", "No");

                                        if (addChildLabourAbandon.isChecked())
                                            dataToSave.put("ChildLabour", "Yes");
                                        else
                                            dataToSave.put("ChildLabour", "No");

                                        if (addChildLabourChildMarriage.isChecked())
                                            dataToSave.put("ChildMarriage", "Yes");
                                        else
                                            dataToSave.put("ChildMarriage", "No");

                                        if (addChildLabourRadioMale.isSelected())
                                            dataToSave.put("gender", "Male");
                                        else if (addChildLabourRadioFemale.isSelected())
                                            dataToSave.put("gender", "Female");


                                        dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                                        newChildLabourAppeal.setValue(dataToSave);

                                        startActivity(new Intent(AddChildLabourAppealActivity.this, MainNavigationActivity.class));
                                        finish();


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            } else {




                            DatabaseReference newChildLabourAppeal = mDatabaseReference.push();
                            Map<String, String> dataToSave = new HashMap<>();

                            dataToSave.put("appealFirstName", userDetail.getAppealFirstName());
                            dataToSave.put("appealLastName", userDetail.getAppealLastName());
                            dataToSave.put("appealImageDp", userDetail.getAppealImageDp());
                            dataToSave.put("description", desc);
//                            dataToSave.put("PhysicalAbuse" , physicalStr);
//                            dataToSave.put("SexualAbuse" ,sexualStr);
//                            dataToSave.put("PsychologicalAbuse" , psycoStr);
//                            dataToSave.put("Abandon" ,abandonStr);
//                            dataToSave.put("childLabour" , childLabourStr);
//                            dataToSave.put("ChildMarriage" , childMarriageStr);
                            dataToSave.put("ChildApproxAge", approxAge);
                            dataToSave.put("gender", male);
                            dataToSave.put("picProof", downloadUrl.toString());
                            if (addChildLabourSexualAbuse.isChecked())
                                dataToSave.put("SexualAbuse", "Yes");
                            else
                                dataToSave.put("SexualAbuse", "No");

                            if (addChildLabourPhysicalAbuse.isSelected())
                                dataToSave.put("PhysicalAbuse", "Yes");
                            else
                                dataToSave.put("PhysicalAbuse", "No");

                            if (addChildLabourPsychologicalAbuse.isChecked())
                                dataToSave.put("psycologicalAbuse", "Yes");
                            else
                                dataToSave.put("psycologicalAbuse", "No");

                            if (addChildLabourAbandon.isChecked())
                                dataToSave.put("ChildLabour", "Yes");
                            else
                                dataToSave.put("ChildLabour", "No");

                            if (addChildLabourChildMarriage.isChecked())
                                dataToSave.put("ChildMarriage", "Yes");
                            else
                                dataToSave.put("ChildMarriage", "No");

                            if (addChildLabourRadioMale.isSelected())
                                dataToSave.put("gender", "Male");
                            else if (addChildLabourRadioFemale.isSelected())
                                dataToSave.put("gender", "Female");


                            dataToSave.put("timestamp", String.valueOf(java.lang.System.currentTimeMillis()));
                            newChildLabourAppeal.setValue(dataToSave);

                            startActivity(new Intent(AddChildLabourAppealActivity.this, MainNavigationActivity.class));
                            finish();
                        }
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
                addChildLabourPicProofImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }
}
