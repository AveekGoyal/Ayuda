package com.example.admin.ayuda.Activity.Appeal;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ayuda.R;

public class AppealListFragment extends Fragment {
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
        return inflater.inflate(R.layout.appeal_list_fragment, container, false);
    }
}
