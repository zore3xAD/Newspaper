package com.android.zore3x.newspaper;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.android.zore3x.newspaper.model.api.NewsApi;
import com.android.zore3x.newspaper.model.database.AppDatabase;

public class App extends Application {

    public static final int REQUEST_SELECT_SOURCE_DIALOG = 100;
    public static final int REQUEST_SELECT_CATEGORY_DIALOG = 200;
    public static final int REQUEST_SELECT_FROM_TO_DATE_RANGE_DIALOG = 300;

    private static final String API_KEY = "414de4e48911427ea31809811dbdfbbc";
    private static final String DATABASE_NAME = "newspaper.db";

    private static NewsApi mNewsApi;
    private static AppDatabase mAppDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        mNewsApi = new NewsApi(API_KEY);
        mAppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public static NewsApi getNewsApi() {
        return mNewsApi;
    }

    public static AppDatabase getAppDatabase() { return mAppDatabase; }
}
