package com.walpolerobotics.scouting.matchappneo2019.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"matchNumber","eventKey"})
public class Match {
    @ColumnInfo(name = "matchNumber")
    @NonNull
    public int matchNumber;

    @ColumnInfo(name = "eventKey")
    @NonNull
    public String eventKey;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "blue_1")
    public int blue1;

    @ColumnInfo(name = "blue_2")
    public int blue2;

    @ColumnInfo(name = "blue_3")
    public int blue3;

    @ColumnInfo(name = "red_1")
    public int red1;

    @ColumnInfo(name = "red_2")
    public int red2;

    @ColumnInfo(name = "red_3")
    public int red3;
}
