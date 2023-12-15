package com.example.myapplicationapipaises.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplicationapipaises.Country;
import com.example.myapplicationapipaises.CountryEntity;

import java.util.List;

@Dao
public interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountries(List<CountryEntity> countries);

    @Query("SELECT * FROM countries")
    LiveData<List<Country>> getCountries();

    @Query("DELETE FROM countries")
    void deleteAllCountries();
}
