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


public class AppealAcceptedByNgoAdapter extends RecyclerView.Adapter<AppealAcceptedByNgoAdapter.ViewHolder> {

    private Context context;
    private List<Ngo_Appeals> ngo_appealsList;
    private FirebaseDatabase mDatabase;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;

    public AppealAcceptedByNgoAdapter(Context context, List<Ngo_Appeals> ngo_appealsList) {
        this.context = context;
        this.ngo_appealsList = ngo_appealsList;
    }

    @Override
    public AppealAcceptedByNgoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appeal_accepted_by_ngo_cardview, parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        Ngo_Appeals ngo_appeals = ngo_appealsList.get(position);
        holder.appealTitle.setText(String.format("%s needs blood", ngo_appeals.getAppealName()));
        holder.timestamp.setText(String.format("%s",ngo_appeals.getAppealTimestamp()));
        String imageUrl = ngo_appeals.getAppealImageDp();
        Picasso.with(context).load(imageUrl).into(holder.imageDp);

    }

    @Override
    public int getItemCount() {
        return ngo_appealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageDp;
        public TextView appealTitle;
        public TextView timestamp;


        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            context= ctx;
            imageDp = itemView.findViewById(R.id.AppealAcceptedPicImageView);
            appealTitle = itemView.findViewById(R.id.AppealAcceptedTitlePlainText);
            timestamp = itemView.findViewById(R.id.AppealAcceptedAppealTimestampPlainText);

        }
    }
}
