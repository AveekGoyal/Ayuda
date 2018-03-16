package com.example.admin.ayuda.Activity.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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


public class NgoRegistrationActivity extends AppCompatActivity {


    private ImageButton ngoRegImageButton;
    private EditText ngoRegOrgName;
    private EditText ngoRegHeadName;
    private EditText ngoRegPanNumber;
    private RadioGroup ngoRegRadioGroup;
    private EditText ngoRegEmail;
    private EditText ngoRegPassword;
    private EditText ngoRegConfirmPass;
    private EditText ngoRegMobileNumber;
    private EditText ngoRegWebsiteLink;
    private EditText ngoRegAddress;
    private EditText ngoRegPinCode;
    private Spinner ngoRegStateSpinner;
    private Spinner ngoRegCitySpinner;
    private Button ngoRegSubmitButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private StorageReference mStorageReference;
    private Uri resultUri= null;
    private String male =" ";
    private String female=" ";
    private final static int GALLERY_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngo_registration_activity);

        ngoRegImageButton = findViewById(R.id.NgoRegDpImageButton);
        ngoRegOrgName = findViewById(R.id.NgoRegNameTextBox);
        ngoRegHeadName= findViewById(R.id.NgoRegHeadNameTextBox);
        ngoRegPanNumber = findViewById(R.id.NgoRegPanNoTextBox);
        ngoRegRadioGroup = findViewById(R.id.ngoRegRadioGroup);
        ngoRegEmail = findViewById(R.id.NgoRegEmailTextBox);
        ngoRegPassword = findViewById(R.id.NgoRegPasswordTextBox);
        ngoRegConfirmPass = findViewById(R.id.NgoRegConfirmPassTextBox);
        ngoRegMobileNumber = findViewById(R.id.NgoRegMobileNoTextBox);
        ngoRegWebsiteLink = findViewById(R.id.NgoRegWebsiteLinkTextBox);
        ngoRegAddress = findViewById(R.id.NgoRegAddressTextBox);
        ngoRegPinCode = findViewById(R.id.NgoRegPincodeTextBox);
        ngoRegStateSpinner= findViewById(R.id.NgoRegStateSpinner);
        ngoRegCitySpinner = findViewById(R.id.NgoRegCitySpinner);
        ngoRegSubmitButton = findViewById(R.id.NgoRegSubmitButton);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference= mDatabase.getReference().child("NgoAdmin");
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference().child("NgoAdmin_Pics");


        ngoRegImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });

        ngoRegSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNgoAdminAccount();
            }
        });

        ngoRegRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.NgoRegRadioMale:
                        male = "Male";
                        break;
                    case R.id.NgoRegRadioFemale:
                        female="Female";
                }
            }
        });







    }

    private void createNgoAdminAccount() {

        final String orgName = ngoRegOrgName.getText().toString().trim();
        final String headName= ngoRegHeadName.getText().toString().trim();
        final String password= ngoRegPassword.getText().toString().trim();
        final String adminEmail = ngoRegEmail.getText().toString().trim();
        final String panNumber = ngoRegPanNumber.getText().toString().trim();
        final String confpass= ngoRegConfirmPass.getText().toString().trim();
        final String mobileNo = ngoRegMobileNumber.getText().toString().trim();
        final String webLink =ngoRegWebsiteLink.getText().toString().trim();
        final String ngoAddress = ngoRegAddress.getText().toString().trim();
        final String pinCode =ngoRegPinCode.getText().toString().trim();


        if (password.length() < 6)
        {
            Toast.makeText(getApplicationContext(), "Please enter a password with atleast 6 characters", Toast.LENGTH_LONG).show();
        }
        else if (!password.equals(confpass))
        {
            Toast.makeText(getApplicationContext(),"Passwords doesnot match", Toast.LENGTH_LONG).show();

        }

        else if (!TextUtils.isEmpty(orgName)
                && !TextUtils.isEmpty(headName) && !TextUtils.isEmpty(adminEmail) && !TextUtils.isEmpty(panNumber)
                && !TextUtils.isEmpty(mobileNo) && !TextUtils.isEmpty(ngoAddress))
        {
            mAuth.createUserWithEmailAndPassword(adminEmail,password).addOnCompleteListener(NgoRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        StorageReference imagePath = mStorageReference.child("NgoAdmin_Pics")
                                .child(resultUri.getLastPathSegment());
                        imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String userId = mAuth.getCurrentUser().getUid();
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                                DatabaseReference currentUserDb = mDatabaseReference.child(userId);
                                currentUserDb.child("orgName").setValue(orgName);
                                currentUserDb.child("headName").setValue(headName);
                                currentUserDb.child("panNumber").setValue(panNumber);
                                if (male.equals("Male"))
                                {
                                    currentUserDb.child("gender").setValue(male);
                                }
                                else if(female.equals("Female"))
                                {
                                    currentUserDb.child("gender").setValue(female);
                                }
                                currentUserDb.child("email").setValue(adminEmail);
                                currentUserDb.child("mobileNumber").setValue(mobileNo);
                                currentUserDb.child("websiteLink").setValue(webLink);
                                currentUserDb.child("ngoAddress").setValue(ngoAddress);
                                currentUserDb.child("ngoPinCode").setValue(pinCode);
                                currentUserDb.child("type").setValue("NgoAdmin");
                                currentUserDb.child("imageDp").setValue(downloadUrl.toString());
                                Toast.makeText(getApplicationContext(), " Account Created Successfully", Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification().addOnCompleteListener(NgoRegistrationActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(getApplicationContext(), "Verification Email Sent", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                Intent intent = new Intent(NgoRegistrationActivity.this, MainActivity.class );
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
                    .start(this);


        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();

               ngoRegImageButton.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}

