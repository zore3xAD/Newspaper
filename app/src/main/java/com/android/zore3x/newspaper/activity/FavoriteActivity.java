package com.android.zore3x.newspaper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.adapter.FavoriteAdapter;
import com.android.zore3x.newspaper.model.database.Favorite;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    public static Intent newInstance(Context context) {
        return new Intent(context, FavoriteActivity.class);
    }

    private RecyclerView mFavoriteRecyclerView;
    private FavoriteAdapter mFavoriteAdapter = new FavoriteAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mFavoriteRecyclerView = findViewById(R.id.favorite_recyclerView);
        mFavoriteRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mFavoriteRecyclerView.setAdapter(mFavoriteAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Favorite> favoriteList = App.getAppDatabase().mFavoriteDao().getAll();
                mFavoriteAdapter.setNewsList(favoriteList);
            }
        }).start();

    }
}
