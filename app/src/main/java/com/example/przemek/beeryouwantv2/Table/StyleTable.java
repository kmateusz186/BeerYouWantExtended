package com.example.przemek.beeryouwantv2.Table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.model.Style;

/**
 * Created by Przemek on 04.12.2016.
 */

public class StyleTable {
    public static final String TABLE_NAME = "style";
    //public static final String ID_STYLE = "id_style";
    public static final String NAME_STYLE = "name";
    public static final String COLOR = "color";
    public static final String MALT_WHEAT = "malt_wheat";
    public static final String FERMENTATION = "fermentation";
    public static final String ALCOHOL = "alcohol";
    public static final String MALT = "malt";
    public static final String BITTER = "bitter";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_STYLE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_STYLE + " TEXT,"
                + COLOR + " TEXT,"
                + BITTER + " INTEGER,"
                + MALT + " INTEGER,"
                + ALCOHOL + " INTEGER,"
                + MALT_WHEAT + " TEXT,"
                + FERMENTATION + " TEXT,"
                + "FOREIGN KEY(" + BITTER + ")"
                + "REFERENCES " + BMLevelTable.TABLE_NAME + "(" + BaseColumns._ID + "),"
                + "FOREIGN KEY(" + MALT + ")"
                + "REFERENCES " + BMLevelTable.TABLE_NAME + "(" + BaseColumns._ID + "),"
                +"FOREIGN KEY(" + ALCOHOL + ")"
                + "REFERENCES " + ALevelTable.TABLE_NAME + "(" + BaseColumns._ID + ")"
                + ")";
        db.execSQL(CREATE_STYLE_TABLE);
        onInsert(db, new Style(0, "Eurolager", "Jasne","Opcjonalnie", "Dolna", null, null, null, null), 1, 1, 2);
        onInsert(db, new Style(0, "American Pale Ale", "Jasne","Opcjonalnie", "Górna", null, null, null, null), 2, 2, 2);
        onInsert(db, new Style(0, "India Pale Ale", "Jasne","Opcjonalnie", "Górna", null, null, null, null), 3, 2, 2);
        onInsert(db, new Style(0, "Barley Wine", "Jasne","Opcjonalnie", "Górna", null, null, null, null), 3, 3, 3);
        onInsert(db, new Style(0, "Stout", "Ciemne","Opcjonalnie", "Górna", null, null, null, null), 1, 2, 2);
        onInsert(db, new Style(0, "Grodziskie", "Jasne","Tak", "Górna", null, null, null, null), 1, 1, 1);
        onInsert(db, new Style(0, "Pszeniczne", "Jasne","Tak", "Górna", null, null, null, null), 1, 2, 2);
        onInsert(db, new Style(0, "Black India Pale Ale", "Ciemne","Opcjonalnie", "Górna", null, null, null, null), 3, 2, 2);
        onInsert(db, new Style(0, "Strong Lager", "Jasne","Opcjonalnie", "Dolna", null, null, null, null), 1, 1, 3);
        onInsert(db, new Style(0, "Double India Pale Ale", "Jasne","Opcjonalnie", "Górna", null, null, null, null), 3, 2, 3);
        onInsert(db, new Style(0, "Russian Imperial Stout", "Ciemne","Opcjonalnie", "Górna", null, null, null, null), 3, 3, 3);
        onInsert(db, new Style(0, "Koźlak", "Jasne","Opcjonalnie", "Dolna", null, null, null, null), 2, 3, 2);
        onInsert(db, new Style(0, "Porter Bałtycki", "Ciemne","Opcjonalnie", "Dolna", null, null, null, null), 3, 3, 3);


    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        StyleTable.onCreate(db);
    }

    public static void onInsert(SQLiteDatabase db, Style style, int idBitter, int idMalt, int idAlcohol){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_STYLE, style.getNameStyle());
        contentValues.put(COLOR, style.getColorStyle());
        contentValues.put(BITTER, idBitter);
        contentValues.put(MALT, idMalt);
        contentValues.put(ALCOHOL, idAlcohol);
        contentValues.put(MALT_WHEAT, style.getMaltWheat());
        contentValues.put(FERMENTATION, style.getFermentation());
        db.insert(TABLE_NAME, null, contentValues);
    }
}
