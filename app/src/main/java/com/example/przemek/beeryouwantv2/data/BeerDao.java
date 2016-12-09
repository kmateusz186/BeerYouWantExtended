package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.Table.BeerTable;
import com.example.przemek.beeryouwantv2.model.Beer;

import java.util.ArrayList;
import java.util.List;



public class BeerDao implements Dao<Beer> {

    private static final String INSERT =
            "insert into " + BeerTable.TABLE_NAME
                    + "(" + BeerTable.NAME_BEER +
                    ", " + BeerTable.STYLE +
                    ", " + BeerTable.WORKS +")" +
                    " values (?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public BeerDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(BeerDao.INSERT);
    }

    @Override
    public long save(Beer type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getNameBeer());
        insertStatement.bindLong(2, (long) type.getStyle().getIdStyle());
        insertStatement.bindLong(3, (long) type.getWorks().getIdWorks());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(Beer type) {
        final ContentValues values = new ContentValues();
        values.put(BeerTable.NAME_BEER, type.getNameBeer());
        values.put(BeerTable.STYLE, type.getStyle().getIdStyle());
        values.put(BeerTable.WORKS, type.getWorks().getIdWorks());
        db.update(BeerTable.TABLE_NAME, values, BaseColumns._ID
                        + " = ?",
                new String[] { String.valueOf(type.getIdBeer()) });
    }

    @Override
    public void delete(Beer type) {
        if (type.getIdBeer() > 0) {
            db.delete(BeerTable.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] { String.valueOf(type.getIdBeer())});
        }
    }

    @Override
    public Beer get(int id) {
        Beer beer = null;
        Cursor c = db.query(BeerTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, BeerTable.NAME_BEER,
                        BeerTable.STYLE, BeerTable.WORKS},
                BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id)},
                null, null, null, "1");
        if(c.moveToFirst()) {
            beer = new Beer(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
            //student = this.buildStudentFromCursor(c);
            /*beer.setIdBeer(c.getInt(0));
            beer.setNameBeer(c.getString(1));
            beer.setStyle(c.getInt(2));
            beer.setWorks(c.getInt(3));*/
        }
        if(!c.isClosed()) {
            c.close();
        }
        return beer;
    }

    @Override
    public List<Beer> getAll() {
        Beer beer = null;
        List<Beer> list = new ArrayList<>();
        Cursor c = db.query(BeerTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, BeerTable.NAME_BEER,
                        BeerTable.STYLE, BeerTable.WORKS},
                null, null, null, null, BeerTable.NAME_BEER, null);
        if(c.moveToFirst()) {
            do {
                beer = new Beer(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3));
                if (beer != null) {
                    list.add(beer);
                }
            } while (c.moveToNext());
        }
        if(!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
