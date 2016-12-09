package com.example.przemek.beeryouwantv2.model;

/**
 * Created by Przemek on 02.12.2016.
 */

public class Beer {
    private int idBeer;
    private String nameBeer;
    private int idStyle;
    private int idWorks;

    private Style style;
    private Works works;

    public Beer() {
    }
    public Beer(int idBeer, String nameBeer, Style style, Works works) {
        this.idBeer = idBeer;
        this.nameBeer = nameBeer;
        this.style = style;
        this.works = works;
        //this.idStyle = style.getIdStyle();
        //this.idWorks = works.getIdWorks();
    }

    public Beer(int idBeer, String nameBeer, int idStyle, int idWorks) {
        this.idBeer = idBeer;
        this.nameBeer = nameBeer;
        this.idStyle = idStyle;
        this.idWorks = idWorks;
        this.style = new Style();
        this.works = new Works();
    }

    public int getIdBeer() {
        return idBeer;
    }
    public void setIdBeer(int idBeer) {
        this.idBeer = idBeer;
    }

    public String getNameBeer() {
        return nameBeer;
    }
    public void setNameBeer(String nameBeer) {
        this.nameBeer = nameBeer;
    }

    public Style getStyle() {
        return style;
    }
    public void setStyle(Style style) {
        this.style = style;
    }

    public Works getWorks() {
        return works;
    }
    public void setWorks(Works works) {
        this.works = works;
    }

    public int getIdStyle() {
        return idStyle;
    }
    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public int getIdWorks() {
        return idWorks;
    }
    public void setIdWorks(int idWorks) {
        this.idWorks = idWorks;
    }
}
