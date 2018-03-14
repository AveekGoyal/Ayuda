package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.admin.ayuda.R;

public class AddCommunityDevelopmentAppealActivity extends AppCompatActivity {

    private ImageButton addCommunityDevPicProofImageButton;
    private CheckBox addCommunityDevCleaningCheckBox;
    private CheckBox addCommunityDevHungerCheckBox;
    private CheckBox addCommunityDevHealthIssuesCheckBox;
    private CheckBox addCommunityDevPovertyCheckBox;
    private EditText addCommunityDevDescTextBox;
    private Button addCommunityDevPreviousComplaintsButton;
    private EditText addCommunityDevContactNoTextBox;
    private  Button addCommunityDevSubmitButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_community_development_appeal_activity);

        addCommunityDevPicProofImageButton = findViewById(R.id.AddCommunityDevPicProofImageButton);
        addCommunityDevCleaningCheckBox = findViewById(R.id.AddCommunityDevCleaningCheckBox);
        addCommunityDevHungerCheckBox = findViewById(R.id.AddCommunityDevHungerCheckBox);
        addCommunityDevHealthIssuesCheckBox = findViewById(R.id.AddCommunityDevHealthIssuesCheckBox);
        addCommunityDevPovertyCheckBox = findViewById(R.id.AddCommunityDevPovertyCheckBox);
        addCommunityDevDescTextBox = findViewById(R.id.AddCommunityDevDescTextBox);
        addCommunityDevPreviousComplaintsButton = findViewById(R.id.AddCommunityDevPreviousComplaintsButton);
        addCommunityDevContactNoTextBox = findViewById(R.id.AddCommunityDevContactNoTextBox);
        addCommunityDevSubmitButton = findViewById(R.id.AddCommunityDevSubmitButton);
    }
}
