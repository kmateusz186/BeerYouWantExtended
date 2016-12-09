package com.example.przemek.beeryouwantv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Province {
    private int idProvince;
    private String nameProvince;
    private int idCountry;
    private Country country;
    private List<Works> worksList;

    public Province() {
        this.worksList = new ArrayList<>();
    }

    public Province(int idProvince, String nameProvince, int idCountry) {
        this.idProvince = idProvince;
        this.nameProvince = nameProvince;
        this.idCountry = idCountry;
        this.country = new Country();
        this.worksList = new ArrayList<>();
    }

    public Province(int idProvince, String nameProvince, Country country, List<Works> worksList) {
        this.idProvince = idProvince;
        this.nameProvince = nameProvince;
        this.country = country;
        //this.idCountry = country.getIdCountry();
        this.worksList = worksList;
    }

    public int getIdProvince() {
        return idProvince;
    }
    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }

    public String getNameProvince() {
        return nameProvince;
    }
    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Works> getWorksList() {
        return worksList;
    }
    public void setWorksList(List<Works> worksList) {
        this.worksList = worksList;
    }

    public int getIdCountry() {
        return idCountry;
    }
    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }
}
