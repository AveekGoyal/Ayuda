package com.example.admin.ayuda.Data.AppealAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Activity.Appeal.OldAgeAppealDetailsActivity;
import com.example.admin.ayuda.Model.OldAgeHomeAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by asus on 3/19/2018.
 */

public class OldAgeAppealAdapter extends RecyclerView.Adapter<OldAgeAppealAdapter.ViewHolder> {

    private Context context;
    private List<OldAgeHomeAppeal> oldAgeHomeAppealList;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public OldAgeAppealAdapter(Context context, List<OldAgeHomeAppeal> oldAgeHomeAppealList) {
        this.context = context;
        this.oldAgeHomeAppealList = oldAgeHomeAppealList;
    }



    @Override
    public OldAgeAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_list_fragment_cardview,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(OldAgeAppealAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageDp;
        public TextView appealUsername;
        public ImageView appealPic;
        public TextView appealTitle;
        public TextView appealDate;
        String userId = null;

        public ViewHolder(View itemView , final Context ctx) {
            super(itemView);
            context = ctx;
            imageDp = itemView.findViewById(R.id.AppealListDpImageView);
            imageDp = itemView.findViewById(R.id.AppealListDpImageView);
            appealUsername = itemView.findViewById(R.id.AppealListUsernamePlainText);
            appealPic = itemView.findViewById(R.id.AppealListPictureImageView);
            appealTitle = itemView.findViewById(R.id.AppealListTitlePlainText);
            appealDate = itemView.findViewById(R.id.AppealListDateCreatedPlainText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        OldAgeHomeAppeal oldAgeHomeAppeal = oldAgeHomeAppealList.get(getAdapterPosition());

                        Intent intent = new Intent(context , OldAgeAppealDetailsActivity.class);

                    //Here
                        //intent.putExtra Waala funtion bacha hai yaha!!!!
                    //here


                }
            });
        }
    }
}
