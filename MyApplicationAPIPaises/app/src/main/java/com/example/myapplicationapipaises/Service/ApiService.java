package com.example.myapplicationapipaises.Service;

import com.example.myapplicationapipaises.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


import retrofit2.http.Query;

public interface ApiService {
    @GET("api/paises")
    Call<List<Country>> getCountries();

        @GET("api/paises")
    Call<List<Country>> searchCountries(@Query("q") String query);
}
