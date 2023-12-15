package com.example.myapplicationapipaises.Repository;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.myapplicationapipaises.Country;
import com.example.myapplicationapipaises.CountryEntity;
import com.example.myapplicationapipaises.Model.CountryViewModel;
import com.example.myapplicationapipaises.DAO.CountryDao;
import com.example.myapplicationapipaises.Service.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

public class CountryRepository {
    private ApiService apiService;
    private CountryDao countryDao;
    private CountryViewModel countryViewModel;

    public CountryRepository(ApiService apiService, CountryDao countryDao, CountryViewModel countryViewModel) {
        this.apiService = apiService;
        this.countryDao = countryDao;
        this.countryViewModel = countryViewModel;
    }

    public LiveData<List<Country>> getCountries() {
        return countryDao.getCountries();
    }

    public void refreshCountries() {
        new RefreshCountriesTask(apiService, countryDao).execute();
    }

    public void deleteAllCountries() {
        new DeleteAllCountriesTask(countryDao).execute();
    }

    public void searchCountries(String query) {
        new SearchCountriesTask(apiService, countryDao).execute(query);
    }

    private static class RefreshCountriesTask extends AsyncTask<Void, Void, Void> {
        private ApiService apiService;
        private CountryDao countryDao;

        public RefreshCountriesTask(ApiService apiService, CountryDao countryDao) {
            this.apiService = apiService;
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Response<List<Country>> response = apiService.getCountries().execute();
                if (response.isSuccessful()) {
                    List<Country> remoteData = response.body();
                    CountryRepository.saveCountriesToLocalDatabase(countryDao, remoteData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class DeleteAllCountriesTask extends AsyncTask<Void, Void, Void> {
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

    private static class SearchCountriesTask extends AsyncTask<String, Void, Void> {
        private ApiService apiService;
        private CountryDao countryDao;

        public SearchCountriesTask(ApiService apiService, CountryDao countryDao) {
            this.apiService = apiService;
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(String... queries) {
            if (queries.length > 0) {
                String query = queries[0];
                try {
                    Response<List<Country>> response = apiService.searchCountries(query).execute();
                    if (response.isSuccessful()) {
                        List<Country> searchResults = response.body();
                        CountryRepository.saveCountriesToLocalDatabase(countryDao, searchResults);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private static void saveCountriesToLocalDatabase(CountryDao countryDao, List<Country> countries) {
        List<CountryEntity> countryEntities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getDescription() != null) {
                countryEntities.add(new CountryEntity(country.getDescription(), country.getType()));
            }
        }
        countryDao.insertCountries(countryEntities);
    }
}
