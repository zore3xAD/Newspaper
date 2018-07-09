package com.android.zore3x.newspaper.presenter;

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.activity.EverythingActivity;
import com.android.zore3x.newspaper.model.RequestParameter;
import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.model.api.SortBy;
import com.android.zore3x.newspaper.view.EverythingView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EverythingPresenter {

    private EverythingView mView;

    public void attach(EverythingView view) {
        mView = view;
    }
    public void detach() {
        mView = null;
    }

    public void loadData(RequestParameter query) {
        App.getNewsApi().getEverything(query.getPage(), query.getPageSize(), query.getQ(),
                query.getSources(), query.getDomains(), query.getFrom(), query.getTo(),
                query.getLanguage(), query.getSortBy().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        if (mView != null) {
                            mView.showData(response.getArticleList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


//    public void loadData() {
//        App.getNewsApi().getEverything(1, 5, "", SortBy.POPULARITY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response response) {
//                        if (mView != null) {
//                            mView.showData(response.getArticleList());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (mView != null) {
//                            mView.showMessage(e.getLocalizedMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//    public void loadSortedData(SortBy sortBy) {
//        App.getNewsApi().getEverything(1, 5, "", sortBy)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response response) {
//                        if (mView != null) {
//                            mView.showData(response.getArticleList());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (mView != null) {
//                            mView.showMessage(e.getLocalizedMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    public void loadFilteredData(String from, String to) {
//
//        App.getNewsApi().getEverything(1, 5, "", from, to)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response response) {
//                        mView.showData(response.getArticleList());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    public void loadFilteredData(String source) {
//        App.getNewsApi().getEverything(1, 5, "", source)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response response) {
//                        mView.showData(response.getArticleList());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
