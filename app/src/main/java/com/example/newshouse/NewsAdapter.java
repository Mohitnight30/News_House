package com.example.newshouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<Articles> newsDataArrayList;
    ListItemClickListener itemClickListener;

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.news_layout, parent, false);

        NewsViewHolder newsViewHolder = new NewsViewHolder(itemView);

        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        Articles articles = newsDataArrayList.get(position);
        String date=String.valueOf(articles.getPublishedAt());
        String formateDate= date.toString().substring(0,10);

        holder.newsTitle.setText(String.valueOf(articles.getTitle()));
        holder.newsDescription.setText(String.valueOf(articles.getDescription()));
        holder.newsDate.setText(formateDate);
        Glide.with(context).load(articles.getUrlToImage()).into(holder.newsImageViews);

    }

    @Override
    public int getItemCount() {
        return newsDataArrayList.size();
    }

    public interface ListItemClickListener {
        void onListItemClickListener(int clickedItemIndex);
    }

    public NewsAdapter(Context context, ArrayList<Articles> newsDataArrayList, ListItemClickListener itemClickListener) {
        this.context = context;
        this.newsDataArrayList= newsDataArrayList;
        this.itemClickListener=itemClickListener;
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView newsImageViews;
        public TextView newsTitle, newsDescription, newsDate;

        public NewsViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            this.newsTitle= (TextView) itemView.findViewById(R.id.news_Title);
            this.newsDescription= (TextView) itemView.findViewById(R.id.news_Description);
            this.newsDate= (TextView) itemView.findViewById(R.id.news_Date);
            this.newsImageViews= (ImageView) itemView.findViewById(R.id.news_image_view);

        }

        @Override
        public void onClick(View view) {
            int pos=getAdapterPosition();
            itemClickListener.onListItemClickListener(pos);
        }
    }
}
