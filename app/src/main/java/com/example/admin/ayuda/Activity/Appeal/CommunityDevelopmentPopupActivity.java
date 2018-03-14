package com.example.admin.ayuda.Activity.Appeal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.admin.ayuda.R;

public class CommunityDevelopmentPopupActivity extends AppCompatActivity {

    private Button communityDevPopupProceedButton;
    private Button communityDevPopupRejectButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_development_popup);

        communityDevPopupProceedButton = findViewById(R.id.CommunityDevPopupProceedButton);
        communityDevPopupRejectButton = findViewById(R.id.CommunityDevPopupRejectButton);
    }
}
