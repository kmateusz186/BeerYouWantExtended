package com.example.przemek.beeryouwantv2.Table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Przemek on 04.12.2016.
 */

public class BMLevelTable {
    public static final String TABLE_NAME = "bm_level";
    public static final String ID_BMLEVEL = "id_bm_level";
    public static final String NAME_BMLEVEL = "name";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_BM_LEVEL_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_BMLEVEL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_BMLEVEL + " TEXT,"
                + ")";
        db.execSQL(CREATE_BM_LEVEL_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        BMLevelTable.onCreate(db);
    }
}
