package com.example.przemek.beeryouwantv2.Table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Przemek on 04.12.2016.
 */

public class StyleTable {
    public static final String TABLE_NAME = "style";
    public static final String ID_STYLE = "id_style";
    public static final String NAME_STYLE = "name";
    public static final String COLOR = "color";
    public static final String MALT_WHEAT = "malt_wheat";
    public static final String FERMENTATION = "fermentation";
    public static final String ALCOHOL = "alcohol";
    public static final String MALT = "malt";
    public static final String BITTER = "bitter";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_STYLE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID_STYLE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_STYLE + " TEXT,"
                + COLOR + " TEXT,"
                + BITTER + " INTEGER,"
                + MALT + " INTEGER,"
                + ALCOHOL + " INTEGER,"
                + MALT_WHEAT + " TEXT,"
                + FERMENTATION + " TEXT,"
                + "FOREIGN KEY(" + BITTER + ")"
                + "REFERENCES " + BMLevelTable.TABLE_NAME + "(" + BMLevelTable.ID_BMLEVEL + "),"
                + "FOREIGN KEY(" + MALT + ")"
                + "REFERENCES " + BMLevelTable.TABLE_NAME + "(" + BMLevelTable.ID_BMLEVEL + "),"
                +"FOREIGN KEY(" + ALCOHOL + ")"
                + "REFERENCES " + ALevelTable.TABLE_NAME + "(" + ALevelTable.ID_ALEVEL + ")"
                + ")";
        db.execSQL(CREATE_STYLE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        StyleTable.onCreate(db);
    }
}
