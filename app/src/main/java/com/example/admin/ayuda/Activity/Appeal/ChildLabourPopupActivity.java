package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.ayuda.R;

public class ChildLabourPopupActivity extends AppCompatActivity {

    private Button childLabourPopupProceedButton;
    private Button childLabourPopupRejectButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_labour_popup);

        childLabourPopupProceedButton = findViewById(R.id.ChildLabourPopupProceedButton);
        childLabourPopupRejectButton = findViewById(R.id.ChildLabourPopupRejectButton);

    }
}
