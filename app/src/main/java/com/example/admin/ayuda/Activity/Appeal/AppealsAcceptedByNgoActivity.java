package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.admin.ayuda.Data.AppealAdapters.AppealAcceptedByNgoAdapter;
import com.example.admin.ayuda.Model.Ngo_Appeals;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppealsAcceptedByNgoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private RecyclerView recyclerView;
    private List<Ngo_Appeals> ngo_appealsList;
    private AppealAcceptedByNgoAdapter appealAcceptedByNgoAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appeal_accepted_by_ngo_activity);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUser = mAuth.getCurrentUser();
        final String userId = mUser.getUid();
        ngo_appealsList = new ArrayList<>();
        recyclerView = findViewById(R.id.AppealAcceptedRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mDatabase.getReference().child("Ngo_Appeals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                ngo_appealsList.clear();
                while (items.hasNext())
                {
                    DataSnapshot item = items.next();
                    String adminUserId = item.child("adminUserId").getValue(String.class);
//                    String appealImageDp = item.child("appealImageDp").getValue(String.class);
//                    String appealName = item.child("appealName").getValue(String.class);
//                    String appealTimestamp = item.child("appealTimestamp").getValue(String.class);
                    if (adminUserId.equals(userId))
                    {
                        String appealImageDp = item.child("appealImageDp").getValue(String.class);
                        String appealName = item.child("appealName").getValue(String.class);
                        String appealTimestamp = item.child("appealTimestamp").getValue(String.class);
                        Ngo_Appeals entry = new Ngo_Appeals(appealImageDp,appealName,appealTimestamp);
                        ngo_appealsList.add(entry);
                    }

                }
                appealAcceptedByNgo();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void appealAcceptedByNgo() {
        FirebaseDatabase.getInstance().getReference().child("Ngo_Appeals").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appealAcceptedByNgoAdapter = new AppealAcceptedByNgoAdapter(getApplicationContext(),ngo_appealsList);
                recyclerView.setAdapter(appealAcceptedByNgoAdapter);
                appealAcceptedByNgoAdapter.notifyDataSetChanged();
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
