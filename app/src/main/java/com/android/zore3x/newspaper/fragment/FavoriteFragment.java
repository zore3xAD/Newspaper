package com.android.zore3x.newspaper.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.adapter.FavoriteAdapter;
import com.android.zore3x.newspaper.model.database.Favorite;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class FavoriteFragment extends Fragment {

    public static FavoriteFragment newInstance() {

        Bundle args = new Bundle();

        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mFavoriteRecyclerView;
    private FavoriteAdapter mFavoriteAdapter = new FavoriteAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFavoriteRecyclerView = view.findViewById(R.id.favorite_recyclerView);
        mFavoriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFavoriteRecyclerView.setAdapter(mFavoriteAdapter);

        App.getAppDatabase().mFavoriteDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<Favorite>>() {

                    @Override
                    public void onSuccess(List<Favorite> favorites) {
                        mFavoriteAdapter.setNewsList(favorites);
                        mFavoriteAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
