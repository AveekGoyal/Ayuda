package com.example.admin.ayuda.Activity.Login;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.ayuda.Model.Ngo_Appeals;
import com.example.admin.ayuda.Model.OrgName;
import com.example.admin.ayuda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
import com.tapadoo.alerter.Alerter;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MemberRegistrationActivity extends AppCompatActivity{

    private ImageButton memRegDpImageButton;
    private EditText memRegFirstNameTextBox;
    private EditText memRegLastNameTextBox;
    private RadioButton memRegRadioMale;
    private RadioButton memRegRadioFemale;
    private  EditText memRegEmailTextBox;
    private  EditText memRegPasswordTextBox;
    private  EditText memRegConfirmPassTextBox;
    private  EditText memRegMobileNoTextBox;
    private Spinner memRegOrgNameSpinner;
    private EditText memRegMemberIdTextBox;
    private EditText memRegAddressTextBox;
    private  Spinner memRegCitySpinner;
    private Spinner memRegStateSpinner;
    private EditText memRegPincodeTextBox;
    private Button memRegSubmitButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private StorageReference mStorageReference;
    private RadioGroup memberRadioGrGroup;
    private Uri resultUri= null;
    private String male= " ";
    private String female= " ";
    private final static int GALLERY_CODE = 1;
    public List<String> orgNameList;
    public String orgNameSelected= " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_registration_activity);

        memRegDpImageButton = findViewById(R.id.MemRegDpImageButton);
        memRegFirstNameTextBox= findViewById(R.id.MemRegFirstNameTextBox);
        memRegLastNameTextBox = findViewById(R.id.MemRegLastNameTextBox);
        memRegRadioMale = findViewById(R.id.MemRegRadioMale);
        memRegRadioFemale = findViewById(R.id.MemRegRadioFemale);
        memRegEmailTextBox = findViewById(R.id.MemRegEmailTextBox);
        memRegPasswordTextBox =findViewById(R.id.MemRegPasswordTextBox);
        memRegConfirmPassTextBox = findViewById(R.id.MemRegConfirmPassTextBox);
        memRegMobileNoTextBox =findViewById(R.id.MemRegMobileNoTextBox);
        memRegOrgNameSpinner = findViewById(R.id.MemRegOrgNameSpinner);
        memRegMemberIdTextBox =findViewById(R.id.MemRegMemberIdTextBox);
        memRegAddressTextBox = findViewById(R.id.MemRegAddressTextBox);
        memRegCitySpinner = findViewById(R.id.MemRegCitySpinner);
        memRegStateSpinner = findViewById(R.id.MemRegStateSpinner);
        memRegSubmitButton = findViewById(R.id.MemRegSubmitButton);
        memRegPincodeTextBox = findViewById(R.id.MemRegPincodeTextBox);
        memberRadioGrGroup = findViewById(R.id.MemberRadioGrGroup);



        mDatabase =FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Member");
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference().child("Member_Pics");


        memRegDpImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });

        memRegSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMemberAccount();
            }
        });

        memberRadioGrGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i)
                {
                    case R.id.MemRegRadioMale:
                        male = "Male";
                        break;

                    case R.id.MemRegRadioFemale:
                        female = "Female";
                        break;
                }
            }
        });

        //Add VAlues in Spinner
        String[] cities = {"Panipat" , "Meerut" , "Pune" , "Saharanpur"};
        ArrayAdapter<String> adaptercity = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , cities);
        memRegCitySpinner.setAdapter(adaptercity);

        String[] states = {"Haryana" , "Uttar Pradesh" , "Punjab" , "J&K"};
        ArrayAdapter<String> adapterstate = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , states);
        memRegStateSpinner.setAdapter(adapterstate);

        String[] org = {"Helping Hands","Helping Ngo" , "Helping People" , "new world"};
        ArrayAdapter<String> adapterorg = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , org);
        memRegOrgNameSpinner.setAdapter(adapterorg);

        orgNameList = new ArrayList<>();
        mDatabase.getReference().child("orgName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                orgNameList.clear();
                while(items.hasNext()){
                    DataSnapshot item = items.next();
                    String name = item.child("orgName").getValue(String.class);
                    orgNameList.add(name);
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        ArrayAdapter<OrgName> adapter =
//                new ArrayAdapter<OrgName>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, orgNameList);
//        memRegOrgNameSpinner.setAdapter(adapter);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, orgNameList);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        memRegOrgNameSpinner.setAdapter(dataAdapter);



    }

    private void createMemberAccount() {
        new MaterialDialog.Builder(this)
                .title("Creating Account")
                .content("Please Wait")
                .progress(true, 0)
                .show();

        final String firstName = memRegFirstNameTextBox.getText().toString().trim();
        final String lastName = memRegLastNameTextBox.getText().toString().trim();
        final String email = memRegEmailTextBox.getText().toString().trim();
        final String pwd = memRegPasswordTextBox.getText().toString().trim();
        final String finalPwd = memRegConfirmPassTextBox.getText().toString().trim();
        final String number = memRegMobileNoTextBox.getText().toString().trim();
        final String memId = memRegMemberIdTextBox.getText().toString().trim();
        final String address = memRegAddressTextBox.getText().toString().trim();
        final String pincode = memRegPincodeTextBox.getText().toString().trim();
        final String city = memRegCitySpinner.getSelectedItem().toString().trim();
        final String state = memRegStateSpinner.getSelectedItem().toString().trim();
        final String org = memRegOrgNameSpinner.getSelectedItem().toString().trim();

        if (pwd.length() < 6)
        {
            Toast.makeText(getApplicationContext(), "Please enter a password with atleast 6 characters", Toast.LENGTH_LONG).show();
        }
        else if (!pwd.equals(finalPwd))
        {
            Toast.makeText(getApplicationContext(),"Passwords does not match", Toast.LENGTH_LONG).show();

        }
        else if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) &&
        !TextUtils.isEmpty(number) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(pincode)
        && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(state) && !TextUtils.isEmpty(orgNameSelected))
        {

          mAuth.createUserWithEmailAndPassword(email , pwd).addOnCompleteListener(MemberRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  StorageReference imagePath = mStorageReference.child("Member_Pics")
                          .child(resultUri.getLastPathSegment());
                  imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                      @Override
                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          String userId = mAuth.getCurrentUser().getUid();
                          Uri downloadUrl = taskSnapshot.getDownloadUrl();

                          DatabaseReference currentUserDb = mDatabaseReference.child(userId);
                          currentUserDb.child("firstName").setValue(firstName);
                          currentUserDb.child("lastName").setValue(lastName);
                          currentUserDb.child("email").setValue(email);
                          currentUserDb.child("MobileNumber").setValue(number);
                          currentUserDb.child("OrgName").setValue(org);
                          currentUserDb.child("MemberId").setValue(memId);
                          currentUserDb.child("Address").setValue(address);
                          currentUserDb.child("City").setValue(city);
                          currentUserDb.child("State").setValue(state);
                          currentUserDb.child("Pincode").setValue(pincode);
                          if(male.equals("Male"))
                          {
                              currentUserDb.child("Gender").setValue(male);
                          }
                          else
                          {
                              currentUserDb.child("Gender").setValue(female);
                          }

                          currentUserDb.child("imageDp").setValue(downloadUrl.toString());
                          currentUserDb.child("type").setValue("Member");
                          Toasty.success(getApplicationContext(), "Account Created Successfully!", Toast.LENGTH_SHORT, true).show();                          FirebaseUser user = mAuth.getCurrentUser();
                          user.sendEmailVerification().addOnCompleteListener(MemberRegistrationActivity.this, new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  if (task.isSuccessful())
                                  {
                                      Toasty.info(getApplicationContext(), "Verification email sent Successfully", Toast.LENGTH_SHORT, true).show();


                                  }
                                  else
                                  {

                                      Toasty.error(getApplicationContext(), "Verification email failed. Please check your email", Toast.LENGTH_SHORT, true).show();
                                  }
                              }
                          });

                          Intent intent = new Intent(MemberRegistrationActivity.this, MainActivity.class );
                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                          startActivity(intent);

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

                memRegDpImageButton.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
