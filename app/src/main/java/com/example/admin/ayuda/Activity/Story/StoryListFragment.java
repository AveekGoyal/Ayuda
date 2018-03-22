package com.example.admin.ayuda.Activity.Story;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ayuda.Activity.Event.AddEventActivity;
import com.example.admin.ayuda.Data.AppealAdapters.StoryAdapter;
import com.example.admin.ayuda.Model.Story;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StoryListFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private RecyclerView recyclerView;
    private List<Story> storyList;
    private StoryAdapter storyAdapter;



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
        storyList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.storyListRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        storyList.clear();
        addStory();
        return view;
    }

    private void addStory() {
        FirebaseDatabase.getInstance().getReference().child("Story").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Story story = dataSnapshot.getValue(Story.class);
                storyList.add(story);
                storyAdapter = new StoryAdapter(getActivity(),storyList);
                recyclerView.setAdapter(storyAdapter);
                storyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
