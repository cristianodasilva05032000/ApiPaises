package com.example.myapplicationapipaises;

public class Country {
    private String description;
    private String type;

    public Country(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
