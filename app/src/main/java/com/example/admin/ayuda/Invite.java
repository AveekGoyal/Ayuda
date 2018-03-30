package com.example.admin.ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tapadoo.alerter.Alerter;



public class Invite extends AppCompatActivity {

    private Button whatsapp;
    private Button facebook;
    private Button twitter;
    private Button google;
    private String message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite);

        google = findViewById(R.id.inviteG);
        facebook = findViewById(R.id.inviteFacebook);
        twitter = findViewById(R.id.inviteTwitetr);
        whatsapp = findViewById(R.id.invitewhatsapp);

        message="Help us to innovate more. Message sent Using Ayuda App!";

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT , message);
                    intent.setPackage("com.whatsapp");
                    startActivity(intent);
                }
                catch (Exception e){
                    Alerter.create(Invite.this)
                            .setTitle("No App found")
                            .setText("Please Install Whatsapp to use this feature.")
                            .setDuration(10000)
                            .show();
                }
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    intent.setPackage("advanced.twitter.android");
                    startActivity(intent);
                }
                catch (Exception e){
                    Alerter.create(Invite.this)
                            .setTitle("No App found")
                            .setText("Please Install Twitter to use this feature.")
                            .setDuration(10000)
                            .show();
                }
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT , message);
                    intent.setPackage("com.google.android.apps.plus");
                    startActivity(intent);
                }
                catch (Exception e){
                    Alerter.create(Invite.this)
                            .setTitle("No App found")
                            .setText("Please Install Google+ to use this feature.")
                            .setDuration(10000)
                            .show();
                }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT , message);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (Exception e){
                    Alerter.create(Invite.this)
                            .setTitle("No App found")
                            .setText("Please Install Facebook to use this feature.")
                            .setDuration(10000)
                            .show();
                }
            }
        });

    }
}
