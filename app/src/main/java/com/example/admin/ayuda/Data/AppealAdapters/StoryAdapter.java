package com.example.admin.ayuda.Data.AppealAdapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Activity.Appeal.BloodBankAppealDetailActivity;
import com.example.admin.ayuda.Activity.MainNavigationActivity;
import com.example.admin.ayuda.Activity.Story.StoryDetailsActivity;
import com.example.admin.ayuda.Activity.Story.StoryListFragment;
import com.example.admin.ayuda.Data.AppealAdapters.Event.EventAdapter;
import com.example.admin.ayuda.Model.BloodBankAppeal;
import com.example.admin.ayuda.Model.Event;
import com.example.admin.ayuda.Model.Story;
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
    public void onBindViewHolder(final StoryAdapter.ViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        final Story story = storyList.get(position);

        final String uId = mUser.getUid();

        DatabaseReference getType = FirebaseDatabase.getInstance().getReference().child("NgoAdmin").child(uId);
        getType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 String type =  dataSnapshot.child("type").getValue(String.class);
                if(type == null)
                {
                    String fName = story.getFirstName();
                    String lName = story.getLastName();
                    if(fName == null && lName == null)
                    {
                        holder.storyUser.setText(String.format("By: %s" , story.getOrgName()));
                        String image = story.getStoryImage();
                        Picasso.with(context).load(image).into(holder.storyImage);
                        java.text.DateFormat dateFormat = getDateInstance();
                        String formattedDate = dateFormat.format(new Date(Long.valueOf(story.getTimestamp())).getTime());
                        holder.timestamp.setText(String.format("Created On%s", formattedDate));


                    }
                    else
                    {
                        holder.storyUser.setText(String.format("By: %s %s" , story.getFirstName(), story.getLastName()));
                        String image = story.getStoryImage();
                        Picasso.with(context).load(image).into(holder.storyImage);
                        java.text.DateFormat dateFormat = getDateInstance();
                        String formattedDate = dateFormat.format(new Date(Long.valueOf(story.getTimestamp())).getTime());
                        holder.timestamp.setText(String.format("Created On%s", formattedDate));


                    }

                }
                else if(type.equals("NgoAdmin"))
                {
                    String orgName = story.getOrgName();
                    if(orgName== null)
                    {
                        holder.storyUser.setText(String.format("By: %s %s" , story.getFirstName(), story.getLastName()));
                        String image = story.getStoryImage();
                        Picasso.with(context).load(image).into(holder.storyImage);
                        java.text.DateFormat dateFormat = getDateInstance();
                        String formattedDate = dateFormat.format(new Date(Long.valueOf(story.getTimestamp())).getTime());
                        holder.timestamp.setText(String.format("Created On%s", formattedDate));


                    }
                    else
                    {
                        holder.storyUser.setText(String.format("By: %s" ,story.getOrgName()));
                        String image = story.getStoryImage();
                        Picasso.with(context).load(image).into(holder.storyImage);
                        java.text.DateFormat dateFormat = getDateInstance();
                        String formattedDate = dateFormat.format(new Date(Long.valueOf(story.getTimestamp())).getTime());
                        holder.timestamp.setText(String.format("Created On%s", formattedDate));


                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView storyImage;
        public TextView storyUser;
        public  TextView timestamp;

        public ViewHolder(View itemView , final Context ctx) {
            super(itemView);
            context = ctx;
            storyImage = itemView.findViewById(R.id.StoryListPictureUploadImageView);
            storyUser = itemView.findViewById(R.id.StoryListUserNamePlainText);
            timestamp= itemView.findViewById(R.id.StoryTimestamp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Story story = storyList.get(getAdapterPosition());
                    Intent intent = new Intent((context) , StoryDetailsActivity.class);

                    intent.putExtra("storyImage" , storyList.get(getAdapterPosition()).getStoryImage());
                    intent.putExtra("caption" , storyList.get(getAdapterPosition()).getCaption());
                    intent.putExtra("timestamp" , storyList.get(getAdapterPosition()).getTimestamp());
                    ctx.startActivity(intent);

                }
            });

        }
    }
}
