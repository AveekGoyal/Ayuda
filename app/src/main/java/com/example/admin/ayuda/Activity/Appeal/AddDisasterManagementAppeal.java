package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.admin.ayuda.R;

public class AddDisasterManagementAppeal extends AppCompatActivity {

    private ImageButton addDisasterPicProofImageButton;
    private EditText addDisasterDescTextBox;
    private CheckBox addDisasterNeedFood;
    private CheckBox addDisasterNeedWater;
    private CheckBox addDisasterNeedShelter;
    private CheckBox addDisasterNeedClothing;
    private CheckBox addDisasterNeedMedical;
    private CheckBox addDisasterNeedRehab;
    private  EditText addDisasterContactNoTextBox;
    private EditText addDisasterAltContactNoTextBox;
    private Button addDisasterSafetyButton;
    private Button addDisasterSubmitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_disaster_management_appeal);

        addDisasterPicProofImageButton = findViewById(R.id.AddDisasterPicProofImageButton);
        addDisasterDescTextBox = findViewById(R.id.AddDisasterDescTextBox);
        addDisasterNeedFood = findViewById(R.id.AddDisasterNeedFood);
        addDisasterNeedWater = findViewById(R.id.AddDisasterNeedWater);
        addDisasterNeedShelter = findViewById(R.id.AddDisasterNeedShelter);
        addDisasterNeedClothing = findViewById(R.id.AddDisasterNeedClothing);
        addDisasterNeedMedical = findViewById(R.id.AddDisasterNeedMedical);
        addDisasterNeedRehab = findViewById(R.id.AddDisasterNeedRehab);
        addDisasterContactNoTextBox = findViewById(R.id.AddDisasterContactNoTextBox);
        addDisasterAltContactNoTextBox = findViewById(R.id.AddDisasterAltContactNoTextBox);
        addDisasterSafetyButton = findViewById(R.id.AddDisasterSafetyButton);
        addDisasterSubmitButton = findViewById(R.id.AddDisasterSubmitButton);

    }
}
