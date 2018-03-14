package com.example.admin.ayuda.Activity.Event;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.ayuda.R;

import org.w3c.dom.Text;

public class EventDetailsActivity extends AppCompatActivity {

    private Spinner eventDetailGoingSpinner;
    private ImageView eventDetailsPictureImageView;
    private EditText eventDetailsTitlePlainText;
    private  Spinner eventDetailChooseCategory;
    private TextView eventDetailsStartDateTextBox;
    private TextView eventDetailsStartTimeTextBox;
    private  TextView eventDetailsEndDateTextBox;
    private  TextView eventDetailsEndTimeTextBox;
    private TextView eventDetailsDescriptionPlainText;
    private RadioButton eventDetailsVolunteerRadioYes;
    private RadioButton eventDetailsVolunteerRadioNo;
    private RadioButton eventDetailsSponsorRadioYes;
    private RadioButton eventDetailsSponsorRadioNo;

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

        //For Going waala spinner
        String[] status = {"Intrested" , "Going" , "Not Going"};
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , status);
        eventDetailGoingSpinner.setAdapter(adapterStatus);
    }
}
