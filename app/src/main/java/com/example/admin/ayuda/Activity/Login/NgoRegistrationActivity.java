package com.example.admin.ayuda.Activity.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.admin.ayuda.R;

public class NgoRegistrationActivity extends AppCompatActivity {

    private Spinner NgoRegStateSpinner;
    private  Spinner NgoRegCitySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngo_registration_activity);

        //Joining Var. With their Ids
        NgoRegStateSpinner = (Spinner) findViewById(R.id.NgoRegStateSpinner);
        NgoRegCitySpinner = (Spinner) findViewById(R.id.NgoRegCitySpinner);

        //Adding Values in Spinner
        //For State Spinner:
        String[] states = {"Uttar Pradesh" , "Haryana" , "Punjab" , "Gujrat" , "Jharkhand"};
        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , states);
        NgoRegStateSpinner.setAdapter(adapterState);

        //For Cities Spinner:
        String[] cities = {"Meerut" , "Panipat" , "Amritsar" , "Sonepat" , "Barauni"};
        ArrayAdapter<String> adapterCities = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , cities);
        NgoRegCitySpinner.setAdapter(adapterCities);
    }

}
