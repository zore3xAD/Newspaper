package com.android.zore3x.newspaper.presenter;

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.api.EverythingQuery;
import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.view.EverythingView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class EverythingPresenter extends MvpPresenter<EverythingView> {

    private int oldPage = 1;
    private boolean isNewPage;

    public void loadData(EverythingQuery query) {

        // проверяем хотим ли мы загрузить новую страницу
        if(query.getPage() > oldPage) {
            isNewPage = true;
            oldPage = query.getPage();
        } else {
            isNewPage = false;
        }
        App.getNewsApi().getEverything(query.getPage(), query.getPageSize(), query.getQ(),
                query.getSources(), query.getDomains(), query.getFrom(), query.getTo(),
                query.getLanguage(), query.getSortBy().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getViewState().showProgress();
                    }

                    @Override
                    public void onNext(Response response) {

                        onLoafFinished(response.getArticleList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideProgress();
                        onLoadError(e.getLocalizedMessage());
                    }
                    @Override
                    public void onComplete() {
                        getViewState().hideProgress();
                    }
                });
    }

    private void onLoadError(String localizedMessage) {
        getViewState().showMessage(localizedMessage);
    }

    private void onLoafFinished(List<Article> articleList) {
        if(isNewPage) {
            getViewState().showNewPage(articleList);
        } else {
            getViewState().showData(articleList);
        }
    }
}
