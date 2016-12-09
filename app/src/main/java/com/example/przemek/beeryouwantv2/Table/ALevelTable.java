package com.example.przemek.beeryouwantv2.Table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.model.ALevel;

/**
 * Created by Przemek on 04.12.2016.
 */

public class ALevelTable {
    public static final String TABLE_NAME = "a_level";
    //public static final String ID_ALEVEL = "id_a_level";
    public static final String NAME_ALEVEL = "name";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_A_LEVEL_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_ALEVEL + " TEXT"
                + ")";
        db.execSQL(CREATE_A_LEVEL_TABLE);
        onInsert(db, new ALevel(0, "Niska", null));
        onInsert(db, new ALevel(0, "Normalna", null));
        onInsert(db, new ALevel(0, "Wysoka", null));
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        ALevelTable.onCreate(db);
    }

    public static void onInsert(SQLiteDatabase db, ALevel aLevel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_ALEVEL, aLevel.getNameALevel());
        db.insert(TABLE_NAME, null, contentValues);
    }
}
