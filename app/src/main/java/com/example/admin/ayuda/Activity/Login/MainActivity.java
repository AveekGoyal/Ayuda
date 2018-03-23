package com.example.admin.ayuda.Activity.Login;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/*
This is Login Interface, we will ask for user type and then accordingly login the user based on Email and Password
 */
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseUser mUser;
    private RadioGroup loginRadioGroup;
    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginButton;
    private Button loginCreateButton;
    private ProgressBar mProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.loginLoginButton);
        loginEmail=findViewById(R.id.loginEmailTextBox);
        loginPassword=findViewById(R.id.loginPasswordTextBox);
        loginRadioGroup=findViewById(R.id.loginRadioGroup);
        loginCreateButton = findViewById(R.id.loginNewUserRegButton);
        mProgress=findViewById(R.id.progressBar2);

        // ActionBar bar = getActionBar();

        // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser= firebaseAuth.getCurrentUser();

                if(mUser != null && mUser.isEmailVerified())
                {

                    //Toast.makeText(MainActivity.this,"Signed In", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,MainNavigationActivity.class));
                    finish();
                }
                else
                {
                    mAuth.signOut();
                    // Toast.makeText(MainActivity.this,"Not Signed In", Toast.LENGTH_LONG).show();
                }
            }
        };


        loginRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.loginRadioAdmin:
                        loginCreateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               startActivity(new Intent(MainActivity.this , NgoRegistrationActivity.class));

                            }
                        });

                        loginButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String email = loginEmail.getText().toString();
                                String password = loginPassword.getText().toString();
                                loginNgoAdmin(email,password);

                            }
                        });

                        break;
                    case R.id.loginRadioMember:
                        loginCreateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(MainActivity.this, MemberRegistrationActivity.class));
                            }
                        });

                        loginButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String email = loginEmail.getText().toString();
                                String password = loginPassword.getText().toString();
                                loginMember(email,password);
                            }
                        });


                        break;
                    case R.id.loginRadioNonMember:
                        loginCreateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(MainActivity.this, NonMemberRegistrationActivity.class));
                            }
                        });

                        loginButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String email = loginEmail.getText().toString();
                                String password = loginPassword.getText().toString();
                                mProgress.setIndeterminate(true);

                                mProgress.setVisibility(View.VISIBLE);
                                loginNonMember(email,password);



                            }
                        });
                        
                        break;

                }
            }
        });


    }

    private void loginMember(final String email, final String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful())
                {


                    mAuthListener = new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                            mUser=firebaseAuth.getCurrentUser();

                            DatabaseReference mNgoAdminDatabase = FirebaseDatabase.getInstance().getReference().child("Member");

                            if(mUser.isEmailVerified())
                            {

                                DatabaseReference newAdmin = mNgoAdminDatabase.push();
                                Map<String, String> dataToSave = new HashMap<>();

                                dataToSave.put("Email", email);
                                dataToSave.put("Password", password);
                                newAdmin.setValue(dataToSave);
                                startActivity(new Intent(MainActivity.this, MainNavigationActivity.class));

                                finish();
                            }
                        }
                    };
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Task Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }

        });


    }

    private void loginNonMember(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful())
                {


                    mAuthListener = new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                            mUser=firebaseAuth.getCurrentUser();

                            DatabaseReference mNgoAdminDatabase = FirebaseDatabase.getInstance().getReference().child("NonMember");

                            if(mUser.isEmailVerified())
                            {

                                DatabaseReference newAdmin = mNgoAdminDatabase.push();
                                Map<String, String> dataToSave = new HashMap<>();

                                dataToSave.put("Email", email);
                                dataToSave.put("Password", password);
                                newAdmin.setValue(dataToSave);
                                startActivity(new Intent(MainActivity.this, MainNavigationActivity.class));
                                finish();
                            }
                        }
                    };
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Task Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    private void loginNgoAdmin(final String email, final String password) {


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(getApplicationContext(),"Task Successfull",Toast.LENGTH_LONG).show();

                if (task.isSuccessful())
                {


                   mAuthListener = new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                            mUser=firebaseAuth.getCurrentUser();

                            DatabaseReference mNgoAdminDatabase = FirebaseDatabase.getInstance().getReference().child("NgoAdmin");

                            if(mUser.isEmailVerified())
                            {

                                DatabaseReference newAdmin = mNgoAdminDatabase.push();
                                Map<String, String> dataToSave = new HashMap<>();

                                dataToSave.put("Email", email);
                                dataToSave.put("Password", password);
                                newAdmin.setValue(dataToSave);
                                startActivity(new Intent(MainActivity.this, MainNavigationActivity.class));
                                finish();
                            }
                        }
                    };
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Task Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


 /*
    Non-member login method - he/she can login from google or facebook
     */
}
