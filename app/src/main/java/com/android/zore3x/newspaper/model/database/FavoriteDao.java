package com.android.zore3x.newspaper.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    Maybe<List<Favorite>> getAll();

    @Insert
    Long[] insert(Favorite...favorite);
}
