package com.walpolerobotics.scouting.matchappneo2019.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {
    @PrimaryKey
    public String key;

    @ColumnInfo(name = "readable_name")
    public String readableName;
}
