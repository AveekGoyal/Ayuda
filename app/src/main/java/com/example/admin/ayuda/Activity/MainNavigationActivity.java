package com.example.admin.ayuda.Activity;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.admin.ayuda.Activity.Appeal.AppealListFragment;
import com.example.admin.ayuda.Activity.Event.EventListFragment;
import com.example.admin.ayuda.Activity.News.NewsListFragment;
import com.example.admin.ayuda.Activity.Story.StoryListFragment;
import com.example.admin.ayuda.R;

public class MainNavigationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navigation_activity);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

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
}
