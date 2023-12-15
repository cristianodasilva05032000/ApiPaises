package com.example.myapplicationapipaises.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplicationapipaises.CountryEntity;
import com.example.myapplicationapipaises.DAO.CountryDao;

@Database(entities = {CountryEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public void deleteAllCountries() {
        new DeleteAllCountriesAsyncTask(instance).execute();
    }

    private static class DeleteAllCountriesAsyncTask extends AsyncTask<Void, Void, Void> {
        private CountryDao countryDao;

        private DeleteAllCountriesAsyncTask(AppDatabase db) {
            countryDao = db.countryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAllCountries();
            return null;
        }
    }
}
