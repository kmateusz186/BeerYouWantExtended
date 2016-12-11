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
    private int idProvince;
    private Province province;
    private List<Beer> beersList;

    public Works() {
        this.beersList = new ArrayList<>();
    }

    public Works(int idWorks, String nameWorks) {
        this.idWorks = idWorks;
        this.nameWorks = nameWorks;
    }

    public Works(int idWorks, String nameWorks, int idProvince) {
        this.idWorks = idWorks;
        this.nameWorks = nameWorks;
        this.idProvince = idProvince;
        this.province = new Province();
    }

    public Works(int idWorks, String nameWorks, int imageResourceID, String descriptionWorks, int idProvince, int favouriteWorks ) {
        this.idWorks = idWorks;
        this.imageResourceID = imageResourceID;
        this.favouriteWorks = favouriteWorks;
        this.nameWorks = nameWorks;
        this.descriptionWorks = descriptionWorks;
        this.idProvince = idProvince;
        this.province = new Province();
        this.beersList = new ArrayList<>();
    }


    public Works(int idWorks, String nameWorks, int imageResourceID, String descriptionWorks, Province province, int favouriteWorks, List<Beer> beersList) {
        this.idWorks = idWorks;
        this.imageResourceID = imageResourceID;
        this.favouriteWorks = favouriteWorks;
        this.nameWorks = nameWorks;
        this.descriptionWorks = descriptionWorks;
        this.province = province;
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

    public List<Beer> getBeersList() {
        return beersList;
    }
    public void setBeersList(List<Beer> beersList) {
        this.beersList = beersList;
    }

    public int getIdProvince() {
        return idProvince;
    }
    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }
}
