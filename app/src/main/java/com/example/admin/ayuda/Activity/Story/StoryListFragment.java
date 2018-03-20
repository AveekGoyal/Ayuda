package com.example.admin.ayuda.Activity.Story;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ayuda.Activity.Event.AddEventActivity;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class StoryListFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;



    public static StoryListFragment newInstance()
    {
        StoryListFragment fragment= new StoryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.story_list_fragment,container,false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        FloatingActionButton addSubmitBtn = view.findViewById(R.id.StoryListAddStoryFloatingButton);
        addSubmitBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if ((mAuth != null && mUser != null))
                {
                    startActivity(new Intent(getActivity(), AddStoryActivity.class));
                }
            }
        });
        return view;
    }
}
