package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.admin.ayuda.R;


public class AddChildLabourAppealActivity extends AppCompatActivity {

    private ImageButton addChildLabourPicProofImageButton;
    private EditText addChildLabourDescTextBox;
    private CheckBox addChildLabourPhysicalAbuse;
    private CheckBox addChildLabourSexualAbuse;
    private CheckBox addChildLabourPsychologicalAbuse;
    private CheckBox addChildLabourAbandon;
    private CheckBox addChildLabourChildLabour;
    private CheckBox addChildLabourChildMarriage;
    private RadioButton addChildLabourRadioMale;
    private RadioButton addChildLabourRadioFemale;
    private Button addChildLabourSubmitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_child_labour_appeal_activity);

        addChildLabourPicProofImageButton = findViewById(R.id.AddChildLabourPicProofImageButton);
        addChildLabourDescTextBox = findViewById(R.id.AddChildLabourDescTextBox);
        addChildLabourPhysicalAbuse = findViewById(R.id.AddChildLabourPhysicalAbuse);
        addChildLabourSexualAbuse = findViewById(R.id.AddChildLabourSexualAbuse);
        addChildLabourAbandon = findViewById(R.id.AddChildLabourAbandon);
        addChildLabourPsychologicalAbuse = findViewById(R.id.AddChildLabourPsychologicalAbuse);
        addChildLabourChildLabour = findViewById(R.id.AddChildLabourChildLabour);
        addChildLabourChildMarriage = findViewById(R.id.AddChildLabourChildMarriage);
        addChildLabourRadioMale = findViewById(R.id.AddChildLabourRadioMale);
        addChildLabourRadioFemale = findViewById(R.id.AddChildLabourRadioFemale);
        addChildLabourSubmitButton = findViewById(R.id.AddChildLabourSubmitButton);

    }
}
