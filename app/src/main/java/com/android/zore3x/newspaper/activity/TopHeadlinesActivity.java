package com.android.zore3x.newspaper.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.RxSearch;
import com.android.zore3x.newspaper.adapter.NewsAdapter;
import com.android.zore3x.newspaper.dialog.CategoryListDialog;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.api.TopHeadlinesQuery;
import com.android.zore3x.newspaper.model.api.Category;
import com.android.zore3x.newspaper.model.api.Country;
import com.android.zore3x.newspaper.presenter.TopHeadlinesPresenter;
import com.android.zore3x.newspaper.view.TopHeadlinesView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.android.zore3x.newspaper.dialog.CategoryListDialog.EXTRA_CATEGORY;

public class TopHeadlinesActivity extends MvpAppCompatActivity implements TopHeadlinesView {

    private NewsAdapter mAdapter = new NewsAdapter();
    private RecyclerView mTopHeadlinesRecyclerView;
    private ProgressBar mProgressBar;
    private Button mShowMoreButton;

    @InjectPresenter
    TopHeadlinesPresenter mPresenter;
    private TopHeadlinesQuery mTopHeadlinesQuery = new TopHeadlinesQuery();

    public static Intent newInstance(Context context) {
        return new Intent(context, TopHeadlinesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_headlines);

        initQuery();

        mShowMoreButton = findViewById(R.id.load_more_top_headlines_button);
        mProgressBar = findViewById(R.id.top_headlines_progressBar);
        mTopHeadlinesRecyclerView = findViewById(R.id.topheadlines_recyclerView);
        mTopHeadlinesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mTopHeadlinesRecyclerView.setAdapter(mAdapter);
        mTopHeadlinesRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    mShowMoreButton.setVisibility(View.VISIBLE);
                } else {
                    mShowMoreButton.setVisibility(View.GONE);
                }
            }
        });

        mShowMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int page = mTopHeadlinesQuery.getPage();
                mTopHeadlinesQuery.setPage(++page);
                mPresenter.loadData(mTopHeadlinesQuery);
            }
        });
        mPresenter.loadData(mTopHeadlinesQuery);
    }

    private void initQuery() {
        mTopHeadlinesQuery.setSources("google-news-ru");

        mTopHeadlinesQuery.setCategory(Category.GENERAL);
        mTopHeadlinesQuery.setCountry(Country.RU);
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
    public void showNewPage(List<Article> data) {
        mAdapter.addNewsList(data);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_headlines_activity, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.app_bar_top_hedlines_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        // поиск по запросу
        Observable<String> searchObservable = RxSearch.getTextWatcher(searchView);
        searchObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mTopHeadlinesQuery.setQ(s);
                mPresenter.loadData(mTopHeadlinesQuery);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_select_category:

                CategoryListDialog dialog = new CategoryListDialog();
                dialog.show(getSupportFragmentManager(), "category_dialog");

                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == 1) {
                mTopHeadlinesQuery.setCategory((Category)data.getSerializableExtra(EXTRA_CATEGORY));
                mPresenter.loadData(mTopHeadlinesQuery);
            }
        }

    }
}
