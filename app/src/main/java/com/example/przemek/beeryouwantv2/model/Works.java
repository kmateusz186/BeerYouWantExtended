package com.example.przemek.beeryouwantv2.model;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Works {
    private int idWorks;
    private int imageResourceID;
    private Province province;
    private Country country;
    private int favourite;
    private String name;
    private String description;

    public Works(int idWorks, int imageResourceID, Province province, Country country, int favourite, String name, String description) {
        this.idWorks = idWorks;
        this.imageResourceID = imageResourceID;
        this.province = province;
        this.country = country;
        this.favourite = favourite;
        this.name = name;
        this.description = description;
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

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
