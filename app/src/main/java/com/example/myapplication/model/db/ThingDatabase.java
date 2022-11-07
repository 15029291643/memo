package com.example.myapplication.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Thing.class}, version = 1, exportSchema = false)
public abstract class ThingDatabase extends RoomDatabase {
    private static ThingDatabase instance = null;
    public abstract ThingService thingService();

    public static ThingDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ThingDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, ThingDatabase.class, "name").build();
                }
            }
        }
        return instance;
    }
}
