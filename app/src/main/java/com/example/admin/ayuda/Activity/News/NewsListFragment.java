package com.example.admin.ayuda.Activity.News;

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
import android.widget.Spinner;

import com.example.admin.ayuda.Data.AppealAdapters.News.NewsAdapter;
import com.example.admin.ayuda.Model.News;
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


public class NewsListFragment extends Fragment {

    private FirebaseDatabase mDatabase;
    private RecyclerView recycler;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Spinner chooseNewsCategory;
    private NewsAdapter addNewsAdapter;
    private List<News> newsList;
    String type=" ";


    public static NewsListFragment newInstance()
    {
        NewsListFragment fragment = new NewsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.news_list_fragment, container,false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        newsList = new ArrayList<>();
        recycler = view.findViewById(R.id.newsListFragmentRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        String userId = mUser.getUid();


        //checking user Type from Firebase. If it is NgoAdmin then Floating button is visible otherwise not.
        DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child("NgoAdmin").child(userId);
        getType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                type = dataSnapshot.child("type").getValue(String.class);
                if (type == null)
                {
                    FloatingActionButton addNewsBtn = view.findViewById(R.id.NewsListFloatingActionButton);
                    addNewsBtn.setVisibility(View.GONE);
                }
                else if (type.equals("NgoAdmin"))
                {
                    FloatingActionButton addNewsBtn = view.findViewById(R.id.NewsListFloatingActionButton);
                    addNewsBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mAuth!= null && mUser != null)
                            {
                                startActivity(new Intent(getActivity(), AddNewsActivity.class));
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Clearing the list.
        newsList.clear();

        getNewsData();


        return view;
    }

    private void getNewsData() {
        mDatabase.getReference().child("News").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Fetch Data from adapter.
                News news = dataSnapshot.getValue(News.class);
                newsList.add(news);
                addNewsAdapter = new NewsAdapter(getActivity(),newsList);
                recycler.setAdapter(addNewsAdapter);
                addNewsAdapter.notifyDataSetChanged();


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
