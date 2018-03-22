package com.example.admin.ayuda.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton profileActivityDpImageButton;
    private EditText profileNameEditText;
    private EditText profileContactNoEditText;
    private EditText profileEmailIdEditText;
    private EditText profileLocationEditText;
    private Button profileEditButton;
    private Button profileSubmitButton;
    private static final int GALLERY_CODE =1;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private StorageReference storage;
    private FirebaseAuth auth;
    private Uri mImageUri;
    private Uri resultUri;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private String type = " ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        profileActivityDpImageButton = findViewById(R.id.ProfileActivityDpImageButton);
        profileNameEditText = findViewById(R.id.ProfileNameEditText);
        profileContactNoEditText = findViewById(R.id.ProfileContactNoEditText);
        profileEmailIdEditText = findViewById(R.id.ProfileEmailIdEditText);
        profileLocationEditText = findViewById(R.id.ProfileLocationEditText);
        profileEditButton = findViewById(R.id.ProfileEditButton);
        profileSubmitButton = findViewById(R.id.ProfileSubmitButton);


//        profileActivityDpImageButton.setEnabled(false);
//        profileNameEditText.setEnabled(false);
//        profileContactNoEditText.setEnabled(false);
//        profileEmailIdEditText.setEnabled(false);
//        profileLocationEditText.setEnabled(false);
//        profileSubmitButton.setEnabled(false);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database =FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance().getReference();
        final String userId = user.getUid();
        DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child(userId);
        getType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                type = dataSnapshot.child("type").getValue(String.class);
                if(type.equals("NgoAdmin")){
                    String imageUrl = getIntent().getStringExtra("imageDp");
                    Picasso.with(getApplicationContext()).load(imageUrl).into(profileActivityDpImageButton);
                    profileNameEditText.setText(String.format("Organisation:" , getIntent().getStringExtra("orgName")));
                    profileContactNoEditText.setText(String.format("Contact Number:" , getIntent().getStringExtra("mobileNumber")));
                    profileEmailIdEditText.setText(String.format("Email ID:" ,getIntent().getStringExtra("email")));
                    profileLocationEditText.setText("Location is not available in this Version on Ayuda.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        StorageReference filePath;

        if(type.equals("NonMember")){
            filePath = storage.child("NonMember_Pics").child(resultUri.getLastPathSegment());
        }
        else if(type.equals("NonAdmin"))
        {
            filePath = storage.child("Member_Pics").child(resultUri.getLastPathSegment());

        }
        else
        {
            filePath = storage.child("NgoAdmin_Pics").child(resultUri.getLastPathSegment());

        }

        filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                DatabaseReference profileEdit = databaseReference.push();
                Map<String, String> dataToSave = new HashMap<>();

                dataToSave.put("imageDp" , downloadUrl.toString());
                profileEdit.setValue(dataToSave);

            }
        });


        //Code Goes Here
        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                profileActivityDpImageButton.setEnabled(true);
//                profileNameEditText.setEnabled(true);
//                profileContactNoEditText.setEnabled(true);
//                profileEmailIdEditText.setEnabled(true);
//                profileLocationEditText.setEnabled(false);
//                profileSubmitButton.setEnabled(true);
//                profileEditButton.setEnabled(false);
            }
        });

        profileSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = profileNameEditText.getText().toString().trim();
                final String contactNumber = profileContactNoEditText.getText().toString().trim();
                final String emailId = profileEmailIdEditText.getText().toString().trim();

                DatabaseReference editProfile = databaseReference.push();
                Map<String , String> dataToSave = new HashMap<>();

                dataToSave.put("orgName" , name);
                dataToSave.put("mobileNumber" , contactNumber);
                dataToSave.put("email" , emailId);
                editProfile.setValue(dataToSave);
                startActivity(new Intent(ProfileActivity.this , ProfileActivity.class));
                finish();
            }
        });

        profileActivityDpImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });
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
                profileActivityDpImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }
}
