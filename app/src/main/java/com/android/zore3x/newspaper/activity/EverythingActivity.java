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
import android.widget.Toast;

import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.RxSearch;
import com.android.zore3x.newspaper.adapter.NewsAdapter;
import com.android.zore3x.newspaper.dialog.SelectFromToDialog;
import com.android.zore3x.newspaper.dialog.SourceListDialog;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.api.EverythingQuery;
import com.android.zore3x.newspaper.model.api.SortBy;
import com.android.zore3x.newspaper.presenter.EverythingPresenter;
import com.android.zore3x.newspaper.view.EverythingView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.android.zore3x.newspaper.dialog.SelectFromToDialog.EXTRA_DATE_FROM;
import static com.android.zore3x.newspaper.dialog.SelectFromToDialog.EXTRA_DATE_TO;
import static com.android.zore3x.newspaper.dialog.SourceListDialog.EXTRA_SOURCE;

public class EverythingActivity extends MvpAppCompatActivity implements EverythingView {

    public static Intent newInstance(Context context) {
        return new Intent(context, EverythingActivity.class);
    }

    private RecyclerView mEverythingRecyclerView;
    private Button mShowMoreButton;

    @InjectPresenter
    EverythingPresenter mPresenter;
    private NewsAdapter mAdapter;

    private EverythingQuery mEverythingQuery = new EverythingQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everything);

        mAdapter = new NewsAdapter();

        mShowMoreButton = findViewById(R.id.everything_load_more_button);
        mEverythingRecyclerView = findViewById(R.id.everything_recyclerView);
        mEverythingRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mEverythingRecyclerView.setAdapter(mAdapter);
        mEverythingRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
                int page = mEverythingQuery.getPage();
                mEverythingQuery.setPage(++page);
                mPresenter.loadData(mEverythingQuery);
            }
        });
        mPresenter.loadData(mEverythingQuery);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_everything_activity, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        // поиск по запросу
        Observable<String> searchObservable = RxSearch.getTextWatcher(searchView);
        searchObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mEverythingQuery.setQ(s);
                mPresenter.loadData(mEverythingQuery);
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
        switch (item.getItemId()) {
            case R.id.action_sort_by_popularity: {
                mEverythingQuery.setSortBy(SortBy.POPULARITY);

                //mPresenter.loadSortedData(SortBy.POPULARITY);
                mPresenter.loadData(mEverythingQuery);
                return true;
            }
            case R.id.action_sort_by_published_at:{
                mEverythingQuery.setSortBy(SortBy.PUBLISHED_AT);

//                mPresenter.loadSortedData(SortBy.PUBLISHED_AT);
                mPresenter.loadData(mEverythingQuery);
                return true;
            }
            case R.id.action_sort_by_relevancy: {
                mEverythingQuery.setSortBy(SortBy.RELEVANCY);

//                mPresenter.loadSortedData(SortBy.RELEVANCY);
                mPresenter.loadData(mEverythingQuery);
                return true;
            }
            case R.id.action_select_from_to: {
                SelectFromToDialog dialog = new SelectFromToDialog();
                dialog.show(getSupportFragmentManager(), "select_from_to");
                return true;
            }
            case R.id.action_select_sources: {
                SourceListDialog dialog = new SourceListDialog();
                dialog.show(getSupportFragmentManager(), "select_source");
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                String from, to;
                TimeZone timeZone = TimeZone.getTimeZone("UTC");
                java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                df.setTimeZone(timeZone);
                from = df.format(new Date(data.getExtras().getLong(EXTRA_DATE_FROM)));
                to = df.format(new Date(data.getExtras().getLong(EXTRA_DATE_TO)));
                mEverythingQuery.setFrom(from);
                mEverythingQuery.setTo(to);

//                mPresenter.loadFilteredData(from, to);
                mPresenter.loadData(mEverythingQuery);
            } else if(requestCode == 2) {
                String source = data.getExtras().getString(EXTRA_SOURCE);
                mEverythingQuery.setSources(source);

//                mPresenter.loadFilteredData(source);
                mPresenter.loadData(mEverythingQuery);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
