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

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.adapter.NewsAdapter;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.model.api.SortBy;
import com.android.zore3x.newspaper.presenter.EverythingPresenter;
import com.android.zore3x.newspaper.view.EverythingView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EverythingActivity extends AppCompatActivity implements EverythingView {

    public static Intent newInstance(Context context) {
        return new Intent(context, EverythingActivity.class);
    }

    private RecyclerView mEverythingRecyclerView;
    private EverythingPresenter mPresenter;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everything);

        mAdapter = new NewsAdapter();
        mPresenter = new EverythingPresenter();

        mEverythingRecyclerView = findViewById(R.id.everything_recyclerView);
        mEverythingRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mEverythingRecyclerView.setAdapter(mAdapter);

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
        new MenuInflater(getApplicationContext()).inflate(R.menu.menu_everything_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_by_popularity: {
                mPresenter.loadSortedData(SortBy.POPULARITY);
                return true;
            }
            case R.id.action_sort_by_published_at:{
                mPresenter.loadSortedData(SortBy.PUBLISHED_AT);
                return true;
            }
            case R.id.action_sort_by_relevancy: {
                mPresenter.loadSortedData(SortBy.RELEVANCY);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
