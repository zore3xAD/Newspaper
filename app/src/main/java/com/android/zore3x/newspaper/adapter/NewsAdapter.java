package com.android.zore3x.newspaper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.PhotoViewHolder> {

    private List<Article> mNewsList = new ArrayList<>();

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news_item,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bind(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNewsList(List<Article> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        private TextView mSourceTextView;
        private TextView mNewsTitleTextView;
        private TextView mDescriptionTextView;
        private ImageView mNewsImageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            mSourceTextView = itemView.findViewById(R.id.source_name_textView);
            mNewsTitleTextView = itemView.findViewById(R.id.news_title_textView);
            mDescriptionTextView = itemView.findViewById(R.id.news_description_textView);
            mNewsImageView = itemView.findViewById(R.id.news_imageView);
        }

        public void bind(Article data) {
            mSourceTextView.setText(data.getSource().getName());
            mNewsTitleTextView.setText(data.getTitle());
            mDescriptionTextView.setText(data.getDescription());

            Picasso.get().load(data.getUrlToImage())
                    .placeholder(R.drawable.placeholder_photo)
                    .into(mNewsImageView);
        }
    }
}
