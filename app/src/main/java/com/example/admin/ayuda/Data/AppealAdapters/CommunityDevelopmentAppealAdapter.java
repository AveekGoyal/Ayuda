package com.example.admin.ayuda.Data.AppealAdapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ayuda.Model.CommunityAppeal;

import java.util.List;

/**
 * Created by HP on 3/18/2018.
 */

public class CommunityDevelopmentAppealAdapter extends RecyclerView.Adapter<CommunityDevelopmentAppealAdapter.ViewHolder> {
    private List<CommunityAppeal> communityAppealList;

    public CommunityDevelopmentAppealAdapter(FragmentActivity activity, List<CommunityAppeal> communityAppealList) {

    }
    public CommunityDevelopmentAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public void onBindViewHolder(CommunityDevelopmentAppealAdapter.ViewHolder holder, int position) {

    }

    public int getItemCount() { return 0; }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) { super(itemView);}
    }
}

