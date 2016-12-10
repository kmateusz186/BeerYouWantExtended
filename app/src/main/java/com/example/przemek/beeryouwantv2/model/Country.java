package com.example.przemek.beeryouwantv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Country {
    private int idCountry;
    private String nameCountry;
    private List<Province> provincesList;

    public Country() {
        this.provincesList = new ArrayList<>();
    }

    public Country(int idCountry, String nameCountry) {
        this.idCountry = idCountry;
        this.nameCountry = nameCountry;
        this.provincesList = new ArrayList<>();
    }

    public Country(int idCountry, String nameCountry, List<Province> provincesList) {
        this.idCountry = idCountry;
        this.nameCountry = nameCountry;
        this.provincesList = provincesList;
    }

    public int getIdCountry() {
        return idCountry;
    }
    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }
    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public List<Province> getProvincesList() {
        return provincesList;
    }
    public void setProvincesList(List<Province> provincesList) {
        this.provincesList = provincesList;
    }
}
