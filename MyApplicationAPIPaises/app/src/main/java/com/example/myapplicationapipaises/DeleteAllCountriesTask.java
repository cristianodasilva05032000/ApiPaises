package com.example.myapplicationapipaises;

import android.os.AsyncTask;

import com.example.myapplicationapipaises.DAO.CountryDao;

public class DeleteAllCountriesTask extends AsyncTask<Void, Void, Void> {
    private CountryDao countryDao;

    public DeleteAllCountriesTask(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        countryDao.deleteAllCountries();
        return null;
    }
}
