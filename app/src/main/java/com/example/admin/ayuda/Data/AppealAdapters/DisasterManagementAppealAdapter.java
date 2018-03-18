package com.example.admin.ayuda.Data.AppealAdapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ayuda.Model.DisasterAppeal;

import java.util.List;

/**
 * Created by Admin on 16-Mar-18.
 */

public class DisasterManagementAppealAdapter extends RecyclerView.Adapter<DisasterManagementAppealAdapter.ViewHolder> {

    private List<DisasterAppeal> disasterAppealList;


    public DisasterManagementAppealAdapter(FragmentActivity activity, List<DisasterAppeal> disasterAppealList) {
    }

    @Override
    public DisasterManagementAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DisasterManagementAppealAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
