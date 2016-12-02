package com.example.przemek.beeryouwantv2.model;

/**
 * Created by Przemek on 02.12.2016.
 */

public class ALevel {
    private int idALevel;
    private String name;

    public ALevel(int idALevel, String name) {
        this.idALevel = idALevel;
        this.name = name;
    }

    public int getIdALevel() {
        return idALevel;
    }

    public void setIdALevel(int idALevel) {
        this.idALevel = idALevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
