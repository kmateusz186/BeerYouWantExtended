package com.example.przemek.beeryouwantv2.model;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Province {
    private int idProvince;
    private String name;
    private Country country;

    public Province(int idProvince, String name, Country country) {
        this.idProvince = idProvince;
        this.name = name;
        this.country = country;
    }

    public int getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
