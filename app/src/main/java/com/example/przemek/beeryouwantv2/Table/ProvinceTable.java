package com.example.przemek.beeryouwantv2.Table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.model.Province;

/**
 * Created by Przemek on 04.12.2016.
 */

public class ProvinceTable {
    public static final String TABLE_NAME = "province";
    //public static final String ID_PROVINCE = "id_province";
    public static final String NAME_PROVINCE = "name";
    public static final String COUNTRY = "country";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_PROVINCE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_PROVINCE + " TEXT,"
                + COUNTRY + " INTEGER,"
                + "FOREIGN KEY(" + COUNTRY + ")"
                + "REFERENCES " + CountryTable.TABLE_NAME + "(" + BaseColumns._ID + ")"
                + ")";
        db.execSQL(CREATE_PROVINCE_TABLE);
        onInsert(db, new Province(0, "Małopolskie", null, null), 1);
        onInsert(db, new Province(0, "Śląskie", null, null), 1);
        onInsert(db, new Province(0, "Mazowieckie", null, null), 1);
        onInsert(db, new Province(0, "Kujawsko-Pomorskie", null, null), 1);
        onInsert(db, new Province(0, "Dolnośląskie", null, null), 1);

    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        ProvinceTable.onCreate(db);
    }

    public static void onInsert(SQLiteDatabase db, Province province, int idCountry){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_PROVINCE, province.getNameProvince());
        contentValues.put(COUNTRY, idCountry);
        db.insert(TABLE_NAME, null, contentValues);
    }
}
