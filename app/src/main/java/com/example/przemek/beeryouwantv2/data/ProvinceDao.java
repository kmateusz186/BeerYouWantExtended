package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.Table.ProvinceTable;
import com.example.przemek.beeryouwantv2.model.Province;

import java.util.ArrayList;
import java.util.List;


public class ProvinceDao implements Dao<Province> {

    private static final String INSERT =
            "insert into " + ProvinceTable.TABLE_NAME
                    + "(" + ProvinceTable.NAME_PROVINCE +
                    ", " + ProvinceTable.COUNTRY + ")" +
                    " values (?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public ProvinceDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(ProvinceDao.INSERT);
    }
    @Override
    public long save(Province type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getNameProvince());
        insertStatement.bindLong(2, (long) type.getCountry().getIdCountry());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(Province type) {
        final ContentValues values = new ContentValues();
        values.put(ProvinceTable.NAME_PROVINCE, type.getNameProvince());
        values.put(ProvinceTable.COUNTRY, type.getCountry().getIdCountry());
        db.update(ProvinceTable.TABLE_NAME, values, BaseColumns._ID
                        + " = ?",
                new String[] { String.valueOf(type.getIdProvince()) });
    }

    @Override
    public void delete(Province type) {
        if (type.getIdProvince() > 0) {
            db.delete(ProvinceTable.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] { String.valueOf(type.getIdProvince())});
        }
    }

    @Override
    public Province get(int id) {
        Province province = null;
        Cursor c = db.query(ProvinceTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, ProvinceTable.NAME_PROVINCE,
                        ProvinceTable.COUNTRY},
                BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id)},
                null, null, null, "1");
        if(c.moveToFirst()) {
            province = new Province(c.getInt(0), c.getString(1), c.getInt(2));
        }
        if(!c.isClosed()) {
            c.close();
        }
        return province;
    }

    @Override
    public List<Province> getAll() {
        Province province = null;
        List<Province> list = new ArrayList<>();
        Cursor c = db.query(ProvinceTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, ProvinceTable.NAME_PROVINCE,
                        ProvinceTable.COUNTRY},
                null, null, null, null, ProvinceTable.NAME_PROVINCE, null);
        if(c.moveToFirst()) {
            do {
                province = new Province(c.getInt(0), c.getString(1), c.getInt(2));
                if (province != null) {
                    list.add(province);
                }
            } while (c.moveToNext());
        }
        if(!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
