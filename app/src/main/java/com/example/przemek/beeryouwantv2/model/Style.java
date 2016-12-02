package com.example.przemek.beeryouwantv2.model;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Style {
    private int idStyle;
    private String name;
    private String color;
    private String maltWheat;
    private String fermentation;
    private ALevel alcohol;
    private BMLevel malt;
    private int bitter;

    public Style(int idStyle, String name, String color, String maltWheat, String fermentation, ALevel alcohol, BMLevel malt, int bitter) {
        this.idStyle = idStyle;
        this.name = name;
        this.color = color;
        this.maltWheat = maltWheat;
        this.fermentation = fermentation;
        this.alcohol = alcohol;
        this.malt = malt;
        this.bitter = bitter;
    }

    public int getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getBitter() {
        return bitter;
    }

    public void setBitter(int bitter) {
        this.bitter = bitter;
    }
}
