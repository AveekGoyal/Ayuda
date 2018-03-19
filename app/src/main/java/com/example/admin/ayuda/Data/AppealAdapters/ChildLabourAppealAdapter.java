package com.example.admin.ayuda.Data.AppealAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Activity.Appeal.ChildLabourDetailsActivity;
import com.example.admin.ayuda.Model.ChildAbuseAppeals;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ChildLabourAppealAdapter extends RecyclerView.Adapter<ChildLabourAppealAdapter.ViewHolder>{

    private Context context;
    private List<ChildAbuseAppeals> childAbuseAppealList;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    public ChildLabourAppealAdapter(Context context, List<ChildAbuseAppeals> childAbuseAppealList) {
        this.context = context;
        this.childAbuseAppealList = childAbuseAppealList;
    }

    @Override
    public ChildLabourAppealAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_list_fragment_cardview,parent,false);

        return new ViewHolder(view , context);
    }

    @Override
    public void onBindViewHolder(ChildLabourAppealAdapter.ViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        ChildAbuseAppeals childAbuseAppeals = childAbuseAppealList.get(position);
        String imageUrl = null;

        final String uId = mUser.getUid();

        holder.appealUsername.setText(String.format("%s %s" , childAbuseAppeals.getAppealFirstName() , childAbuseAppeals.getAppealLastName()));
        String imageDp = childAbuseAppeals.getAppealImageDp();
        Picasso.with(context).load(imageDp).into(holder.imageDp);
        holder.appealTitle.setText(String.format("Title: %s  -is a problem." , childAbuseAppeals.getDescription()));
        java.text.DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(childAbuseAppeals.getTimestamp())).getTime());

        holder.appealDate.setText(String.format("Created On: %s", formattedDate));
        imageUrl =childAbuseAppeals.getPicProof();

        Picasso.with(context).load(imageUrl).into(holder.appealPic);

    }

    @Override
    public int getItemCount() {
        return childAbuseAppealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageDp;
        public TextView appealUsername;
        public ImageView appealPic;
        public TextView appealTitle;
        public TextView appealDate;
        String userId = null;

        public ViewHolder(View itemView , final  Context ctx) {
            super(itemView);
            context = ctx;
            imageDp = itemView.findViewById(R.id.AppealListDpImageView);
            appealUsername = itemView.findViewById(R.id.AppealListUsernamePlainText);
            appealPic = itemView.findViewById(R.id.AppealListPictureImageView);
            appealTitle = itemView.findViewById(R.id.AppealListTitlePlainText);
            appealDate = itemView.findViewById(R.id.AppealListDateCreatedPlainText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ChildAbuseAppeals childAbuseAppeals = childAbuseAppealList.get(getAdapterPosition());

                    Intent intent = new Intent(context , ChildLabourDetailsActivity.class);

                    intent.putExtra("appealPic" , childAbuseAppealList.get(getAdapterPosition()).getPicProof());
                    intent.putExtra("appealTitle" , childAbuseAppealList.get(getAdapterPosition()).getDescription());
                    intent.putExtra(("physicalAbuse" ), childAbuseAppealList.get(getAdapterPosition()).getPhysicalAbuse());
                    intent.putExtra("sexualAbuse" ,childAbuseAppealList.get(getAdapterPosition()).getSexualAbuse());
                    intent.putExtra("psychologicalAbuse" , childAbuseAppealList.get(getAdapterPosition()).getPsychologicalAbuse());
                    intent.putExtra("abandon" , childAbuseAppealList.get(getAdapterPosition()).getChildAbandon());
                    intent.putExtra("childLabour" , childAbuseAppealList.get(getAdapterPosition()).getChildLabour());
                    intent.putExtra("childMarriage" , childAbuseAppealList.get(getAdapterPosition()).getChildMarriage());
                    intent.putExtra("appoxAge" , childAbuseAppealList.get(getAdapterPosition()).getChildApproxAge());
                    intent.putExtra("gender" , childAbuseAppealList.get(getAdapterPosition()).getGender());
                    ctx.startActivity(intent);


                }
            });
        }
    }
}
