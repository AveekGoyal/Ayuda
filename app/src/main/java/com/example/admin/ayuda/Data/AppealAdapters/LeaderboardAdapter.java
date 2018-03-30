package com.example.admin.ayuda.Data.AppealAdapters;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.ayuda.Activity.LeaderBoard;
import com.example.admin.ayuda.Model.Event;
import com.example.admin.ayuda.Model.LeaderboardModel;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by asus on 3/31/2018.
 */

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private Context context;
    private List<LeaderboardModel> leaderBoard;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    public LeaderboardAdapter(Context context, List<LeaderboardModel> leaderBoard) {
        this.context = context;
        this.leaderBoard = leaderBoard;
    }

    @Override
    public LeaderboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_board_cardview , parent , false);
        return new ViewHolder(view , context);
    }

    @Override
    public void onBindViewHolder(LeaderboardAdapter.ViewHolder holder, int position) {

//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
//        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference();
        LeaderboardModel leaderboardModel = leaderBoard.get(position);

        String imageDp = leaderboardModel.getImageDp();
        Picasso.with(context).load(imageDp).into(holder.imageDp);
        holder.rank.setText(String.format("%s" , leaderboardModel.getRank()));

        holder.points.setText(String.format("%s" , leaderboardModel.getPoints()));
        holder.orgName.setText(String.format("%s" , leaderboardModel.getOrgName()));


    }

    @Override
    public int getItemCount() {
        return leaderBoard.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView rank;
        private ImageView imageDp;
        public TextView orgName;
        public TextView points;

        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            context  = ctx;
            imageDp = itemView.findViewById(R.id.leaderboardImageDp);
            rank = itemView.findViewById(R.id.leaderboardRank);
            orgName = itemView.findViewById(R.id.leaderboardOrgName);
            points = itemView.findViewById(R.id.leaderboardPoints);


        }
    }
}
