package com.example.myapplicationapipaises.Model;

import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationapipaises.Repository.CountryRepository;

public class CountryViewModelFactory implements ViewModelProvider.Factory {

    private final CountryRepository countryRepository;

    public CountryViewModelFactory(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CountryViewModel.class)) {
            return (T) new CountryViewModel(countryRepository);
        }
        throw new IllegalArgumentException("");
    }
}
