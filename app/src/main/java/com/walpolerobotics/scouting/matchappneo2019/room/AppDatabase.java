package com.walpolerobotics.scouting.matchappneo2019.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Match.class, Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MatchDao matchDao();
    public abstract  EventDao eventDao();
}
