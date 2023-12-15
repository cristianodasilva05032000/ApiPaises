package com.example.myapplicationapipaises;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationapipaises.Adapter.CountryAdapter;
import com.example.myapplicationapipaises.DAO.CountryDao;
import com.example.myapplicationapipaises.Database.AppDatabase;
import com.example.myapplicationapipaises.Model.CountryViewModel;
import com.example.myapplicationapipaises.Model.CountryViewModelFactory;
import com.example.myapplicationapipaises.Repository.CountryRepository;
import com.example.myapplicationapipaises.Retrofit.RetrofitClient;
import com.example.myapplicationapipaises.Service.ApiService;

public class MainActivity extends AppCompatActivity {

    private CountryViewModel countryViewModel;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new CountryAdapter();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ApiService apiService = RetrofitClient.getApiService();
        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        CountryDao countryDao = appDatabase.countryDao();


        CountryRepository countryRepository = new CountryRepository(apiService, countryDao, countryViewModel);


        CountryViewModelFactory factory = new CountryViewModelFactory(countryRepository);
        countryViewModel = new ViewModelProvider(this, factory).get(CountryViewModel.class);

        countryViewModel.getCountries().observe(this, countries -> adapter.submitList(countries));

        Button searchButton = findViewById(R.id.searchButton);
        EditText searchEditText = findViewById(R.id.searchEditText);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchTerm = searchEditText.getText().toString();


                new DeleteAllCountriesTask(countryDao).execute();

                countryRepository.searchCountries(searchTerm);
            }
        });
    }
}
