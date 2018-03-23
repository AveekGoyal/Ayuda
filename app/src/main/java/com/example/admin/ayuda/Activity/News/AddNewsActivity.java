package com.example.admin.ayuda.Activity.News;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.VideoView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Model.NgoAdmin;
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


public class AddNewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton addNewsPicProofImageButton;
    private Spinner addNewsChooseCategorySpinner;
    private EditText addNewsHeadlineTextBox;
    private EditText addNewsDescriptionTextBox;
    private Button addNewsUploadButton;
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
        setContentView(R.layout.add_news_activity);

        // Joining Variables with their Ids

        addNewsPicProofImageButton = findViewById(R.id.AddNewsPicProofImageButton);
        addNewsChooseCategorySpinner = findViewById(R.id.AddNewsChooseCategorySpinner);
        addNewsHeadlineTextBox = findViewById(R.id.AddNewsHeadlineTextBox);
        addNewsDescriptionTextBox = findViewById(R.id.AddNewsDescriptionTextBox);
        addNewsUploadButton = findViewById(R.id.AddNewsUploadButton);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId = mUser.getUid();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("News");

        addNewsPicProofImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });

        addNewsUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNews();
            }
        });

    }

    private void addNews() {
        new MaterialDialog.Builder(this)
                .title("Uploading News")
                .content("Please Wait")
                .progress(true, 0)
                .show();


        final String newsHeadline = addNewsHeadlineTextBox.getText().toString().trim();
        final String newsDescription = addNewsDescriptionTextBox.getText().toString().trim();

        if (!TextUtils.isEmpty(newsHeadline) && !TextUtils.isEmpty(newsDescription)) {
            StorageReference filePath = mStorage.child("News_Images").child(resultUri.getLastPathSegment());
            filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference getUserType = FirebaseDatabase.getInstance().getReference().child("NgoAdmin");
                    getUserType.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String orgName = dataSnapshot.child(userId).child("orgName").getValue(String.class);
                            String imageUrl = dataSnapshot.child(userId).child("imageDp").getValue(String.class);
                            NgoAdmin userDetails = new NgoAdmin(orgName, imageUrl);

                            DatabaseReference newNews = mDatabaseReference.push();
                            Map<String, String> dataToSave = new HashMap<>();
                            dataToSave.put("newsOrgName", userDetails.getOrgName());
                            dataToSave.put("newsOrgDp", userDetails.getImageDp());
                            dataToSave.put("newsHeadline", newsHeadline);
                            dataToSave.put("newsDescription", newsDescription);

                            dataToSave.put("picProof", downloadUrl.toString());
                            newNews.setValue(dataToSave);
                            startActivity(new Intent(AddNewsActivity.this, MainNavigationActivity.class));
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
                addNewsPicProofImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
