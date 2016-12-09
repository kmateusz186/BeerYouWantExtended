package com.example.przemek.beeryouwantv2.Table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.model.Beer;

/**
 * Created by Przemek on 04.12.2016.
 */

public class BeerTable {
    public static final String TABLE_NAME = "beer";
    //public static final String ID_BEER = "id_beer";
    public static final String NAME_BEER = "name";
    public static final String STYLE = "style";
    public static final String WORKS = "works";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_BEER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_BEER + " TEXT,"
                + STYLE + " INTEGER,"
                + WORKS + " INTEGER,"
                + "FOREIGN KEY(" + STYLE + ")"
                + "REFERENCES " + StyleTable.TABLE_NAME + "(" + BaseColumns._ID + "),"
                + "FOREIGN KEY(" + WORKS + ")"
                + "REFERENCES " + WorksTable.TABLE_NAME + "(" + BaseColumns._ID + ")"
                + ")";
        db.execSQL(CREATE_BEER_TABLE);
        onInsert(db, new Beer(0, "Żywiec", null, null), 1, 2);
        onInsert(db, new Beer(0, "Tyskie", null, null), 1, 1);
        onInsert(db, new Beer(0, "Żywiec APA", null, null), 2, 2);
        onInsert(db, new Beer(0, "Zielony Wilk", null, null), 3, 3);
        onInsert(db, new Beer(0, "Mr.Hard", null, null), 4, 4);
        onInsert(db, new Beer(0, "Czarny Wilk", null, null), 5, 3);
        onInsert(db, new Beer(0, "Smoked Cracow", null, null), 6, 4);
        onInsert(db, new Beer(0, "Ciechan Pszeniczne", null, null), 7, 5);
        onInsert(db, new Beer(0, "Blakcyl", null, null), 8, 6);
        onInsert(db, new Beer(0, "Warka Strong", null, null), 9, 7);
        onInsert(db, new Beer(0, "Zeus", null, null), 10, 8);
        onInsert(db, new Beer(0, "Doctor Brew RIS", null, null), 11, 9);
        onInsert(db, new Beer(0, "Żywiec Bock", null, null), 12, 2);
        onInsert(db, new Beer(0, "Żywiec Porter", null, null), 13, 2);

    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        BeerTable.onCreate(db);
    }

    public static void onInsert(SQLiteDatabase db, Beer beer, int idStyle, int idWorks){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_BEER, beer.getNameBeer());
        contentValues.put(STYLE, idStyle);
        contentValues.put(WORKS, idWorks);
        db.insert(TABLE_NAME, null, contentValues);
    }
}

