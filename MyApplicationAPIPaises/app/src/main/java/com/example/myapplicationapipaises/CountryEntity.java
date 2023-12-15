package com.example.myapplicationapipaises;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class CountryEntity {
    @PrimaryKey
    @NonNull
    private String description;

    private String type;

    public CountryEntity(@NonNull String description, String type) {
        this.description = description;
        this.type = type;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
