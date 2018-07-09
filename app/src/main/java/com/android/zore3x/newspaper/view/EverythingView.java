package com.android.zore3x.newspaper.view;

import com.android.zore3x.newspaper.model.Article;
import com.android.zore3x.newspaper.model.api.SortBy;

import java.util.List;

public interface EverythingView {

    void showMessage(String message);
    void showData(List<Article> data);
    void showNewPage(List<Article> data);
}
