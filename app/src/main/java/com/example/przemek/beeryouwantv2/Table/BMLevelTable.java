package com.example.przemek.beeryouwantv2.Table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.model.BMLevel;

/**
 * Created by Przemek on 04.12.2016.
 */

public class BMLevelTable {
    public static final String TABLE_NAME = "bm_level";
    //public static final String ID_BMLEVEL = "id_bm_level";
    public static final String NAME_BMLEVEL = "name";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_BM_LEVEL_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_BMLEVEL + " TEXT"
                + ")";
        db.execSQL(CREATE_BM_LEVEL_TABLE);
        onInsert(db, new BMLevel(0, "Słaba", null));
        onInsert(db, new BMLevel(0, "Normalna", null));
        onInsert(db, new BMLevel(0, "Mocna", null));
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        BMLevelTable.onCreate(db);
    }

    public static void onInsert(SQLiteDatabase db, BMLevel bmLevel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_BMLEVEL, bmLevel.getNameBMLevel());
        db.insert(TABLE_NAME, null, contentValues);
    }
}
