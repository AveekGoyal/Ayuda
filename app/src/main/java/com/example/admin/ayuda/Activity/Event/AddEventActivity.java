package com.example.admin.ayuda.Activity.Event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.admin.ayuda.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddEventActivity extends AppCompatActivity {

    private Button addEventStartDatePicker;
    Calendar myCalendar = Calendar.getInstance();
    private  Button addEventEndDatePicker;
    private  Button addEventStartTimePicker;
    private Button addEventEndTimePicker;
    private ImageView addEventPicProofImageButton;
    private Spinner addEventChooseCategorySpinner;
    private EditText addEventDescriptionTextBox;
    private EditText addEventTitleTextBox;
    private RadioButton addEventVolunteerRadioYes;
    private RadioButton addEventVolunteerRadioNo;
    private RadioButton addEventSponsorRadioYes;
    private  RadioButton AddEventSponsorRadioNo;
    private Button addEventSubmitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);

        //Joining Variables with their Ids
         addEventStartDatePicker = (Button) findViewById(R.id.AddEventStartDatePicker);
         addEventEndDatePicker = (Button) findViewById(R.id.AddEventEndDatePicker);
        addEventStartTimePicker = (Button) findViewById(R.id.AddEventStartTimePicker);
        addEventEndTimePicker = (Button) findViewById(R.id.AddEventEndTimePicker);
        addEventPicProofImageButton = findViewById(R.id.AddEventPicProofImageButton);
        addEventChooseCategorySpinner = findViewById(R.id.AddEventChooseCategorySpinner);
        addEventTitleTextBox = findViewById(R.id.AddEventTitleTextBox);
        addEventDescriptionTextBox = findViewById(R.id.AddEventDescriptionTextBox);
        addEventVolunteerRadioYes = findViewById(R.id.AddEventVolunteerRadioYes);
        addEventVolunteerRadioNo = findViewById(R.id.AddEventVolunteerRadioNo);
        addEventSponsorRadioYes = findViewById(R.id.AddEventSponsorRadioYes);
        addEventSponsorRadioYes = findViewById(R.id.AddEventSponsorRadioNo);
        addEventSubmitButton = findViewById(R.id.AddEventSubmitButton);


        /*
        Functions to use Date Pick Button
         */

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

        addEventEndDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddEventActivity.this, date , myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addEventStartDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddEventActivity.this, date , myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
                        Toast.makeText(AddEventActivity.this , ( selectedHour + ":" + selectedMinute) , Toast.LENGTH_LONG).show();
                    }
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
                        Toast.makeText(AddEventActivity.this , ( selectedHour + ":" + selectedMinute) , Toast.LENGTH_LONG).show();
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        /*
        Time Pick Ends Here
         */

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Toast.makeText(AddEventActivity.this ,(sdf.format(myCalendar.getTime())) , Toast.LENGTH_LONG).show();
    }
}
