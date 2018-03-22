package com.example.admin.ayuda.Data.AppealAdapters.News;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.ayuda.Activity.News.NewsDetailsActivity;
import com.example.admin.ayuda.Data.AppealAdapters.BloodBankAppealAdapter;
import com.example.admin.ayuda.Data.AppealAdapters.Event.EventAdapter;
import com.example.admin.ayuda.Model.Event;
import com.example.admin.ayuda.Model.News;
import com.example.admin.ayuda.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.admin.ayuda.R.*;

/**
 * Created by HP on 3/20/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<News> newsList;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_fragment_cardview, parent, false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        News newNews = newsList.get(position);

        String imageUrl= null;

        final String uId = mUser.getUid();


        imageUrl =newNews.getPicProof();
        Picasso.with(context).load(imageUrl).into(holder.newsPic);
        holder.newsHeadline.setText(String.format("Headline : %s is uploading %s important news",newNews.getNewsOrgName(), newNews.getNewsHeadline()));
        holder.newsDescription.setText(String.format("Description: %s ",newNews.getNewsDescription()));

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView newsPic;
        public TextView newsHeadline;
        public TextView newsDescription;

        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            context = ctx;
            newsPic = itemView.findViewById(R.id.NewsListPicProofImageView);
            newsHeadline = itemView.findViewById(R.id.NewsListHeadlinePlainText);
            newsDescription = itemView.findViewById(id.NewsListDescriptionPlainText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = newsList.get(getAdapterPosition());

                    Intent intent = new Intent(context, NewsDetailsActivity.class);

                    intent.putExtra("newsPic", newsList.get(getAdapterPosition()).getPicProof());
                    intent.putExtra("newsHeadline",newsList.get(getAdapterPosition()).getNewsHeadline());
                    intent.putExtra("newsDescription",newsList.get(getAdapterPosition()).getNewsDescription());
                    ctx.startActivity(intent);
                }
            });
        }
    }
}
