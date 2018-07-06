package com.android.zore3x.newspaper.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.database.Favorite;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> mNewsList = new ArrayList<>();

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news_item,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
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

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView mSourceTextView;
        private TextView mNewsTitleTextView;
        private TextView mDescriptionTextView;
        private ImageView mNewsImageView;
        private ImageView mAddToFavoriteButton;
        private ShareButton mShareButton;

        CallbackManager mCallbackManager;
        ShareDialog mShareDialog;

        Article article;

        public NewsViewHolder(View itemView) {
            super(itemView);



            mSourceTextView = itemView.findViewById(R.id.source_name_textView);
            mNewsTitleTextView = itemView.findViewById(R.id.news_title_textView);
            mDescriptionTextView = itemView.findViewById(R.id.news_description_textView);
            mNewsImageView = itemView.findViewById(R.id.news_imageView);
            mAddToFavoriteButton = itemView.findViewById(R.id.add_to_favorite_imageView);
            mAddToFavoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // путь к изображению
                    final String filePath = view.getContext().getCacheDir().getPath() + article.getFilePath();

                    // загрузка изоюражения
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                URL url = new URL(article.getUrlToImage());
                                InputStream in = new BufferedInputStream(url.openStream());
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                byte[] buf = new byte[1024];
                                int n = 0;
                                while (-1!=(n=in.read(buf))) {
                                    out.write(buf, 0, n);
                                }
                                out.close();
                                in.close();
                                byte[] response = out.toByteArray();

                                FileOutputStream fos = new FileOutputStream(filePath);
                                fos.write(response);
                                fos.close();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    // добавление в базу данных
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            App.getAppDatabase().mFavoriteDao().insert(new Favorite(article.getTitle(),
                                    article.getDescription(), filePath,
                                    article.getSource().getName()));
                        }
                    }).start();
                    Toast.makeText(view.getContext(), "Add to favorite", Toast.LENGTH_SHORT).show();
                }
            });
            mShareButton = itemView.findViewById(R.id.share_link_button);
            mShareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShareDialog shareDialog = new ShareDialog((Activity)view.getContext());

                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setQuote(article.getTitle())
                            .setContentUrl(Uri.parse(article.getUrl()))
                            .build();
                    if(shareDialog.canShow(ShareLinkContent.class)) {
                        shareDialog.show(linkContent);
                    }
                }
            });
        }

        public void bind(Article data) {

            article = data;

            mSourceTextView.setText(data.getSource().getName());
            mNewsTitleTextView.setText(data.getTitle());
            mDescriptionTextView.setText(data.getDescription());

            Picasso.get().load(data.getUrlToImage())
                    .placeholder(R.drawable.placeholder_photo)
                    .into(mNewsImageView);
        }

    }
}
