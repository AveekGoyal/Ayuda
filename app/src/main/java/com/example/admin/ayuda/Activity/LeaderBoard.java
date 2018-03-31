package com.example.admin.ayuda.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admin.ayuda.Data.AppealAdapters.LeaderboardAdapter;
import com.example.admin.ayuda.Model.LeaderboardModel;
import com.example.admin.ayuda.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class LeaderBoard extends AppCompatActivity {


    private List<LeaderboardModel> leaderboardModels;
    private LeaderboardAdapter leaderboardAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        recyclerView = findViewById(R.id.leaderboardRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

       //new leaderboardlist
        leaderboardModels = new ArrayList<>();

        //add value to list
        leaderboardModels.add(new LeaderboardModel("1", "https://firebasestorage.googleapis.com/v0/b/ayuda-28fe1.appspot.com/o/NgoAdmin_Pics%2FNgoAdmin_Pics%2Fcropped1953335107.jpg?alt=media&token=38ebe887-afd2-4224-851d-54ccefcdbbb3" , "new World" , "Points:500"));
        leaderboardModels.add(new LeaderboardModel("2" , "https://firebasestorage.googleapis.com/v0/b/ayuda-28fe1.appspot.com/o/NgoAdmin_Pics%2FNgoAdmin_Pics%2Fcropped-1385368712.jpg?alt=media&token=244d1689-fe5e-4f1e-8df2-dfbe3efcd553" , "Health Ngo" , "Points:450"));
        leaderboardModels.add(new LeaderboardModel("3" , "https://firebasestorage.googleapis.com/v0/b/ayuda-28fe1.appspot.com/o/NgoAdmin_Pics%2FNgoAdmin_Pics%2Fcropped2089099275.jpg?alt=media&token=dc23cf8f-4960-4b49-aaac-ad99f2521f3b" , "Helping Hands" , "Points:200"));
        leaderboardModels.add(new LeaderboardModel("4" , "https://firebasestorage.googleapis.com/v0/b/ayuda-28fe1.appspot.com/o/NgoAdmin_Pics%2FNgoAdmin_Pics%2Fcropped1309539454.jpg?alt=media&token=0d67f4cc-a772-4bc3-9ef8-9534d8abe4f3" , "Helping People" , "Points:100" ));
        //pass that value to adapter



        leaderboardAdapter = new LeaderboardAdapter(getApplicationContext() ,leaderboardModels );
        recyclerView.setAdapter(leaderboardAdapter);
        leaderboardAdapter.notifyDataSetChanged();


    }
}
