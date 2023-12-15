package com.example.myapplicationapipaises;

import android.os.AsyncTask;

import com.example.myapplicationapipaises.DAO.CountryDao;
import com.example.myapplicationapipaises.Model.CountryViewModel;
import com.example.myapplicationapipaises.Service.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class SearchCountriesTask extends AsyncTask<String, Void, List<Country>> {
    private ApiService apiService;
    private CountryDao countryDao;
    private CountryViewModel countryViewModel;

    public SearchCountriesTask(ApiService apiService, CountryDao countryDao, CountryViewModel countryViewModel) {
        this.apiService = apiService;
        this.countryDao = countryDao;
        this.countryViewModel = countryViewModel;
    }

    @Override
    protected List<Country> doInBackground(String... queries) {
        List<Country> searchResults = new ArrayList<>();
        if (queries.length > 0) {
            String query = queries[0];
            try {
                Response<List<Country>> response = apiService.searchCountries(query).execute();
                if (response.isSuccessful()) {
                    searchResults = response.body();

                    saveCountriesToLocalDatabase(searchResults);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return searchResults;
    }


    private void saveCountriesToLocalDatabase(List<Country> countries) {
        List<CountryEntity> countryEntities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getDescription() != null) {
                countryEntities.add(new CountryEntity(country.getDescription(), country.getType()));
            }
        }
        countryDao.insertCountries(countryEntities);
    }
}
