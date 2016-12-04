package com.example.przemek.beeryouwantv2.Table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Przemek on 04.12.2016.
 */

public class ALevelTable {
    public static final String TABLE_NAME = "a_level";
    public static final String ID_ALEVEL = "id_a_level";
    public static final String NAME_ALEVEL = "name";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_A_LEVEL_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_ALEVEL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_ALEVEL + " TEXT,"
                + ")";
        db.execSQL(CREATE_A_LEVEL_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        ALevelTable.onCreate(db);
    }
}
