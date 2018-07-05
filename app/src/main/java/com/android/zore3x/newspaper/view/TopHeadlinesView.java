package com.android.zore3x.newspaper.view;

import com.android.zore3x.newspaper.model.Article;

import java.util.List;

public interface TopHeadlinesView {

    void showMessage(String message);
    void showData(List<Article> data);

}
