package com.android.zore3x.newspaper.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.adapter.NewsAdapter;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.presenter.TopHeadlinesPresenter;
import com.android.zore3x.newspaper.view.TopHeadlinesView;

import java.util.List;

public class TopHeadlinesActivity extends AppCompatActivity implements TopHeadlinesView {

    private NewsAdapter mAdapter = new NewsAdapter();
    private RecyclerView mNewLineRecyclerView;

    private TopHeadlinesPresenter mPresenter;

    public static Intent newInstance(Context context) {
        return new Intent(context, TopHeadlinesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_headlines);

        mPresenter = new TopHeadlinesPresenter();

        mNewLineRecyclerView = findViewById(R.id.topheadlines_recyclerView);
        mNewLineRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mNewLineRecyclerView.setAdapter(mAdapter);

        mPresenter.attach(this);
        mPresenter.loadData();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.menu_news_paper_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_by_popularity: {

                return true;
            }
            case R.id.action_sort_by_newest:{

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
