package com.android.zore3x.newspaper.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SelectedSourceDao {

    @Query("SELECT * from selected_source")
    List<SelectedSource> getSourceList();

    @Query("SELECT * FROM selected_source WHERE source = :source")
    SelectedSource getSource(String source);

    @Insert
    Long[] insert(Favorite...favorites);

    @Delete
    Long[] delete(Favorite...favorites);
}
