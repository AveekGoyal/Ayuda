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

import com.example.admin.ayuda.Activity.Appeal.DisasterManagementAppealDetailsActivity;
import com.example.admin.ayuda.Model.DisasterAppeal;
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
 * Created by Admin on 16-Mar-18.
 */

public class DisasterManagementAppealAdapter extends RecyclerView.Adapter<DisasterManagementAppealAdapter.ViewHolder> {

    private Context context;
    private List<DisasterAppeal> disasterAppealList;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;


    public DisasterManagementAppealAdapter(Context context, List<DisasterAppeal> disasterAppealList) {
        this.context = context;
        this.disasterAppealList = disasterAppealList;
    }

    @Override
    public DisasterManagementAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_list_fragment_cardview,parent, false);

        return new ViewHolder(view , context);
    }

    @Override
    public void onBindViewHolder(final DisasterManagementAppealAdapter.ViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        DisasterAppeal disasterAppeal = disasterAppealList.get(position);
        String imageUrl = null;

        final String uId = mUser.getUid();

        holder.appealUsername.setText(String.format("%s %s" , disasterAppeal.getAppealFirstName(), disasterAppeal.getAppealLastName()));
        String imageDp = disasterAppeal.getAppealImageDp();
        Picasso.with(context).load(imageDp).into(holder.imageDp);
        holder.appealTitle.setText(String.format("Title: %s had occured" , disasterAppeal.getDescription()));
        java.text.DateFormat dateFormat = getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(disasterAppeal.getTimestamp())).getTime());

        holder.appealDate.setText(String.format("Created On: %s", formattedDate));
        imageUrl = disasterAppeal.getPicProof();

        Picasso.with(context).load(imageUrl).into(holder.appealPic);
    }

    @Override
    public int getItemCount() {
        return disasterAppealList.size();
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
            appealUsername = itemView.findViewById(R.id.AppealListUsernamePlainText);
            appealPic = itemView.findViewById(R.id.AppealListPictureImageView);
            appealTitle = itemView.findViewById(R.id.AppealListTitlePlainText);
            appealDate = itemView.findViewById(R.id.AppealListDateCreatedPlainText);
            itemView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    DisasterAppeal disasterAppeal = disasterAppealList.get(getAdapterPosition());

                    Intent intent = new Intent(context , DisasterManagementAppealDetailsActivity.class);

                    intent.putExtra("appealPic" , disasterAppealList.get(getAdapterPosition()).getPicProof());
                    intent.putExtra("appealTitle", disasterAppealList.get(getAdapterPosition()).getDescription());
                    intent.putExtra("needFood", disasterAppealList.get(getAdapterPosition()).getNeedFood());
                    intent.putExtra("needWater", disasterAppealList.get(getAdapterPosition()).getNeedWater());
                    intent.putExtra("needShelter", disasterAppealList.get(getAdapterPosition()).getNeedShelter());
                    intent.putExtra("needClothing", disasterAppealList.get(getAdapterPosition()).getNeedClothing());
                    intent.putExtra("needMedicalFacilities", disasterAppealList.get(getAdapterPosition()).getNeedMedical());
                    intent.putExtra("needRehabilitation", disasterAppealList.get(getAdapterPosition()).getNeedRehab());
                    intent.putExtra("typeOfDisaster" , disasterAppealList.get(getAdapterPosition()).getTypeOfDisaster());
                    intent.putExtra("contactNo", disasterAppealList.get(getAdapterPosition()).getContactNo());
                    intent.putExtra("altContactNo", disasterAppealList.get(getAdapterPosition()).getAltContactNo());
                    ctx.startActivity(intent);


                }
            });
        }
    }
}
