package com.example.przemek.beeryouwantv2.model;

/**
 * Created by Przemek on 02.12.2016.
 */

public class BMLevel {
    private int idBMLevel;
    private String name;

    public BMLevel(int idBMLevel, String name) {
        this.idBMLevel = idBMLevel;
        this.name = name;
    }

    public int getIdBMLevel() {
        return idBMLevel;
    }

    public void setIdBMLevel(int idBMLevel) {
        this.idBMLevel = idBMLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
