package com.android.zore3x.newspaper;

import android.app.Application;

import com.android.zore3x.newspaper.model.api.NewsApi;

public class App extends Application {

    private static final String API_KEY = "414de4e48911427ea31809811dbdfbbc";
    private static NewsApi mNewsApi;

    @Override
    public void onCreate() {
        super.onCreate();
        mNewsApi = new NewsApi(API_KEY);
    }

    public static NewsApi getNewsApi() {
        return mNewsApi;
    }
}
