package com.android.zore3x.newspaper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.adapter.NewsAdapter;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.api.TopHeadlinesQuery;
import com.android.zore3x.newspaper.model.api.Category;
import com.android.zore3x.newspaper.model.api.Country;
import com.android.zore3x.newspaper.presenter.TopHeadlinesPresenter;
import com.android.zore3x.newspaper.view.TopHeadlinesView;

import java.util.List;

public class TopHeadlinesActivity extends AppCompatActivity implements TopHeadlinesView {

    private NewsAdapter mAdapter = new NewsAdapter();
    private RecyclerView mTopHeadlinesRecyclerView;

    private TopHeadlinesPresenter mPresenter;

    private TopHeadlinesQuery mTopHeadlinesQuery = new TopHeadlinesQuery();

    public static Intent newInstance(Context context) {
        return new Intent(context, TopHeadlinesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_headlines);

        mTopHeadlinesQuery.setSources("google-news-ru");

        mTopHeadlinesQuery.setCategory(Category.HEALTH);
        mTopHeadlinesQuery.setCountry(Country.RU);

        mPresenter = new TopHeadlinesPresenter();

        mTopHeadlinesRecyclerView = findViewById(R.id.topheadlines_recyclerView);
        mTopHeadlinesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mTopHeadlinesRecyclerView.setAdapter(mAdapter);

        mPresenter.attach(this);
        mPresenter.loadData(mTopHeadlinesQuery);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<Article> data) {
        mAdapter.setNewsList(data);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
