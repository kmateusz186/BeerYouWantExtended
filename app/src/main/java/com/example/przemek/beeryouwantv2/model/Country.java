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
    private List<Works> worksList;

    public Country() {
        this.provincesList = new ArrayList<>();
        this.worksList = new ArrayList<>();
    }

    public Country(int idCountry, String nameCountry) {
        this.idCountry = idCountry;
        this.nameCountry = nameCountry;
        this.provincesList = new ArrayList<>();
        this.worksList = new ArrayList<>();
    }

    public Country(int idCountry, String nameCountry, List<Province> provincesList, List<Works> worksList) {
        this.idCountry = idCountry;
        this.nameCountry = nameCountry;
        this.provincesList = provincesList;
        this.worksList = worksList;
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

    public List<Works> getWorksList() {
        return worksList;
    }
    public void setWorksList(List<Works> worksList) {
        this.worksList = worksList;
    }
}
