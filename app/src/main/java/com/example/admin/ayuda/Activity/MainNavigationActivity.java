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
import com.example.admin.ayuda.Activity.Event.EventListFragment;
import com.example.admin.ayuda.Activity.Login.MainActivity;
import com.example.admin.ayuda.Activity.News.NewsListFragment;
import com.example.admin.ayuda.Activity.Story.StoryListFragment;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainNavigationActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navigation_activity);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        mAuth =FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

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
                Toast.makeText(getApplicationContext(), "Invite Us", Toast.LENGTH_SHORT).show();

            case R.id.action_acceptedAppeals:
                Toast.makeText(getApplicationContext(), "Accepted Appeals" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_appealsUploaded:
                Toast.makeText(getApplicationContext(), "Appeals Uploaded" , Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
