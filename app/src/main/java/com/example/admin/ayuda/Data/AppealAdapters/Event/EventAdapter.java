package com.example.admin.ayuda.Data.AppealAdapters.Event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Model.Event;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<Event> eventList;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_fragment_cardview,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        Event newEvent = eventList.get(position);

        String imageDp = newEvent.getPicProof();
        Picasso.with(context).load(imageDp).into(holder.imageDp);
        holder.eventTitle.setText(String.format("Title : %s is organizing %s event",newEvent.getEventOrgName(),newEvent.getEventTitle()));





    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageDp;
        public TextView eventTitle;
        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            imageDp = itemView.findViewById(R.id.EventListPictureUploadImageView);
            eventTitle = itemView.findViewById(R.id.EventListTitlePlainText) ;
        }
    }
}
