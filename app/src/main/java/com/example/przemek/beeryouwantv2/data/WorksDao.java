package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.Table.BeerTable;
import com.example.przemek.beeryouwantv2.Table.WorksTable;
import com.example.przemek.beeryouwantv2.model.Beer;
import com.example.przemek.beeryouwantv2.model.Works;

import java.util.ArrayList;
import java.util.List;

public class WorksDao implements Dao<Works> {

    private static final String INSERT =
            "insert into " + WorksTable.TABLE_NAME
                    + "(" + WorksTable.NAME_WORKS +
                    ", " + WorksTable.IMAGE_RESOURCE_ID +
                    ", " + WorksTable.DESCRIPTION +
                    ", " + WorksTable.PROVINCE +
                    ", " + WorksTable.FAVOURITE + ")" +
                    " values (?, ?, ?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public WorksDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(WorksDao.INSERT);
    }

    @Override
    public long save(Works type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getNameWorks());
        insertStatement.bindLong(2, type.getImageResourceID());
        insertStatement.bindString(3, type.getDescriptionWorks());
        insertStatement.bindLong(4, (long) type.getProvince().getIdProvince());
        insertStatement.bindLong(5, (long) type.getFavouriteWorks());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(Works type) {
        final ContentValues values = new ContentValues();
        //values.put(WorksTable.NAME_WORKS, type.getNameWorks());
        //values.put(WorksTable.IMAGE_RESOURCE_ID, type.getImageResourceID());
        //values.put(WorksTable.DESCRIPTION, type.getDescriptionWorks());
        //values.put(WorksTable.PROVINCE, type.getProvince().getIdProvince());
        values.put(WorksTable.FAVOURITE, type.getFavouriteWorks());
        db.update(WorksTable.TABLE_NAME, values, BaseColumns._ID
                        + " = ?",
                new String[] { String.valueOf(type.getIdWorks()) });
    }

    @Override
    public void delete(Works type) {
        if (type.getIdWorks() > 0) {
            db.delete(WorksTable.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] { String.valueOf(type.getIdWorks())});
        }
    }

    @Override
    public Works get(int id) {
        Works works = null;
        Cursor c = db.query(WorksTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, WorksTable.NAME_WORKS,
                        WorksTable.IMAGE_RESOURCE_ID, WorksTable.DESCRIPTION,
                        WorksTable.PROVINCE, WorksTable.FAVOURITE},
                BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id)},
                null, null, null, "1");
        if(c.moveToFirst()) {
            works = new Works(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getInt(4), c.getInt(5));
        }
        if(!c.isClosed()) {
            c.close();
        }
        return works;
    }

    @Override
    public List<Works> getAll() {
        Works works = null;
        List<Works> list = new ArrayList<>();
        Cursor c = db.query(WorksTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, WorksTable.NAME_WORKS,
                        WorksTable.IMAGE_RESOURCE_ID, WorksTable.DESCRIPTION,
                        WorksTable.PROVINCE, WorksTable.FAVOURITE},
                null, null, null, null, WorksTable.NAME_WORKS, null);
        if(c.moveToFirst()) {
            do {
                works = new Works(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getInt(4), c.getInt(5));
                if (works != null) {
                    list.add(works);
                }
            } while (c.moveToNext());
        }
        if(!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public List<Beer> getBeersFromWorks(long worksId) {
        List<Beer> list = new ArrayList<>();
        String sql =
                "select " + BeerTable.TABLE_NAME + "." + BaseColumns._ID + ", "
                        + BeerTable.TABLE_NAME + "." + BeerTable.NAME_BEER + ", "
                        + BeerTable.TABLE_NAME + "." + BeerTable.STYLE + ", "
                        + BeerTable.TABLE_NAME + "." + BeerTable.WORKS + " from "
                        + BeerTable.TABLE_NAME + " where "
                        + BeerTable.TABLE_NAME + "." + BeerTable.WORKS + " = ?";
        Cursor c = db.rawQuery(sql, new String[] { String.valueOf(worksId) });
        if (c.moveToFirst()) {
            do {
                Beer beer = new Beer(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
                list.add(beer);
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public List<Works> getFavouriteWorks() {
        List<Works> list = new ArrayList<>();
        String sql =
                "select " + WorksTable.TABLE_NAME + "." + BaseColumns._ID + ", "
                        + WorksTable.TABLE_NAME + "." + WorksTable.NAME_WORKS + ", "
                        + WorksTable.TABLE_NAME + "." + WorksTable.PROVINCE + " from "
                        + WorksTable.TABLE_NAME + " where "
                        + WorksTable.TABLE_NAME + "." + WorksTable.FAVOURITE + " = ?";
        Cursor c = db.rawQuery(sql, new String[] { String.valueOf(1) });
        if (c.moveToFirst()) {
            do {
                Works works = new Works(c.getInt(0), c.getString(1), c.getInt(2));
                list.add(works);
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
