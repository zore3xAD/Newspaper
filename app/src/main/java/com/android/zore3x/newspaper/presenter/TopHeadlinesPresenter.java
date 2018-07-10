package com.android.zore3x.newspaper.presenter;

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.activity.TopHeadlinesActivity;
import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.model.api.TopHeadlinesQuery;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopHeadlinesPresenter {

    TopHeadlinesActivity mView;

    public void attach(TopHeadlinesActivity view) {
        mView = view;
    }

    public void detach() {
        mView = null;
    }

    public void loadData(TopHeadlinesQuery query) {
//        int page = query.getPage();
//        int pageSize = query.getPageSize();
//        String country = query.getCountry().getCountry();
//        String category = query.getCategory().getCategory();
//        String source = query.getSources();
//        String q = query.getQ();
        App.getNewsApi().getTopHeadlines(query.getPage(), query.getPageSize(),
                query.getCountry().getCountry(), query.getCategory().getCategory(),
                query.getSources(), query.getQ())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        if(mView != null) {
                            mView.showData(response.getArticleList());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mView != null) {
                            mView.showMessage(e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    public void loadData() {
//        App.getNewsApi().getTopHeadlinesNews(1, 5, "us")
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
//                        if(mView != null) {
//                            mView.showData(response.getArticleList());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if(mView != null) {
//                            mView.showMessage(e.getLocalizedMessage());
//                        }
//                    }
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

}
