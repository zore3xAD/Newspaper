package com.android.zore3x.newspaper.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.model.database.Favorite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<Favorite> mNewsList = new ArrayList<>();

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoriteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news_item,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        holder.bind(mNewsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNewsList(List<Favorite> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private TextView mSourceTextView;
        private TextView mNewsTitleTextView;
        private TextView mDescriptionTextView;
        private ImageView mNewsImageView;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            mSourceTextView = itemView.findViewById(R.id.source_name_textView);
            mNewsTitleTextView = itemView.findViewById(R.id.news_title_textView);
            mDescriptionTextView = itemView.findViewById(R.id.news_description_textView);
            mNewsImageView = itemView.findViewById(R.id.news_imageView);
        }

        public void bind(Favorite data) {
            mSourceTextView.setText(data.getSourceName());
            mNewsTitleTextView.setText(data.getTitle());
            mDescriptionTextView.setText(data.getDescription());

            mNewsImageView.setImageDrawable(Drawable.createFromPath(data.getFilePath()));
        }
    }

}
