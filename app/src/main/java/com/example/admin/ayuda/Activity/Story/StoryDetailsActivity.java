package com.example.admin.ayuda.Activity.Story;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class StoryDetailsActivity extends AppCompatActivity{

    private ImageView storyDetailPictureImageView;
    private TextView storyDetailCaptionPlainDetail;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_details_activity);

        storyDetailPictureImageView = findViewById(R.id.StoryDetailPictureImageView);
        storyDetailCaptionPlainDetail = findViewById(R.id.StoryDetailCaptionPlainDetail);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Story");
        mDatabaseReference.keepSynced(true);

        String imageUrl = getIntent().getStringExtra("statusImage");
        Picasso.with(getApplicationContext()).load(imageUrl).into(storyDetailPictureImageView);

        storyDetailCaptionPlainDetail.setText(String.format("%s" , getIntent().getStringExtra("caption")));

    }
}
