package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.przemek.beeryouwantv2.Table.CountryTable;
import com.example.przemek.beeryouwantv2.Table.ProvinceTable;
import com.example.przemek.beeryouwantv2.model.Country;
import com.example.przemek.beeryouwantv2.model.Province;

import java.util.ArrayList;
import java.util.List;



public class CountryDao implements Dao<Country> {

    private static final String INSERT =
            "insert into " + CountryTable.TABLE_NAME
                    + "(" + CountryTable.NAME_COUNTRY + ")" +
                    " values (?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public CountryDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(CountryDao.INSERT);
    }

    @Override
    public long save(Country type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getNameCountry());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(Country type) {
        final ContentValues values = new ContentValues();
        values.put(CountryTable.NAME_COUNTRY, type.getNameCountry());
        db.update(CountryTable.TABLE_NAME, values, BaseColumns._ID
                        + " = ?",
                new String[] { String.valueOf(type.getIdCountry()) });
    }

    @Override
    public void delete(Country type) {
        if (type.getIdCountry() > 0) {
            db.delete(CountryTable.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] { String.valueOf(type.getIdCountry())});
        }
    }

    @Override
    public Country get(int id) {
        Country country = null;
        Cursor c = db.query(CountryTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, CountryTable.NAME_COUNTRY},
                BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id)},
                null, null, null, "1");
        if(c.moveToFirst()) {
            country = new Country();
            country.setIdCountry(c.getInt(0));
            country.setNameCountry(c.getString(1));
        }
        if(!c.isClosed()) {
            c.close();
        }
        return country;
    }

    @Override
    public List<Country> getAll() {
        List<Country> list = new ArrayList<>();
        Cursor c = db.query(CountryTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, CountryTable.NAME_COUNTRY},
                null, null, null, null, CountryTable.NAME_COUNTRY, null);
        if(c.moveToFirst()) {
            do {
                Country country = new Country();
                country.setIdCountry(c.getInt(0));
                country.setNameCountry(c.getString(1));
                list.add(country);
            } while (c.moveToNext());
        }
        if(!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public List<Province> getProvinces(long countryId) {
        List<Province> list = new ArrayList<>();
        String sql =
                "select " + ProvinceTable.TABLE_NAME + "." + BaseColumns._ID + ", "
                        + ProvinceTable.TABLE_NAME + "." + ProvinceTable.NAME_PROVINCE + ", "
                        + ProvinceTable.TABLE_NAME + "." + ProvinceTable.COUNTRY + " from "
                        + ProvinceTable.TABLE_NAME + " where "
                        + ProvinceTable.TABLE_NAME + "." + ProvinceTable.COUNTRY + " = ?";
        Cursor c = db.rawQuery(sql, new String[] { String.valueOf(countryId) });
        if (c.moveToFirst()) {
            do {
                Province province = new Province(c.getInt(0), c.getString(1), c.getInt(2));
                Log.v("CountryDao", province.getNameProvince());
                list.add(province);
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
