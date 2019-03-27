package com.walpolerobotics.scouting.matchappneo2019.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface EventDao {
    @Query("SELECT * from event")
    List<Event> getAll();

    @Query("SELECT * FROM event WHERE `key` LIKE :key")
    Event getByKey(String key);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Event... events);
}
