package com.example.admin.ayuda.Activity.Appeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.ayuda.Data.AppealAdapters.AppealStatusForUserAdapter;
import com.example.admin.ayuda.Model.Ngo_Appeals;
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
import java.util.Iterator;
import java.util.List;

public class AppealsStatusForUserActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private List<Ngo_Appeals> ngo_appealsList;
    private AppealStatusForUserAdapter appealStatusForUserAdapter;
    String userId = " ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appeal_status_for_user_activity);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        userId = mUser.getUid();
        ngo_appealsList = new ArrayList<>();
        recyclerView = findViewById(R.id.AppealStatusRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mDatabase = FirebaseDatabase.getInstance();

        // For blood bank appeals
        mDatabaseReference = mDatabase.getReference().child("BloodBankAppeals");
        mDatabaseReference.keepSynced(true);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren())
                {

                    String isAccepted = item.child("isAccepted").getValue(String.class);
                    String appealUserId = item.child("appealUserId").getValue(String.class);
                    if (isAccepted != null && isAccepted.equals("Yes") && userId.equals(appealUserId)) {
                        String timestamp = item.child("timestamp").getValue(String.class);
                        retrieveNgoAppealData(timestamp);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //For child labour appeals
        mDatabase.getReference().child("ChildLabourAppeal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren())
                {

                    String isAccepted = item.child("isAccepted").getValue(String.class);
                    String appealUserId = item.child("appealUserId").getValue(String.class);
                    if (isAccepted != null && isAccepted.equals("Yes") && userId.equals(appealUserId)) {
                        String timestamp = item.child("timestamp").getValue(String.class);
                        retrieveNgoAppealData(timestamp);


                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // for disaster management
        mDatabase.getReference().child("DisasterManagementAppeals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren())
                {

                    String isAccepted = item.child("isAccepted").getValue(String.class);
                    String appealUserId = item.child("appealUserId").getValue(String.class);
                    if (isAccepted != null && isAccepted.equals("Yes") && userId.equals(appealUserId)) {
                        String timestamp = item.child("timestamp").getValue(String.class);
                        retrieveNgoAppealData(timestamp);


                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // for community development
        mDatabase.getReference().child("CommunityDevelopmentAppeals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren())
                {

                    String isAccepted = item.child("isAccepted").getValue(String.class);
                    String appealUserId = item.child("appealUserId").getValue(String.class);
                    if (isAccepted != null && isAccepted.equals("Yes") && userId.equals(appealUserId)) {
                        String timestamp = item.child("timestamp").getValue(String.class);
                        retrieveNgoAppealData(timestamp);


                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // for old age appeals
        mDatabase.getReference().child("OldAgeAppeal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren())
                {

                    String isAccepted = item.child("isAccepted").getValue(String.class);
                    String appealUserId = item.child("appealUserId").getValue(String.class);
                    if (isAccepted != null && isAccepted.equals("Yes") && userId.equals(appealUserId)) {
                        String timestamp = item.child("timestamp").getValue(String.class);
                        retrieveNgoAppealData(timestamp);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private void retrieveNgoAppealData(final String timestamp) {
        mDatabase.getReference().child("Ngo_Appeals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();

                while (items.hasNext())
                {
                    DataSnapshot item = items.next();
                    String appealTimestamp = item.child("appealTimestamp").getValue(String.class);
                    if(appealTimestamp.equals(timestamp))
                    {
                        String adminOrgName= item.child("adminOrgName").getValue(String.class);
                        String adminContactNo = item.child("adminContactNo").getValue(String.class);
                        String appealImageDp = item.child("appealImageDp").getValue(String.class);
                        String appealName = item.child("appealName").getValue(String.class);
                        Ngo_Appeals ngo_appeals = new Ngo_Appeals(adminOrgName,appealImageDp, appealName, adminContactNo);
                        ngo_appealsList.add(ngo_appeals);
                    }
                }
                appealStatusForUser();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void appealStatusForUser() {
        FirebaseDatabase.getInstance().getReference().child("Ngo_Appeals").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appealStatusForUserAdapter = new AppealStatusForUserAdapter(getApplicationContext(),ngo_appealsList);
                recyclerView.setAdapter(appealStatusForUserAdapter);
                appealStatusForUserAdapter.notifyDataSetChanged();
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
