package com.example.przemek.beeryouwantv2.Table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.R;
import com.example.przemek.beeryouwantv2.model.Works;

/**
 * Created by Przemek on 04.12.2016.
 */

public class WorksTable {
    public static final String TABLE_NAME = "works";
    public static final String NAME_WORKS = "name";
    public static final String IMAGE_RESOURCE_ID = "image_resource_id";
    public static final String DESCRIPTION = "description";
    public static final String PROVINCE = "province";
    public static final String FAVOURITE =  "favourite";

    public static void onCreate(SQLiteDatabase db){
        String CREATE_WORKS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME_WORKS + " TEXT,"
                + IMAGE_RESOURCE_ID + " INTEGER,"
                + DESCRIPTION + " TEXT,"
                + PROVINCE + " INTEGER,"
                + FAVOURITE + " INTEGER,"
                + "FOREIGN KEY(" + PROVINCE + ")"
                + "REFERENCES " + ProvinceTable.TABLE_NAME + "(" + BaseColumns._ID + ")"
                + ")";
        db.execSQL(CREATE_WORKS_TABLE);

        onInsert(db, new Works(0, "Tyskie Browary Książęce",  R.drawable.tyskie_logo, "Znajduje się w Tychach. Jest cześcią koncernu Kompania Piwowarska SA. "
                + "Producent marek takich jak Tyskie, Lech, Dębowe Mocne, Żubr, a także Książęce.", null, 0, null), 2);

        onInsert(db, new Works(0, "Browar w Żywcu",  R.drawable.zywiec_logo, "Browar sięga swoją historią do 1856 roku. Obecnie należy do grupy Heineken. "
                + "Znany z piw Żywiec APA, Desperados, Żywiec Bock i wiele innych.", null, 0, null), 2);

        onInsert(db, new Works(0, "Browar Węgrzce Wielkie",  R.drawable.wegrzce_wielkie_logo, "Mały browar rzemieślniczy zlokalizowany wśród malowniczych pól podkrakowskiej Wieliczki. "
                + "Producent piw niefiltrowanych, niepasteryzowanych, refermentowanych - Czarny Wilk, Zielony Wilk, Dymione.", null, 0, null), 1);

        onInsert(db, new Works(0, "Pracownia Piwa",  R.drawable.pracownia_piwa_logo, "Zlokalizowany koło Krakowa w Modlniczce. "
                + "Producenci różnych stylów, między innymi Barley Wine, American Pale Ale, Grodziskie, a także Russian Imperial Stout.", null, 0, null), 1);

        onInsert(db, new Works(0, "Browar Ciechan",  R.drawable.ciechan_logo, "Browar mieszczący się w Ciechanowie. "
                + "Aktualnie należy do spółki Browary Regionalne Jakubiak Sp. z o.o.", null,  0, null), 3);

        onInsert(db, new Works(0, "Trzech Kumpli",  R.drawable.trzech_kumpli_logo, "Tarnowski kontraktowy browar lotny - gdyż nie posiada własnego browaru. "
                + "Uwielbiają India Pale Ale, w związku z czym tworzą różne wariacje tego stylu.", null, 0, null), 1);

        onInsert(db, new Works(0, "Browar Warka",  R.drawable.browar_warka_logo, "Powstały w 1968 roku. Wybrano Warkę z powodu długoletniej tradycji piwowarskiej tego miasta. "
                + "Znany z piwa Warka Strong.", null, 0, null), 3);

        onInsert(db, new Works(0, "Browar Olimp",  R.drawable.olimp_logo, "Browar Olimp to inicjatywa kontraktowa powstała w czerwcu 2013 roku w Toruniu. "
                + "Stworzyła ją dwójka piwnych fanatyków – Michał i Marcin. Michał Olszewski swoimi pomysłami od lat zmienia obraz rynku piwnego w Toruniu.", null,  0, null), 4);

        onInsert(db, new Works(0, "Doctor Brew",  R.drawable.doctor_brew_logo, "Browar kontraktowy stworzony przez dwóch pasjonatów. "
                + "Piwa chmielone są najnowszymi, a także najciekawszymi odmianami chmielu. Wysoka goryczka i niepowtarzalny aromat piwa.", null, 0, null), 5);

    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        WorksTable.onCreate(db);
    }

    public static void onInsert(SQLiteDatabase db, Works works, int idProvice){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_WORKS, works.getNameWorks());
        contentValues.put(IMAGE_RESOURCE_ID, works.getImageResourceID());
        contentValues.put(DESCRIPTION, works.getDescriptionWorks());
        contentValues.put(PROVINCE, idProvice);
        contentValues.put(FAVOURITE, works.getFavouriteWorks());
        db.insert(TABLE_NAME, null, contentValues);
    }
}
