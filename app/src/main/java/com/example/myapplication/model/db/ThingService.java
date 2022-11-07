package com.example.myapplication.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ThingService {
    @Insert
    void add(Thing thing);

    @Query("SELECT * FROM Thing")
    List<Thing> all();

    @Update
    void update(Thing thing);

    @Query("DELETE FROM Thing WHERE id = :id")
    void delete(int id);
}
