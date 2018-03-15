package com.example.admin.ayuda.Activity.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.admin.ayuda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class NonMemberRegistrationActivity extends AppCompatActivity {

    private ImageButton nonMemRegDpImageButton;
    private EditText nonMemRegFirstNameTextBox;
    private EditText nonMemRegLastNameTextBox;
    private RadioButton nonMemRegRadioMale;
    private EditText nonMemRegContactNoTextBox;
    private  RadioButton nonMemRegRadioFemale;
    private  EditText nonMemRegEmailTextBox;
    private  EditText nonMemRegPasswordTextBox;
    private  EditText nonMemRegConfirmPassTextBox;
    private Button nonMemberSubmitButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private StorageReference mStorageReference;
    private Uri resultUri= null;
    private String male;
    private RadioGroup nonMemberRadioButtonGroup;
    private String female;
    private final static int GALLERY_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_member_registration_activity);

        //Variable used in this java file
        nonMemRegDpImageButton = findViewById(R.id.NonMemRegDpImageButton);
        nonMemberRadioButtonGroup = findViewById(R.id.NonMemberRadioButtonGroup);
        nonMemRegFirstNameTextBox = findViewById(R.id.NonMemRegFirstNameTextBox);
        nonMemRegLastNameTextBox = findViewById(R.id.NonMemRegLastNameTextBox);
        nonMemRegRadioMale = findViewById(R.id.NonMemRegRadioMale);
        nonMemRegRadioFemale = findViewById(R.id.NonMemRegRadioFemale);
        nonMemRegContactNoTextBox = findViewById(R.id.NonMemRegContactNoTextBox);
        nonMemRegEmailTextBox = findViewById(R.id.NonMemRegEmailTextBox);
        nonMemRegPasswordTextBox = findViewById(R.id.NonMemRegPasswordTextBox);
        nonMemRegConfirmPassTextBox = findViewById(R.id.NonMemRegConfirmPassTextBox);
        nonMemberSubmitButton = findViewById(R.id.NonMemberSubmitButton);
        
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("NonMember");
        mAuth =FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference().child("NonMember_Pics");
        
        
        nonMemRegDpImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });


        nonMemberSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNonMemberAccount();
            }
        });

        nonMemberRadioButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){

                    case R.id.NonMemRegRadioMale :
                        male = "Male";
                        break;
                    case  R.id.NonMemRegRadioFemale:
                        female = "Female";

                }
            }
        });
        
    }

    private void createNonMemberAccount() {

        final String firstName = nonMemRegFirstNameTextBox.getText().toString().trim();
        final String lastName = nonMemRegLastNameTextBox.getText().toString().trim();
        final String contactNumber = nonMemRegContactNoTextBox.getText().toString().trim();
        final String emailId = nonMemRegEmailTextBox.getText().toString().trim();
        final String pwd = nonMemRegPasswordTextBox.getText().toString().trim();
        final String finalPwd = nonMemRegConfirmPassTextBox.getText().toString().trim();

        if(pwd.length() < 6){
            Toast.makeText(getApplicationContext() ,"Please enter a password with at least 6 characters", Toast.LENGTH_LONG).show();
        }
        else if (!pwd.equals(finalPwd))
        {
            Toast.makeText(getApplicationContext(),"Passwords does not match", Toast.LENGTH_LONG).show();

        }
        else if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(contactNumber)
                && !TextUtils.isEmpty(emailId))
        {

            mAuth.createUserWithEmailAndPassword(emailId , pwd).addOnCompleteListener(NonMemberRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        StorageReference imagePath = mStorageReference.child("NonMember_Pics").
                                child(resultUri.getLastPathSegment());
                        imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String userId = mAuth.getCurrentUser().getUid();
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                                DatabaseReference currentUserDb = mDatabaseReference.child(userId);
                                currentUserDb.child("firstName").setValue(firstName);
                                currentUserDb.child("lastName").setValue(lastName);
                                currentUserDb.child("contactNumber").setValue(contactNumber);
                                currentUserDb.child("email").setValue(emailId);
                                if (male.equals("Male")) {
                                    currentUserDb.child("Gender").setValue(male);
                                } else {
                                    currentUserDb.child("Gender").setValue(female);
                                }
                                currentUserDb.child("imageDp").setValue(downloadUrl.toString());
                                Toast.makeText(getApplicationContext(), "Account Created Succesfuly", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification().addOnCompleteListener(NonMemberRegistrationActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Verification Email Sent", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                Intent intent = new Intent(NonMemberRegistrationActivity.this, MainActivity.class );
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }

                        });

                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            Uri mImageUri = data.getData();

            CropImage.activity(mImageUri)
                    .setAspectRatio(1, 1)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(NonMemberRegistrationActivity.this);


        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();

                nonMemRegDpImageButton.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}