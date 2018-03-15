package com.example.admin.ayuda.Activity.Appeal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppealListFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;



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
        View view =  inflater.inflate(R.layout.appeal_list_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        FloatingActionButton addAppealbtn = view.findViewById(R.id.AppealListAddAppealFloatingButton);
        addAppealbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth != null && mUser != null) {
                    startActivity(new Intent(getActivity(), ChooseAppealCategoryActivity.class));
                }
            }
        });

        return view;
        }

}

