package com.example.admin.ayuda.Data.AppealAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Model.CommunityAppeal;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 3/18/2018.
 */

public class CommunityDevelopmentAppealAdapter extends RecyclerView.Adapter<CommunityDevelopmentAppealAdapter.ViewHolder> {

    private Context context;
    private List<CommunityAppeal> communityAppealList;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    public CommunityDevelopmentAppealAdapter(Context context, List<CommunityAppeal> communityAppealList) {
        this.context = context;
        this.communityAppealList = communityAppealList;

    }

    public CommunityDevelopmentAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_list_fragment_cardview, parent, false);

        return new ViewHolder(view, context);
    }

    public void onBindViewHolder(CommunityDevelopmentAppealAdapter.ViewHolder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        CommunityAppeal communityAppeal = communityAppealList.get(position);
        String imageUrl = null;

        final String uId = mUser.getUid();

        holder.appealUsername.setText(String.format("%s %s", communityAppeal.getAppealFirstName(), communityAppeal.getAppealLastName()));
        String imageDp = communityAppeal.getAppealImageDp();
        Picasso.with(context).load(imageDp).into(holder.imageDp);
        holder.appealTitle.setText(String.format("Title: Issues %s ", communityAppeal.getDesc()));
        java.text.DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(communityAppeal.getTimestamp())).getTime());

        holder.appealDate.setText(String.format("Created On: %s", formattedDate));
        imageUrl = communityAppeal.getPicProof();

        Picasso.with(context).load(imageUrl).into(holder.appealPic);


    }

    public int getItemCount() {
        return communityAppealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageDp;
        public TextView appealUsername;
        public ImageView appealPic;
        public TextView appealTitle;
        public TextView appealDate;
        String userId = null;


        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            imageDp = itemView.findViewById(R.id.AppealListDpImageView);
            appealUsername = itemView.findViewById(R.id.AppealListUsernamePlainText);
            appealPic = itemView.findViewById(R.id.AppealListPictureImageView);
            appealTitle = itemView.findViewById(R.id.AppealListTitlePlainText);
            appealDate = itemView.findViewById(R.id.AppealListDateCreatedPlainText);
            itemView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    CommunityAppeal communityAppeal = communityAppealList.get(getAdapterPosition());

                    Intent intent = new Intent(context, CommunityDevelopmentAppealAdapter.class);
                    intent.putExtra("appealPic", communityAppealList.get(getAdapterPosition()).getPicProof());
                    intent.putExtra("appealTitle", communityAppealList.get(getAdapterPosition()).getDesc());
                    intent.putExtra("Cleaning", communityAppealList.get(getAdapterPosition()).getCleaning());
                    intent.putExtra("Hunger", communityAppealList.get(getAdapterPosition()).getHunger());
                    intent.putExtra("HealthIssues", communityAppealList.get(getAdapterPosition()).getHealthIssues());
                    intent.putExtra("Poverty", communityAppealList.get(getAdapterPosition()).getPoverty());
                    intent.putExtra("ContactNo", communityAppealList.get(getAdapterPosition()).getContactNo());
                    ctx.startActivity(intent);

                }
            });
        }
    }
}

