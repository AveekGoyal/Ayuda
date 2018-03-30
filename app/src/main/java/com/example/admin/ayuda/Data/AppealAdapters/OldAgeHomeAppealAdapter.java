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
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import static java.text.DateFormat.getDateInstance;

/**
 * Created by HP on 3/20/2018.
 */

public class OldAgeHomeAppealAdapter extends RecyclerView.Adapter<OldAgeHomeAppealAdapter.ViewHolder> {

    private Context context;
    private List<OldAgeHomeAppeal> oldAgeHomeAppealList;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    public OldAgeHomeAppealAdapter(Context context, List<OldAgeHomeAppeal> oldAgeHomeAppealList) {
        this.context = context;
        this.oldAgeHomeAppealList = oldAgeHomeAppealList;
    }

    @Override
    public OldAgeHomeAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_list_fragment_cardview, parent,false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(OldAgeHomeAppealAdapter.ViewHolder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        OldAgeHomeAppeal oldAgeHomeAppeal = oldAgeHomeAppealList.get(position);
        String imageUrl = null;

        final String uId = mUser.getUid();

        holder.appealUsername.setText(String.format("%s %s", oldAgeHomeAppeal.getAppealFirstName(), oldAgeHomeAppeal.getAppealLastName()));
        String imageDp = oldAgeHomeAppeal.getAppealImageDp();
        Picasso.with(context).load(imageDp).into(holder.imageDp);
        holder.appealTitle.setText(String.format("Title: Needs Help %s", oldAgeHomeAppeal.getDescription()));
        java.text.DateFormat dateFormat = getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(oldAgeHomeAppeal.getTimestamp())).getTime());

        holder.appealDate.setText(String.format("Created On: %s", formattedDate));
        imageUrl = oldAgeHomeAppeal.getPicProof();

        Picasso.with(context).load(imageUrl).into(holder.appealPic);


    }

    @Override
    public int getItemCount() {
        return oldAgeHomeAppealList.size();
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
                @Override
                public void onClick(View v) {

                    OldAgeHomeAppeal oldAgeHomeAppeal = oldAgeHomeAppealList.get(getAdapterPosition());

                    Intent intent = new Intent(context, OldAgeAppealDetailsActivity.class);
                    intent.putExtra("appealPic", oldAgeHomeAppealList.get(getAdapterPosition()).getPicProof());
                    intent.putExtra("appealTitle", oldAgeHomeAppealList.get(getAdapterPosition()).getDescription());
                    intent.putExtra("address", oldAgeHomeAppealList.get(getAdapterPosition()).getAddress());
                    intent.putExtra("financialNeeds", oldAgeHomeAppealList.get(getAdapterPosition()).getFinancialNeeds());
                    intent.putExtra("medicalNeeds", oldAgeHomeAppealList.get(getAdapterPosition()).getMedicalNeeds());
                    intent.putExtra("livelihoodNeeds", oldAgeHomeAppealList.get(getAdapterPosition()).getLivelihoodNeeds());
                    intent.putExtra("timestamp",oldAgeHomeAppealList.get(getAdapterPosition()).getTimestamp());
                    intent.putExtra("isAccepted",oldAgeHomeAppealList.get(getAdapterPosition()).getIsAccepted());
                    ctx.startActivity(intent);
                }
            });
        }
    }
}
