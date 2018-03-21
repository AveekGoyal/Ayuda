package com.example.admin.ayuda.Data.AppealAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Activity.Story.StoryDetailsActivity;
import com.example.admin.ayuda.Activity.Story.StoryListFragment;
import com.example.admin.ayuda.Data.AppealAdapters.Event.EventAdapter;
import com.example.admin.ayuda.Model.Event;
import com.example.admin.ayuda.Model.Story;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{

    private Context context;
    private List<Story> storyList;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;

    public StoryAdapter(Context context, List<Story> storyList) {
        this.context = context;
        this.storyList = storyList;
    }

    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_list_fragment_cardview,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(StoryAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView storyImage;
        public TextView storyUser;

        public ViewHolder(View itemView , final Context ctx) {
            super(itemView);
            context = ctx;
            storyImage = itemView.findViewById(R.id.StoryListPictureUploadImageView);
            storyUser = itemView.findViewById(R.id.StoryListUserNamePlainText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Story story = storyList.get(getAdapterPosition());
                    Intent intent = new Intent((context) , StoryDetailsActivity.class);

                    intent.putExtra("storyImage" , storyList.get(getAdapterPosition()).getStoryImage());
                    intent.putExtra("storyCaption" , storyList.get(getAdapterPosition()).getCaption());
                    ctx.startActivity(intent);

                }
            });

        }
    }
}
