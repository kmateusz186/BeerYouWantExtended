package com.example.przemek.beeryouwantv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Style {
    private int idStyle;
    private String nameStyle;
    private String colorStyle;
    private String maltWheat;
    private String fermentation;
    private int idAlcohol;
    private int idMalt;
    private int idBitter;
    private ALevel alcohol;
    private BMLevel malt;
    private BMLevel bitter;
    private List<Beer> beerList;

    public Style() {
        this.beerList = new ArrayList<>();
    }

    public Style(int idStyle, String nameStyle) {
        this.idStyle = idStyle;
        this.nameStyle = nameStyle;
    }

    public Style(int idStyle, String nameStyle, String colorStyle, int idBitter, int idMalt, int idAlcohol, String maltWheat, String fermentation) {
        this.idStyle = idStyle;
        this.nameStyle = nameStyle;
        this.colorStyle = colorStyle;
        this.maltWheat = maltWheat;
        this.fermentation = fermentation;
        this.idAlcohol = idAlcohol;
        this.idMalt = idMalt;
        this.idBitter = idBitter;
        this.alcohol = new ALevel();
        this.malt = new BMLevel();
        this.bitter = new BMLevel();
        this.beerList = new ArrayList<>();
    }

    public Style(int idStyle, String nameStyle, String colorStyle, String maltWheat, String fermentation, ALevel alcohol, BMLevel malt, BMLevel bitter, List<Beer> beerList) {
        this.idStyle = idStyle;
        this.nameStyle = nameStyle;
        this.colorStyle = colorStyle;
        this.maltWheat = maltWheat;
        this.fermentation = fermentation;
        this.alcohol = alcohol;
        this.malt = malt;
        this.bitter = bitter;
        this.beerList = beerList;
        //this.idAlcohol = alcohol.getIdALevel();
        //this.idMalt = malt.getIdBMLevel();
        //this.idBitter = bitter.getIdBMLevel();

    }

    public int getIdStyle() {
        return idStyle;
    }
    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public String getNameStyle() {
        return nameStyle;
    }
    public void setNameStyle(String nameStyle) {
        this.nameStyle = nameStyle;
    }

    public String getColorStyle() {
        return colorStyle;
    }
    public void setColorStyle(String colorStyle) {
        this.colorStyle = colorStyle;
    }

    public String getMaltWheat() {
        return maltWheat;
    }
    public void setMaltWheat(String maltWheat) {
        this.maltWheat = maltWheat;
    }

    public String getFermentation() {
        return fermentation;
    }
    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

    public ALevel getAlcohol() {
        return alcohol;
    }
    public void setAlcohol(ALevel alcohol) {
        this.alcohol = alcohol;
    }

    public BMLevel getMalt() {
        return malt;
    }
    public void setMalt(BMLevel malt) {
        this.malt = malt;
    }

    public BMLevel getBitter() {
        return bitter;
    }
    public void setBitter(BMLevel bitter) {
        this.bitter = bitter;
    }

    public List<Beer> getBeerList() {
        return beerList;
    }
    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }

    public int getIdAlcohol() {
        return idAlcohol;
    }

    public void setIdAlcohol(int idAlcohol) {
        this.idAlcohol = idAlcohol;
    }

    public int getIdMalt() {
        return idMalt;
    }

    public void setIdMalt(int idMalt) {
        this.idMalt = idMalt;
    }

    public int getIdBitter() {
        return idBitter;
    }

    public void setIdBitter(int idBitter) {
        this.idBitter = idBitter;
    }
}
