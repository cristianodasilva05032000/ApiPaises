package com.example.myapplicationapipaises.Model;

import androidx.lifecycle.LiveData;

import java.util.List;


import androidx.lifecycle.ViewModel;

import com.example.myapplicationapipaises.Country;
import com.example.myapplicationapipaises.Repository.CountryRepository;

public class CountryViewModel extends ViewModel {

    private CountryRepository countryRepository;
    private LiveData<List<Country>> countries;

    public CountryViewModel(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        this.countries = countryRepository.getCountries();
    }

    public LiveData<List<Country>> getCountries() {
        return countries;
    }

    public void searchCountries(String query) {

        countryRepository.searchCountries(query);
    }
}
