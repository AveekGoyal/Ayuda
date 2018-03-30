package com.example.admin.ayuda.Activity;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.ayuda.Activity.Appeal.AppealListFragment;
import com.example.admin.ayuda.Activity.Appeal.AppealsAcceptedByNgoActivity;
import com.example.admin.ayuda.Activity.Appeal.AppealsStatusForUserActivity;
import com.example.admin.ayuda.Activity.Event.EventListFragment;
import com.example.admin.ayuda.Activity.Login.MainActivity;
import com.example.admin.ayuda.Activity.News.NewsListFragment;
import com.example.admin.ayuda.Activity.Story.StoryListFragment;
import com.example.admin.ayuda.Invite;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tapadoo.alerter.Alerter;

import es.dmoral.toasty.Toasty;

public class MainNavigationActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    String userId=" ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navigation_activity);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        mAuth =FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId = mUser.getUid();

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.appealFragment:
                                selectedFragment = AppealListFragment.newInstance();
                                break;
                            case R.id.eventFragment:
                                selectedFragment = EventListFragment.newInstance();
                                break;
                            case R.id.newsFragment:
                                selectedFragment = NewsListFragment.newInstance();
                                break;
                            case R.id.storyFragment:
                                selectedFragment = StoryListFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, AppealListFragment.newInstance());
        transaction.commit();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final MenuItem item1 = findViewById(R.id.action_acceptedAppeals);


        switch (item.getItemId()) {
            case R.id.action_signout:
                if (mAuth != null && mUser != null) {
                    Toast.makeText(getApplicationContext(),"Sign out",Toast.LENGTH_LONG).show();

                    mAuth.signOut();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;

            case R.id.action_profile:
                if (mAuth != null && mUser != null)
                {
                    Toast.makeText(getApplicationContext() , "Profile" , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainNavigationActivity.this , ProfileActivity.class));

                }
                break;

            case R.id.action_contactus:
                Toast.makeText(getApplicationContext(), "Contact Us" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_inviteus:

                if (mAuth != null && mUser != null)
                {
                    Toast.makeText(getApplicationContext() , "INVITE US" , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainNavigationActivity.this , InvitePeople.class));

                }
                break;
            case R.id.action_acceptedAppeals:
                DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child("NgoAdmin").child(userId);
                getType.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String type = dataSnapshot.child("type").getValue(String.class);
                        if(type == null)
                        {
                            Alerter.create(MainNavigationActivity.this)
                                    .setTitle("Error ")
                                    .setText("You are not allowed to access this functionality")
                                    .setDuration(10000)
                                    .show();

                        }
                        else if(type.equals("NgoAdmin"))
                        {
                            startActivity(new Intent(getApplicationContext(), AppealsAcceptedByNgoActivity.class));

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                break;

            case R.id.action_appealsUploaded:
                startActivity(new Intent(getApplicationContext(), AppealsStatusForUserActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
