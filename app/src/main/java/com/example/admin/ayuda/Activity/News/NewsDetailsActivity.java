package com.example.admin.ayuda.Activity.News;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Data.AppealAdapters.News.NewsAdapter;
import com.example.admin.ayuda.Model.News;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsDetailsActivity extends AppCompatActivity {

    private ImageView newsPicProofImageView;
    private TextView newsDescriptionPlainText;
    private TextView newsHeadlinePlainText;
    private TextView newsDateTextBox;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private NewsAdapter newsAdapter;
    private List<News> newsList;
    String type=" ";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_activity);

        newsPicProofImageView = findViewById(R.id.NewsPicProofImageView);
        newsHeadlinePlainText = findViewById(R.id.NewsHeadlinePlainText);
        newsDescriptionPlainText = findViewById(R.id.NewsDescriptionPlainText);
        newsDateTextBox = findViewById(R.id.NewsDateTextBox);


        //Connecting firebase to database.
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("News");
        mDatabaseReference.keepSynced(true);

        //setText to all TextView to show data to user.
        String imageUrl = getIntent().getStringExtra("newsPic");
        Picasso.with(getApplicationContext()).load(imageUrl).into(newsPicProofImageView);
        newsHeadlinePlainText.setText(String.format("Headline : %s", getIntent().getStringExtra("newsHeadline")));
        newsDescriptionPlainText.setText(String.format("Description : %s", getIntent().getStringExtra("newsDescription")));
        newsDateTextBox.setText(String.format("Date : %s", getIntent().getStringExtra("newsDate")));




    }
}
