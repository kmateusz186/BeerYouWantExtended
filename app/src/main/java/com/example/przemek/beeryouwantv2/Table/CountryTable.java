package com.example.przemek.beeryouwantv2.Table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Przemek on 04.12.2016.
 */

public class CountryTable {
    public static final String TABLE_NAME = "country";
    public static final String ID_COUNTRY = "id_country";
    public static final String NAME_COUNTRY = "name";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COUNTRY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_COUNTRY + " TEXT,"
                + ")";
        db.execSQL(CREATE_COUNTRY_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        CountryTable.onCreate(db);
    }
}
