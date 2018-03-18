package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.ayuda.Data.AppealAdapters.BloodBankAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.ChildLabourAppealAdapter;
import com.example.admin.ayuda.Model.BloodBankAppeal;
import com.example.admin.ayuda.Model.ChildAbuseAppeals;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AppealListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private RecyclerView recycler;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Spinner chooseSortCategory;
    private BloodBankAppealAdapter bloodBankAppealAdapter;
    private List<BloodBankAppeal> bloodBankAppealList;
    private ChildLabourAppealAdapter childLabourAppealAdapter;
    private List<ChildAbuseAppeals> childAbuseAppealsList;
    String type=" ";





    public static AppealListFragment newInstance()
    {
        AppealListFragment fragment = new AppealListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.appeal_list_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        bloodBankAppealList = new ArrayList<>();

        childAbuseAppealsList = new ArrayList<>();

        recycler = view.findViewById(R.id.appealListFragmentRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        chooseSortCategory = view.findViewById(R.id.AppealListChooseCategorySpinner);
        chooseSortCategory.setOnItemSelectedListener(this);


        List categories = new ArrayList<>();
        categories.add("Blood Bank");
        categories.add("Disaster Management");
        categories.add("Old Age");
        categories.add("Child Abuse");
        categories.add("Community Development");
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseSortCategory.setAdapter(dataAdapter);
        final String userId = mUser.getUid();
        DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child("NonMember").child(userId);
        getType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                type = dataSnapshot.child("type").getValue(String.class);
                if (type==null)
                {
                    DatabaseReference getTypeMember = FirebaseDatabase.getInstance().getReference().child("Member").child(userId);
                    getTypeMember.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            type = dataSnapshot.child("type").getValue(String.class);
                            if(type==null)
                            {
                                FloatingActionButton addAppealBtn = view.findViewById(R.id.AppealListAddAppealFloatingButton);
                                addAppealBtn.setVisibility(View.GONE);

                            }
                            else
                            {
                                FloatingActionButton addAppealBtn = view.findViewById(R.id.AppealListAddAppealFloatingButton);
                                addAppealBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (mAuth != null && mUser != null) {
                                            startActivity(new Intent(getActivity(), ChooseAppealCategoryActivity.class));
                                        }
                                    }
                                });


                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                else
                {
                    FloatingActionButton addAppealBtn = view.findViewById(R.id.AppealListAddAppealFloatingButton);
                    addAppealBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mAuth != null && mUser != null) {
                                startActivity(new Intent(getActivity(), ChooseAppealCategoryActivity.class));
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return view;
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String categorySelected = parent.getItemAtPosition(position).toString();



        if( categorySelected.equals("Blood Bank"))
        {
            bloodBankAppealList.clear();
            getBloodBankAppealData();
        }
        if(categorySelected.equals("Disaster Management"))
        {
            getDisasterManagementAppealData();
        }
        if(categorySelected.equals("Old Age"))
        {
            getOldAgeAppealData();
        }
        if(categorySelected.equals("Child Abuse"))
        {
            childAbuseAppealsList.clear();
            getChildAbuseAppealData();
        }
        if(categorySelected.equals("Community Development"))
        {
            getCommunityDevelopmentAppealData();
        }





    }

    private void getCommunityDevelopmentAppealData() {
    }

    private void getChildAbuseAppealData() {
            FirebaseDatabase.getInstance().getReference().child("ChildLabourAppeal").orderByChild("timestamp").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    ChildAbuseAppeals childAbuseAppeals = dataSnapshot.getValue(ChildAbuseAppeals.class);
                    childAbuseAppealsList.add(childAbuseAppeals);
                    childLabourAppealAdapter = new ChildLabourAppealAdapter(getActivity(), childAbuseAppealsList);
                    recycler.setAdapter(childLabourAppealAdapter);
                    childLabourAppealAdapter.notifyDataSetChanged();



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

    private void getOldAgeAppealData() {
    }

    private void getDisasterManagementAppealData() {
    }

    private void getBloodBankAppealData() {

        FirebaseDatabase.getInstance().getReference().child("BloodBankAppeals").orderByChild("timestamp").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                BloodBankAppeal bloodBankAppeal = dataSnapshot.getValue(BloodBankAppeal.class);
                bloodBankAppealList.add(bloodBankAppeal);
                bloodBankAppealAdapter = new BloodBankAppealAdapter(getActivity(), bloodBankAppealList);
                recycler.setAdapter(bloodBankAppealAdapter);
                bloodBankAppealAdapter.notifyDataSetChanged();
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

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

