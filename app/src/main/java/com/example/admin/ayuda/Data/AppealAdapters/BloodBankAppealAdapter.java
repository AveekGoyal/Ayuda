package com.example.admin.ayuda.Data.AppealAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Model.BloodBankAppeal;
import com.example.admin.ayuda.Model.NgoAdmin;
import com.example.admin.ayuda.Model.NonMember;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import static java.text.DateFormat.getDateInstance;


public class BloodBankAppealAdapter extends RecyclerView.Adapter<BloodBankAppealAdapter.ViewHolder> {


    private Context context;
    private List<BloodBankAppeal> bloodBankAppealList;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    public BloodBankAppealAdapter(Context context, List<BloodBankAppeal> bloodBankAppealList) {
        this.context = context;
        this.bloodBankAppealList = bloodBankAppealList;
    }

    @Override
    public BloodBankAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_list_fragment_cardview,parent,false);


        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(final BloodBankAppealAdapter.ViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        BloodBankAppeal bloodBankAppeal = bloodBankAppealList.get(position);
        String imageUrl= null;

        final String uId = mUser.getUid();



        holder.appealUsername.setText(String.format("%s %s", bloodBankAppeal.getAppealFirstName(), bloodBankAppeal.getAppealLastName()));
        String imageDp = bloodBankAppeal.getAppealImageDp();
        Picasso.with(context).load(imageDp).into(holder.imageDp);
        holder.appealTitle.setText(String.format("Title: %s Needs blood", bloodBankAppeal.getPatientName()));
        java.text.DateFormat dateFormat = getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(bloodBankAppeal.getTimestamp())).getTime());
        holder.appealDate.setText(String.format("Created On%s", formattedDate));
        imageUrl =bloodBankAppeal.getPicProof();

        Picasso.with(context).load(imageUrl).into(holder.appealPic);




    }

    @Override
    public int getItemCount() {
        return bloodBankAppealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageDp;
        public TextView appealUsername;
        public ImageView appealPic;
        public TextView appealTitle;
        public TextView appealDate;
        String userId = null;

        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            imageDp = itemView.findViewById(R.id.AppealListDpImageView);
            appealUsername = itemView.findViewById(R.id.AppealListUsernamePlainText);
            appealPic = itemView.findViewById(R.id.AppealListPictureImageView);
            appealTitle = itemView.findViewById(R.id.AppealListTitlePlainText);
            appealDate = itemView.findViewById(R.id.AppealListDateCreatedPlainText);


        }
    }
}
