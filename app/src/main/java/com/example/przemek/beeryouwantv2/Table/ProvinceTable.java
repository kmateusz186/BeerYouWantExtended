package com.example.przemek.beeryouwantv2.Table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Przemek on 04.12.2016.
 */

public class ProvinceTable {
    public static final String TABLE_NAME = "province";
    public static final String ID_PROVINCE = "id_province";
    public static final String NAME_PROVINCE = "name";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_PROVINCE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_PROVINCE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_PROVINCE + " TEXT,"
                + ")";
        db.execSQL(CREATE_PROVINCE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        ProvinceTable.onCreate(db);
    }
}
