package com.aqeel.johnwick.aggregatecalculator.models;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface UniDao {
    @Query("SELECT * FROM uni")
    List<Uni> getAll();

    @Query("SELECT * FROM uni WHERE uid IN (:userIds)")
    List<Uni> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM uni WHERE uni_Name LIKE :uniName LIMIT 1")
    Uni findByName(String uniName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Uni... uni);


    @Delete
    void delete(Uni uni);
}