package com.walpolerobotics.scouting.matchappneo2019.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MatchDao {
    @Query("SELECT * from `match` WHERE eventKey LIKE :event")
    List<Match> getMatchesForEvent(String event);

    @Query("SELECT * from `match` WHERE eventKey LIKE :event LIKE :number LIMIT 1")
    Match getMatch(String event, int number);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Match... matches);

    @Delete
    void deleteAll(Match... matches);

    @Delete
    void delete(Match match);
}
