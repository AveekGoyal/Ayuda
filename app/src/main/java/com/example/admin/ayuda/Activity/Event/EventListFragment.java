package com.example.admin.ayuda.Activity.Event;


import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ayuda.Data.AppealAdapters.Event.EventAdapter;
import com.example.admin.ayuda.Model.Event;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment{

    private FirebaseDatabase mDatabase;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;
    private List<Event> eventList;
    private EventAdapter addEventAdapter;
    String type=" ";
    public static EventListFragment newInstance()
    {
        EventListFragment fragment = new EventListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.event_list_fragment, container,false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        assert mUser != null;
        String userId = mUser.getUid();
        mDatabase = FirebaseDatabase.getInstance();
        eventList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));



        DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child("NgoAdmin").child(userId);
        getType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                type = dataSnapshot.child("type").getValue(String.class);
                if (type == null)
                {
                    FloatingActionButton addEventBtn = view.findViewById(R.id.EventListFloatingActionButton);
                    addEventBtn.setVisibility(View.GONE);

                }
                else if (type.equals("NgoAdmin"))
                {
                    FloatingActionButton addEventBtn = view.findViewById(R.id.EventListFloatingActionButton);
                    addEventBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(mAuth!= null && mUser != null)
                            {
                                startActivity(new Intent(getActivity(), AddEventActivity.class));
                            }

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        eventList.clear();

        getEventData();


        return view;

    }

    private void getEventData() {
        mDatabase.getReference().child("Events").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Event event = dataSnapshot.getValue(Event.class);
                eventList.add(event);
                addEventAdapter = new EventAdapter(getActivity(),eventList);
                recyclerView.setAdapter(addEventAdapter);
                addEventAdapter.notifyDataSetChanged();


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
