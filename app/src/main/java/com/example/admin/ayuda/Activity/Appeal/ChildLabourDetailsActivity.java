package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.R;


public class ChildLabourDetailsActivity extends AppCompatActivity {

    private ImageView childLabourPicProofImageView;
    private TextView childLabourDescPlainText;
    private CheckBox childLabourPhysicalAbuse;
    private CheckBox childLabourSexualAbuse;
    private CheckBox childLabourPsychologicalAbuse;
    private CheckBox childLabourAbandon;
    private CheckBox childLabourChildMarriage;
    private CheckBox childLabourChildLabour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_labour_details_activity);

        childLabourPicProofImageView = findViewById(R.id.ChildLabourPicProofImageView);
        childLabourDescPlainText = findViewById(R.id.ChildLabourDescPlainText);
        childLabourPhysicalAbuse = findViewById(R.id.ChildLabourPhysicalAbuse);
        childLabourSexualAbuse = findViewById(R.id.ChildLabourSexualAbuse);
        childLabourPsychologicalAbuse = findViewById(R.id.ChildLabourPsychologicalAbuse);
        childLabourAbandon = findViewById(R.id.ChildLabourAbandon);
        childLabourChildLabour = findViewById(R.id.ChildLabourChildLabour);
        childLabourChildMarriage = findViewById(R.id.ChildLabourChildMarriage);
        
        //Setting CheckBox Uneditable so tha in view option user cannot edit them


    }
}
