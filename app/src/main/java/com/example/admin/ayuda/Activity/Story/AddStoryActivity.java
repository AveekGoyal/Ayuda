package com.example.admin.ayuda.Activity.Story;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

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

public class AddStoryActivity extends AppCompatActivity {

    private ImageButton addstoryPictureUploadImageButton;
    private EditText addStoryCaptionTextBox;
    private Button addStorySubmitButton;
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
        setContentView(R.layout.add_story_activity);

        addstoryPictureUploadImageButton = findViewById(R.id.AddstoryPictureUploadImageButton);
        addStoryCaptionTextBox = findViewById(R.id.AddStoryCaptionTextBox);
        addStorySubmitButton = findViewById(R.id.AddStorySubmitButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId=mUser.getUid();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Story");

        addstoryPictureUploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });

        addStorySubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStory();
            }
        });


    }


    private void addStory()
    {
        final String storyTitle = addStoryCaptionTextBox.getText().toString().trim();
        if(!TextUtils.isEmpty(storyTitle))
        {
            StorageReference filePath = mStorage.child("Story_Pics").child(resultUri.getLastPathSegment());
            filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    DatabaseReference getUserType = FirebaseDatabase.getInstance().getReference().child("NonMember");

                    getUserType.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String fName = dataSnapshot.child(userId).child("firstName").getValue(String.class);
                            String lname = dataSnapshot.child(userId).child("lastName").getValue(String.class);
                            String imageUrl = dataSnapshot.child(userId).child("imageDp").getValue(String.class);

                            final NonMember userDetail = new NonMember(fName, lname, imageUrl);
                            if (fName == null && lname == null && imageUrl == null) {
                                DatabaseReference getMemberDetails = FirebaseDatabase.getInstance().getReference().child("Member");
                                getMemberDetails.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String fName = dataSnapshot.child(userId).child("firstName").getValue(String.class);
                                        String lname = dataSnapshot.child(userId).child("lastName").getValue(String.class);
                                        String imageUrl = dataSnapshot.child(userId).child("imageDp").getValue(String.class);
                                        Members memberDetails = new Members(fName, lname, imageUrl);

                                        DatabaseReference newStory = mDatabaseReference.push();
                                        Map<String, String> dataToSave = new HashMap<>();

                                        dataToSave.put("statusImage" , downloadUrl.toString());
                                        dataToSave.put("caption" , storyTitle);
                                        dataToSave.put("firstName" , userDetail.getAppealFirstName());
                                        dataToSave.put("lastName" , userDetail.getAppealLastName());
                                        dataToSave.put("imageDp" , userDetail.getAppealImageDp());

                                        newStory.setValue(dataToSave);
                                        startActivity(new Intent(AddStoryActivity.this , MainNavigationActivity.class));
                                        finish();

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                            else
                            {
                                DatabaseReference newStory = mDatabaseReference.push();
                                Map<String, String> dataToSave = new HashMap<>();

                                dataToSave.put("statusImage" , downloadUrl.toString());
                                dataToSave.put("caption" , storyTitle);
                                dataToSave.put("firstName" , userDetail.getAppealFirstName());
                                dataToSave.put("lastName" , userDetail.getAppealLastName());
                                dataToSave.put("imageDp" , userDetail.getAppealImageDp());

                                newStory.setValue(dataToSave);
                                startActivity(new Intent(AddStoryActivity.this , MainNavigationActivity.class));
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
                addstoryPictureUploadImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }
}
