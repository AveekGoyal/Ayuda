package com.example.admin.ayuda.Activity.Event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class AddEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button addEventStartDatePicker;
    Calendar myCalendar = Calendar.getInstance();
    private  Button addEventEndDatePicker;
    private  Button addEventStartTimePicker;
    private Button addEventEndTimePicker;
    private ImageView addEventPicProofImageButton;
    private Spinner addEventChooseCategorySpinner;
    private EditText addEventDescriptionTextBox;
    private EditText addEventTitleTextBox;
    private RadioGroup addEventVolunteerRadioGroup;
    private RadioGroup addEventSponsorRadioGroup;
    private TextView addEventStartDate;
    private TextView addEventEndDate;
    private TextView addEventStartTime;
    private TextView addEventEndTime;
    private RadioButton addEventVolunteerRadioYes;
    private RadioButton addEventVolunteerRadioNo;
    private RadioButton addEventSponsorRadioYes;
    private  RadioButton AddEventSponsorRadioNo;
    private Button addEventSubmitButton;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private StorageReference mStorage;
    private static final int GALLERY_CODE =1;
    private Uri mImageUri;
    private Uri resultUri;
    private String userId;
    String categorySelected =" ";
    String volunteerRequired = "No";
    String sponsorRequired= "No";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);

        //Joining Variables with their Ids
         addEventStartDatePicker = findViewById(R.id.AddEventStartDatePicker);
         addEventEndDatePicker =  findViewById(R.id.AddEventEndDatePicker);
        addEventStartTimePicker =  findViewById(R.id.AddEventStartTimePicker);
        addEventEndTimePicker =  findViewById(R.id.AddEventEndTimePicker);
        addEventPicProofImageButton = findViewById(R.id.AddEventPicProofImageButton);
        addEventChooseCategorySpinner = findViewById(R.id.AddEventChooseCategorySpinner);
        addEventTitleTextBox = findViewById(R.id.AddEventTitleTextBox);
        addEventDescriptionTextBox = findViewById(R.id.AddEventDescriptionTextBox);
        addEventVolunteerRadioYes = findViewById(R.id.AddEventVolunteerRadioYes);
        addEventVolunteerRadioNo = findViewById(R.id.AddEventVolunteerRadioNo);
        addEventSponsorRadioYes = findViewById(R.id.AddEventSponsorRadioYes);
        addEventSponsorRadioYes = findViewById(R.id.AddEventSponsorRadioNo);
        addEventSubmitButton = findViewById(R.id.AddEventSubmitButton);
        addEventEndDate = findViewById(R.id.AddEventEndDate);
        addEventEndTime = findViewById(R.id.AddEventEndTime);
        addEventStartDate = findViewById(R.id.AddEventStartDate);
        addEventStartTime= findViewById(R.id.AddEventStartTime);
        addEventSponsorRadioGroup = findViewById(R.id.SponsorsRadioGroup);
        addEventVolunteerRadioGroup = findViewById(R.id.VolunteersRadioGroup);



        /*
        Functions to use Date Pick Button
         */



        addEventEndDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurrentDate = Calendar.getInstance();
                int year = mCurrentDate.get(Calendar.YEAR);
                int month = mCurrentDate.get(Calendar.MONTH);
                int dayOfMonth = mCurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endDate = String.format("%s / %s / %s", dayOfMonth,month,year);
                        addEventEndDate.setText(String.format("%s / %s / %s", dayOfMonth,month,year));
                    }
                }, year,month,dayOfMonth);
                mDatePicker.setTitle("Select End Date");
                mDatePicker.show();
            }
        });

        addEventStartDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Calendar mCurrentDate = Calendar.getInstance();
               int year = mCurrentDate.get(Calendar.YEAR);
               int month = mCurrentDate.get(Calendar.MONTH);
               int dayOfMonth = mCurrentDate.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog mDatePicker = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                      startDate =String.format("%s / %s / %s", dayOfMonth,month,year);
                       addEventStartDate.setText(String.format("%s / %s / %s", dayOfMonth,month,year));
                   }
               }, year,month,dayOfMonth);
               mDatePicker.setTitle("Select Start Date");
               mDatePicker.show();
            }
        });

        /*
        Date Pick ENds Here
         */

        /*
        Time Pick Starts Here
         */

        addEventStartTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //Toast.makeText(AddEventActivity.this , ( selectedHour + ":" + selectedMinute) , Toast.LENGTH_LONG).show();

                        if (selectedHour >= 12 && selectedHour <= 24)
                        {
                            startTime =String.format("%s : %s PM", selectedHour, selectedMinute);
                            addEventStartTime.setText(String.format("%s : %s PM", selectedHour, selectedMinute));
                        }
                        else
                        {
                            startTime =String.format("%s : %s PM", selectedHour, selectedMinute);
                            addEventStartTime.setText(String.format("%s : %s AM", selectedHour, selectedMinute));
                        }                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        addEventEndTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //Toast.makeText(AddEventActivity.this , ( selectedHour + ":" + selectedMinute) , Toast.LENGTH_LONG).show();
                        if (selectedHour >= 12 && selectedHour <= 24)
                        {
                            endTime =String.format("%s : %s PM", selectedHour, selectedMinute);
                            addEventEndTime.setText(String.format("%s : %s PM", selectedHour, selectedMinute));
                        }
                        else
                        {
                            endTime =String.format("%s : %s PM", selectedHour, selectedMinute);
                            addEventEndTime.setText(String.format("%s : %s AM", selectedHour, selectedMinute));
                        }

                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        /*
        Time Pick Ends Here
         */


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId=mUser.getUid();
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Events");

        addEventChooseCategorySpinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Animal Charities");
        categories.add("Environmental Charities");
        categories.add("Health Charities");
        categories.add("Education Charities");
        categories.add("Arts and Culture Charities");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddEventActivity.this,android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addEventChooseCategorySpinner.setAdapter(dataAdapter);


        addEventPicProofImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });

        addEventVolunteerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.AddEventVolunteerRadioYes:
                        volunteerRequired = "Yes";
                        break;
                    case R.id.AddEventVolunteerRadioNo:
                        volunteerRequired = "No";
                }
            }
        });

        addEventSponsorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.AddEventSponsorRadioYes:
                        sponsorRequired ="Yes";
                        break;
                    case R.id.AddEventSponsorRadioNo:
                        sponsorRequired = "Yes";
                        break;
                }
            }
        });

        addEventSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });


    }

    private void addEvent() {
       final String eventTitle = addEventTitleTextBox.getText().toString().trim();
       final String eventDescription = addEventDescriptionTextBox.getText().toString().trim();

       if (!TextUtils.isEmpty(eventTitle) && !TextUtils.isEmpty(eventDescription))
       {
           StorageReference filePath = mStorage.child("Event_Images").child(resultUri.getLastPathSegment());
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
                           NgoAdmin userDetails = new NgoAdmin(orgName,imageUrl);

                           DatabaseReference newEvent = mDatabaseReference.push();
                           Map<String, String> dataToSave = new HashMap<>();


                           dataToSave.put("eventOrgName",userDetails.getOrgName());
                           dataToSave.put("eventOrgDp", userDetails.getImageDp());
                           dataToSave.put("eventTitle", eventTitle);
                           dataToSave.put("eventDescription", eventDescription);
                           dataToSave.put("eventStartTime", startTime);
                           dataToSave.put("eventEndTime", endTime);
                           dataToSave.put("eventStartDate", startDate);
                           dataToSave.put("eventEndDate", endDate);
                           dataToSave.put("eventType", categorySelected);
                           if (volunteerRequired.equals("Yes"))
                           {
                               dataToSave.put("volunteerRequired", volunteerRequired);
                           }
                           else
                           {
                               dataToSave.put("volunteerRequired",volunteerRequired);
                           }
                           if (sponsorRequired.equals("Yes"))
                           {
                               dataToSave.put("sponsorRequired", sponsorRequired);
                           }
                           else
                           {
                               dataToSave.put("sponsorRequired",sponsorRequired);
                           }
                           dataToSave.put("picProof", downloadUrl.toString());

                           newEvent.setValue(dataToSave);
                           startActivity(new Intent(AddEventActivity.this, MainNavigationActivity.class));
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
                addEventPicProofImageButton.setImageURI(resultUri);
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categorySelected =  parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
