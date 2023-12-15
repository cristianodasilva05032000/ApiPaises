package com.example.myapplicationapipaises.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationapipaises.Country;
import com.example.myapplicationapipaises.R;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryList = new ArrayList<>();

    public void submitList(List<Country> newList) {
        countryList.clear();
        countryList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country currentCountry = countryList.get(position);
        holder.bind(currentCountry);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView descriptionTextView;
        private TextView typeTextView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
        }

        public void bind(Country country) {
            descriptionTextView.setText(country.getDescription());
            typeTextView.setText(country.getType());
        }
    }
}
