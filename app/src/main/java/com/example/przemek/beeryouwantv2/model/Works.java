package com.example.przemek.beeryouwantv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Works {
    private int idWorks;
    private int imageResourceID;
    private int favouriteWorks;
    private String nameWorks;
    private String descriptionWorks;
    private Province province;
    private Country country;
    private List<Beer> beersList;

    public Works() {
        this.beersList = new ArrayList<>();
    }
    public Works(int idWorks, String nameWorks, int imageResourceID, String descriptionWorks, Province province, Country country, int favouriteWorks, List<Beer> beersList) {
        this.idWorks = idWorks;
        this.imageResourceID = imageResourceID;
        this.favouriteWorks = favouriteWorks;
        this.nameWorks = nameWorks;
        this.descriptionWorks = descriptionWorks;
        this.province = province;
        this.country = country;
        this.beersList = beersList;
    }

    public int getIdWorks() {
        return idWorks;
    }
    public void setIdWorks(int idWorks) {
        this.idWorks = idWorks;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }
    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public int getFavouriteWorks() {
        return favouriteWorks;
    }
    public void setFavouriteWorks(int favouriteWorks) {
        this.favouriteWorks = favouriteWorks;
    }

    public String getNameWorks() {
        return nameWorks;
    }
    public void setNameWorks(String nameWorks) {
        this.nameWorks = nameWorks;
    }

    public String getDescriptionWorks() {
        return descriptionWorks;
    }
    public void setDescriptionWorks(String descriptionWorks) {
        this.descriptionWorks = descriptionWorks;
    }

    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Beer> getBeersList() {
        return beersList;
    }
    public void setBeersList(List<Beer> beersList) {
        this.beersList = beersList;
    }
}
