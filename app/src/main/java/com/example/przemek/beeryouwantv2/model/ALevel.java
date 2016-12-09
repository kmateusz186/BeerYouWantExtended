package com.example.przemek.beeryouwantv2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemek on 02.12.2016.
 */

public class ALevel {
    private int idALevel;
    private String nameALevel;
    private List<Style> styleList;

    public ALevel() {
        this.styleList = new ArrayList<>();
    }
    public ALevel(int idALevel, String nameALevel) {
        this.idALevel = idALevel;
        this.nameALevel = nameALevel;
        this.styleList = new ArrayList<>();
    }
    public ALevel(int idALevel, String nameALevel, List<Style> styleList) {
        this.idALevel = idALevel;
        this.nameALevel = nameALevel;
        this.styleList = styleList;
    }


    public int getIdALevel() {
        return idALevel;
    }
    public void setIdALevel(int idALevel) {
        this.idALevel = idALevel;
    }

    public String getNameALevel() {
        return nameALevel;
    }
    public void setNameALevel(String nameALevel) {
        this.nameALevel = nameALevel;
    }

    public List<Style> getStyleList() {
        return styleList;
    }
    public void setStyleList(List<Style> styleList) {
        this.styleList = styleList;
    }
}
