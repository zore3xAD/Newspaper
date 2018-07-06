package com.android.zore3x.newspaper;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.android.zore3x.newspaper.model.api.NewsApi;
import com.android.zore3x.newspaper.model.database.AppDatabase;

public class App extends Application {

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
