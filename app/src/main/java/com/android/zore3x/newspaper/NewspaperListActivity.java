package com.android.zore3x.newspaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.zore3x.newspaper.adapter.NewsAdapter;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.presenter.NewspaperPresenter;
import com.android.zore3x.newspaper.view.NewspaperView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewspaperListActivity extends AppCompatActivity implements NewspaperView {

    private NewsAdapter mAdapter = new NewsAdapter();
    private RecyclerView mNewLineRecyclerView;

    private NewspaperPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspaper_list);

        mPresenter = new NewspaperPresenter();

        mNewLineRecyclerView = findViewById(R.id.newspaper_recyclerView);
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
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
