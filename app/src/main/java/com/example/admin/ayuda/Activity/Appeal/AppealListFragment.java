package com.example.admin.ayuda.Activity.Appeal;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.admin.ayuda.Data.AppealAdapters.BloodBankAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.ChildLabourAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.CommunityDevelopmentAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.DisasterManagementAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.OldAgeHomeAppealAdapter;
import com.example.admin.ayuda.Model.CommunityAppeal;
import com.example.admin.ayuda.Model.DisasterAppeal;
import com.example.admin.ayuda.Model.BloodBankAppeal;
import com.example.admin.ayuda.Model.ChildAbuseAppeals;
import com.example.admin.ayuda.Model.OldAgeHomeAppeal;
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
    private DisasterManagementAppealAdapter disasterManagementAppealAdapter;
    private List<DisasterAppeal> disasterAppealList;
    private CommunityDevelopmentAppealAdapter communityDevelopmentAppealAdapter;
    private List<CommunityAppeal> communityAppealList;
    private OldAgeHomeAppealAdapter oldAgeHomeAppealAdapter;
    private List<OldAgeHomeAppeal> oldAgeHomeAppealList;
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
        disasterAppealList = new ArrayList<>();
        communityAppealList = new ArrayList<>();
        oldAgeHomeAppealList = new ArrayList<>();
        recycler = view.findViewById(R.id.appealListFragmentRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        chooseSortCategory = view.findViewById(R.id.AppealListChooseCategorySpinner);
        chooseSortCategory.setOnItemSelectedListener(this);


        List<String> categories = new ArrayList<String>();
        categories.add("Blood Bank");
        categories.add("Disaster Management");
        categories.add("Old Age");
        categories.add("Child Abuse");
        categories.add("Community Development");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
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
            disasterAppealList.clear();
            getDisasterManagementAppealData();
        }
        if(categorySelected.equals("Old Age"))
        {
            oldAgeHomeAppealList.clear();
            getOldAgeAppealData();
        }
        if(categorySelected.equals("Child Abuse"))
        {
            childAbuseAppealsList.clear();
            getChildAbuseAppealData();
        }
        if(categorySelected.equals("Community Development"))
        {
            communityAppealList.clear();
            getCommunityDevelopmentAppealData();
        }





    }

    private void getCommunityDevelopmentAppealData() {

        FirebaseDatabase.getInstance().getReference().child("CommunityDevelopmentAppeals").orderByChild("timestamp").addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CommunityAppeal communityDevelopmentAppeal = dataSnapshot.getValue(CommunityAppeal.class);
                communityAppealList.add(communityDevelopmentAppeal);
                communityDevelopmentAppealAdapter =  new CommunityDevelopmentAppealAdapter(getActivity(), communityAppealList);
                recycler.setAdapter(communityDevelopmentAppealAdapter);
                communityDevelopmentAppealAdapter.notifyDataSetChanged();

            }

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
        FirebaseDatabase.getInstance().getReference().child("OldAgeAppeal").orderByChild("timestamp").addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                OldAgeHomeAppeal oldAgeHomeAppeal = dataSnapshot.getValue(OldAgeHomeAppeal.class);
                oldAgeHomeAppealList.add(oldAgeHomeAppeal);
                oldAgeHomeAppealAdapter = new OldAgeHomeAppealAdapter(getActivity(), oldAgeHomeAppealList);
                recycler.setAdapter(oldAgeHomeAppealAdapter);
                oldAgeHomeAppealAdapter.notifyDataSetChanged();


            }

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

    private void getDisasterManagementAppealData() {
        FirebaseDatabase.getInstance().getReference().child("DisasterManagementAppeals").orderByChild("timestamp").addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DisasterAppeal disasterManagementAppeal = dataSnapshot.getValue(DisasterAppeal.class);
                disasterAppealList.add(disasterManagementAppeal);
                disasterManagementAppealAdapter = new DisasterManagementAppealAdapter(getActivity(), disasterAppealList);
                recycler.setAdapter(disasterManagementAppealAdapter);
                disasterManagementAppealAdapter.notifyDataSetChanged();
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

