package com.example.admin.ayuda.Activity.Event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.admin.ayuda.R;

public class EventDetailsActivity extends AppCompatActivity {

    private Spinner EventDetailGoingSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details_activity);

        //Connecting Var. with IDs
        EventDetailGoingSpinner = (Spinner) findViewById(R.id.EventDetailGoingSpinner);

        //For Going waala spinner
        String[] status = {"Intrested" , "Going" , "Not Going"};
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , status);
        EventDetailGoingSpinner.setAdapter(adapterStatus);
    }
}
