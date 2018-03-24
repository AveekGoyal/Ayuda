package com.example.admin.ayuda.Activity.Login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tapadoo.alerter.Alerter;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginLoginButton);
        loginEmail=findViewById(R.id.loginEmailTextBox);
        loginPassword=findViewById(R.id.loginPasswordTextBox);
        loginRadioGroup=findViewById(R.id.loginRadioGroup);
        loginCreateButton = findViewById(R.id.loginNewUserRegButton);

        //Check Internet Connectivity
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        if(connected == false)
        {
            Alerter.create(MainActivity.this)
                    .setTitle("Error Please Connect To internet Account")
                    .setText("This app requires a active internet connection to work.")
                    .setDuration(10000)
                    .show();
        }

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

        final RadioButton loginAdmin = findViewById(R.id.loginRadioAdmin);
        final RadioButton loginMember = findViewById(R.id.loginRadioMember);
        final RadioButton loginNonMember = findViewById(R.id.loginRadioNonMember);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loginAdmin.isChecked() && !loginMember.isChecked() && !loginNonMember.isChecked())
                {
                    Alerter.create(MainActivity.this)
                            .setTitle("Error Logging In")
                            .setText("User Type is not selected. Please select your user type.")
                            .setDuration(10000)
                            .show();
                }

            }
        });
        loginCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loginAdmin.isChecked() && !loginMember.isChecked() && !loginNonMember.isChecked())
                {
                    Alerter.create(MainActivity.this)
                            .setTitle("Error Creating Account")
                            .setText("User Type is not selected. Please select your user type.")
                            .setDuration(10000)
                            .show();
                }

            }
        });





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


                                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password) )
                                {
                                    Alerter.create(MainActivity.this)
                                            .setTitle("Empty Fields")
                                            .setText("Please Enter You email and Password.")
                                            .setDuration(10000)
                                            .show();
                                }
                                else {
                                    loginNgoAdmin(email,password);
                                }




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
                                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password) )
                                {
                                    Alerter.create(MainActivity.this)
                                            .setTitle("Empty Fields")
                                            .setText("Please Enter You email and Password.")
                                            .setDuration(10000)
                                            .show();
                                }
                                else {
                                    loginMember(email, password);
                                }

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

                                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password) )
                                {
                                    Alerter.create(MainActivity.this)
                                            .setTitle("Empty Fields")
                                            .setText("Please Enter You email and Password.")
                                            .setDuration(10000)
                                            .show();
                                }
                                else {
                                    loginNonMember(email, password);
                                }





                            }
                        });

                        break;

                }
            }
        });


    }

    private void loginMember(final String email, final String password) {
        final MaterialDialog materialDialog=new MaterialDialog.Builder(MainActivity.this)
                .title("Logging In")
                .content("Please Wait")
                .progress(true, 0)
                .show();



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
                            else
                            {
                                materialDialog.cancel();
                                Alerter.create(MainActivity.this)
                                        .setTitle("Error Logging In")
                                        .setText("Your Email is not Verified")
                                        .setDuration(10000)
                                        .show();

                            }

                        }
                    };
                }
                else
                {
                    materialDialog.cancel();
                    Alerter.create(MainActivity.this)
                            .setTitle("Error Logging In")
                            .setText("Your Email or Password is Incorrect")
                            .setDuration(10000)
                            .show();


                }
            }

        });


    }

    private void loginNonMember(final String email, final String password) {
        final MaterialDialog materialDialog=new MaterialDialog.Builder(MainActivity.this)
                .title("Logging In")
                .content("Please Wait")
                .progress(true, 0)
                .show();



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
                            else
                            {
                                materialDialog.cancel();
                                Alerter.create(MainActivity.this)
                                        .setTitle("Error Logging In")
                                        .setText("Your Email is not Verified")
                                        .setDuration(10000)
                                        .show();

                            }
                        }
                    };
                }
                else
                {
                    materialDialog.cancel();
                    Alerter.create(MainActivity.this)
                            .setTitle("Error Logging In")
                            .setText("Your Email or Password is Incorrect")
                            .setDuration(10000)
                            .show();


                }
            }

        });

    }

    private void loginNgoAdmin(final String email, final String password) {
        final MaterialDialog materialDialog=new MaterialDialog.Builder(MainActivity.this)
                .title("Logging In")
                .content("Please Wait")
                .progress(true, 0)
                .show();


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


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
                            else
                            {
                                materialDialog.cancel();
                                Alerter.create(MainActivity.this)
                                        .setTitle("Error Logging In")
                                        .setText("Your Email is not Verified")
                                        .setDuration(10000)
                                        .show();

                            }
                        }
                    };
                }
                else
                {
                    materialDialog.cancel();
                    Alerter.create(MainActivity.this)
                            .setTitle("Error Logging In")
                            .setText("Your Email or Password is Incorrect")
                            .setDuration(10000)
                            .show();



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


 //Check Internet connectivity

//
//    public class CheckConnectivity extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent arg1) {
//
//            boolean isConnected = arg1.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
//            if(isConnected){
//                Alerter.create(MainActivity.this)
//                        .setTitle("Error Please Connect To internet Account")
//                        .setText("This app requires a active internet connection to work.")
//                        .setDuration(10000)
//                        .show();
//            }
//            else{
//                Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
}