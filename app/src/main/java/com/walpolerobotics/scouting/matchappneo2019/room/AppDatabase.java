package com.walpolerobotics.scouting.matchappneo2019.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Match.class, Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "frc-data";

    private static AppDatabase db;

    public static AppDatabase getDatabaseInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return db;
    }

    public abstract MatchDao matchDao();
    public abstract EventDao eventDao();
}
