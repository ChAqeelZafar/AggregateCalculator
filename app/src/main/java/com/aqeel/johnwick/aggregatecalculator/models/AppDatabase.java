package com.aqeel.johnwick.aggregatecalculator.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Uni.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UniDao uniDao();
}

