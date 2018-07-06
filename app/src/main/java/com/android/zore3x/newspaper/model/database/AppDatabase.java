package com.android.zore3x.newspaper.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Favorite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoriteDao mFavoriteDao();
}
