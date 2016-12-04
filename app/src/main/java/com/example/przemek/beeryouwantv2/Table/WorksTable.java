package com.example.przemek.beeryouwantv2.Table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Przemek on 04.12.2016.
 */

public class WorksTable {
    public static final String TABLE_NAME = "works";
    public static final String ID_WORKS = "id_works";
    public static final String NAME_WORKS = "name";
    public static final String IMAGE_RESOURCE_ID = "image_resource_id";
    public static final String DESCRIPTION = "description";
    public static final String PROVINCE = "province";
    public static final String COUNTRY = "country";
    public static final String FAVOURITE =  "favourite";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_WORKS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_WORKS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_WORKS + " TEXT,"
                + IMAGE_RESOURCE_ID + " INTEGER,"
                + DESCRIPTION + " TEXT,"
                + PROVINCE + " INTEGER,"
                + COUNTRY + " INTEGER,"
                + FAVOURITE + " INTEGER,"
                + "FOREIGN KEY(" + PROVINCE + ")"
                + "REFERENCES " + ProvinceTable.TABLE_NAME + "(" + ProvinceTable.ID_PROVINCE + "),"
                + "FOREIGN KEY(" + COUNTRY + ")"
                + "REFERENCES " + CountryTable.TABLE_NAME + "(" + CountryTable.ID_COUNTRY + "),"
                + ")";
        db.execSQL(CREATE_WORKS_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        WorksTable.onCreate(db);
    }
}
