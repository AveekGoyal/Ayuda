package com.example.admin.ayuda.Activity.Event;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class EventDetailsActivity extends AppCompatActivity {

    private Spinner eventDetailGoingSpinner;
    private ImageView eventDetailsPictureImageView;
    private TextView eventDetailsTitlePlainText;
    private  TextView eventDetailChooseCategory;
    private TextView eventDetailsStartDateTextBox;
    private TextView eventDetailsStartTimeTextBox;
    private  TextView eventDetailsEndDateTextBox;
    private  TextView eventDetailsEndTimeTextBox;
    private TextView eventDetailsDescriptionPlainText;
    private RadioButton eventDetailsVolunteerRadioYes;
    private RadioButton eventDetailsVolunteerRadioNo;
    private RadioButton eventDetailsSponsorRadioYes;
    private RadioButton eventDetailsSponsorRadioNo;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details_activity);

        //Connecting Var. with IDs
        eventDetailGoingSpinner = (Spinner) findViewById(R.id.EventDetailGoingSpinner);
        eventDetailsPictureImageView = findViewById(R.id.EventDetailsPictureImageView);
        eventDetailChooseCategory = findViewById(R.id.EventDetailChooseCategory);
        eventDetailsTitlePlainText = findViewById(R.id.EventDetailsTitlePlainText);
        eventDetailsStartDateTextBox = findViewById(R.id.EventDetailsStartDateTextBox);
        eventDetailsStartTimeTextBox = findViewById(R.id.EventDetailsStartTimeTextBox);
        eventDetailsEndDateTextBox = findViewById(R.id.EventDetailsEndDateTextBox);
        eventDetailsEndTimeTextBox = findViewById(R.id.EventDetailsEndTimeTextBox);
        eventDetailsDescriptionPlainText  = findViewById(R.id.EventDetailsDescriptionPlainText);
        eventDetailsVolunteerRadioYes =findViewById(R.id.EventDetailsVolunteerRadioYes);
        eventDetailsVolunteerRadioNo =findViewById(R.id.EventDetailsVolunteerRadioNo);
        eventDetailsSponsorRadioYes = findViewById(R.id.EventDetailsSponsorRadioYes);
        eventDetailsSponsorRadioNo = findViewById(R.id.EventDetailsSponsorRadioNo);


        eventDetailsVolunteerRadioNo.setEnabled(false);
        eventDetailsSponsorRadioNo.setEnabled(false);
        eventDetailsVolunteerRadioYes.setEnabled(false);
        eventDetailsSponsorRadioYes.setEnabled(false);



        //For Going waala spinner
        String[] status = {"Interested" , "Going" , "Not Going"};
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , status);
        eventDetailGoingSpinner.setAdapter(adapterStatus);

        //FireBase Goes here
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Events");
        mDatabaseReference.keepSynced(true);

        String imageUrl = getIntent().getStringExtra("picProof");
        Picasso.with(getApplicationContext()).load(imageUrl).into(eventDetailsPictureImageView);

        //Event Category Will come here

        String boldText1 = "Title :";
        String normalText1 = (String.format(getIntent().getStringExtra("eventTitle")));
        SpannableStringBuilder str1 = new SpannableStringBuilder(boldText1 + normalText1);
        str1.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        eventDetailsTitlePlainText.setText(str1);

        String boldText2 = "Description :";
        String normalText2 = (String.format(getIntent().getStringExtra("eventDescription")));
        SpannableStringBuilder str2 = new SpannableStringBuilder(boldText2 + normalText2);
        str2.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        eventDetailsDescriptionPlainText.setText(str2);

        eventDetailsStartDateTextBox.setText(String.format("%s", getIntent().getStringExtra("eventStartDate")));
        eventDetailsStartTimeTextBox.setText(String.format("%s" , getIntent().getStringExtra("eventStartTime")));
        eventDetailsEndDateTextBox.setText(String.format("%s" , getIntent().getStringExtra("eventEndDate")));
        eventDetailsEndTimeTextBox.setText(String.format("%s" , getIntent().getStringExtra("eventEndTime")));
        eventDetailChooseCategory.setText(String.format("%s" , getIntent().getStringExtra("eventType")));

        if(getIntent().getStringExtra("volunteerRequired").equals("Yes")){
            eventDetailsSponsorRadioNo.setChecked(false);
            eventDetailsVolunteerRadioYes.setChecked(true);
        }
        else
        {
            eventDetailsVolunteerRadioYes.setChecked(false);
            eventDetailsVolunteerRadioNo.setChecked(true);
        }

        if(getIntent().getStringExtra("sponsorRequired").equals("Yes"))
        {
            eventDetailsSponsorRadioYes.setChecked(true);
            eventDetailsSponsorRadioNo.setChecked(false);
        }
        else
        {
            eventDetailsSponsorRadioYes.setChecked(false);
            eventDetailsSponsorRadioNo.setChecked(true);
        }
    }
}
