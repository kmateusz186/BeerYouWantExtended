package com.example.przemek.beeryouwantv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.przemek.beeryouwantv2.Table.ALevelTable;
import com.example.przemek.beeryouwantv2.Table.BMLevelTable;
import com.example.przemek.beeryouwantv2.Table.BeerTable;
import com.example.przemek.beeryouwantv2.Table.CountryTable;
import com.example.przemek.beeryouwantv2.Table.ProvinceTable;
import com.example.przemek.beeryouwantv2.Table.StyleTable;
import com.example.przemek.beeryouwantv2.Table.WorksTable;

/**
 * Created by Przemek on 05.12.2016.
 */

public class OpenHelper extends SQLiteOpenHelper{
    private static OpenHelper instance;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "beerYouWant.db";
    public static synchronized OpenHelper getHelper(Context context){
        if(instance == null){
            instance = new OpenHelper(context);
        }
        return instance;
    }
    OpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ALevelTable.onCreate(db);
        BMLevelTable.onCreate(db);
        BeerTable.onCreate(db);
        CountryTable.onCreate(db);
        ProvinceTable.onCreate(db);
        StyleTable.onCreate(db);
        WorksTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ALevelTable.onUpgrade(db, oldVersion, newVersion);
        BMLevelTable.onUpgrade(db, oldVersion, newVersion);
        BeerTable.onUpgrade(db, oldVersion, newVersion);
        CountryTable.onUpgrade(db, oldVersion, newVersion);
        ProvinceTable.onUpgrade(db, oldVersion, newVersion);
        StyleTable.onUpgrade(db, oldVersion, newVersion);
        WorksTable.onUpgrade(db, oldVersion, newVersion);


    }
}
