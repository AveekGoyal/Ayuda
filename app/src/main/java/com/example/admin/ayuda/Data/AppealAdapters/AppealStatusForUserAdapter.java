package com.example.admin.ayuda.Data.AppealAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Model.Ngo_Appeals;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 25-Mar-18.
 */

public class AppealStatusForUserAdapter extends RecyclerView.Adapter<AppealStatusForUserAdapter.ViewHolder> {
    private Context context;
    private List<Ngo_Appeals> ngo_appealsList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;


    public AppealStatusForUserAdapter(Context context, List<Ngo_Appeals> ngo_appealsList) {
        this.context = context;
        this.ngo_appealsList = ngo_appealsList;
    }

    @Override
    public AppealStatusForUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_status_for_user_cardview, parent,false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(AppealStatusForUserAdapter.ViewHolder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        Ngo_Appeals ngo_appeals = ngo_appealsList.get(position);
        holder.appealTitle.setText(String.format("%s", ngo_appeals.getAppealName()));
        String imageUrl = ngo_appeals.getAppealImageDp();
        Picasso.with(context).load(imageUrl).into(holder.imageView);
        holder.orgName.setText(String.format("%s", ngo_appeals.getAdminOrgName()));
        holder.contactNo.setText(String.format("%s", ngo_appeals.getAdminContactNo()));


    }

    @Override
    public int getItemCount() {
        return ngo_appealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView orgName;
        public TextView contactNo;
        public TextView appealTitle;
        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            imageView = itemView.findViewById(R.id.AppealStatusPicImageView);
            appealTitle = itemView.findViewById(R.id.AppealStatusTitlePlainText);
            orgName = itemView.findViewById(R.id.AppealStatusOrgName);
            contactNo = itemView.findViewById(R.id.AppealStatusContactNo);
        }
    }
}
