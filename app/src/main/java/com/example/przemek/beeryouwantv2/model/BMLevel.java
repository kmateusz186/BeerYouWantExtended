package com.example.przemek.beeryouwantv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemek on 02.12.2016.
 */

public class BMLevel {
    private int idBMLevel;
    private String nameBMLevel;
    private List<Style> styleList;

    public BMLevel() {
        this.styleList = new ArrayList<>();
    }
    public BMLevel(int idBMLevel, String nameBMLevel) {
        this.idBMLevel = idBMLevel;
        this.nameBMLevel = nameBMLevel;
        this.styleList = new ArrayList<>();
    }
    public BMLevel(int idBMLevel, String nameBMLevel, List<Style> styleList) {
        this.idBMLevel = idBMLevel;
        this.nameBMLevel = nameBMLevel;
        this.styleList = styleList;
    }

    public int getIdBMLevel() {
        return idBMLevel;
    }
    public void setIdBMLevel(int idBMLevel) {
        this.idBMLevel = idBMLevel;
    }

    public String getNameBMLevel() {
        return nameBMLevel;
    }
    public void setNameBMLevel(String nameBMLevel) {
        this.nameBMLevel = nameBMLevel;
    }

    public List<Style> getStyleList() {
        return styleList;
    }
    public void setStyleList(List<Style> styleList) {
        this.styleList = styleList;
    }
}
