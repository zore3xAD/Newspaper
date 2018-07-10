package com.android.zore3x.newspaper.view;

import com.android.zore3x.newspaper.model.Article;
import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface TopHeadlinesView extends MvpView{

    void showMessage(String message);
    void showData(List<Article> data);
    void showNewPage(List<Article> data);
    void showProgress();
    void hideProgress();

}
