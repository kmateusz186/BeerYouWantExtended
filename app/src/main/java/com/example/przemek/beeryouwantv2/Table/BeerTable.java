package com.example.przemek.beeryouwantv2.Table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Przemek on 04.12.2016.
 */

public class BeerTable {
    public static final String TABLE_NAME = "beer";
    public static final String ID_BEER = "id_beer";
    public static final String NAME_BEER = "name";
    public static final String STYLE = "style";
    public static final String WORKS = "works";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_BEER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_BEER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_BEER + " TEXT,"
                + STYLE + " INTEGER,"
                + WORKS + " INTEGER,"
                + "FOREIGN KEY(" + STYLE + ")"
                + "REFERENCES " + StyleTable.TABLE_NAME + "(" + StyleTable.ID_STYLE + "),"
                + "FOREIGN KEY(" + WORKS + ")"
                + "REFERENCES " + WorksTable.TABLE_NAME + "(" + WorksTable.ID_WORKS + "),"
                + ")";
        db.execSQL(CREATE_BEER_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        BeerTable.onCreate(db);
    }
}

