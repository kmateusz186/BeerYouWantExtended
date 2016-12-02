package com.example.przemek.beeryouwantv2.model;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Country {
    private int idCountry;
    private String name;

    public Country(int idCountry, String name) {
        this.idCountry = idCountry;
        this.name = name;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
