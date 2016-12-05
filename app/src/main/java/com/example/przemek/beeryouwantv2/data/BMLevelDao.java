package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.Table.BMLevelTable;
import com.example.przemek.beeryouwantv2.model.BMLevel;

import java.util.ArrayList;
import java.util.List;


public class BMLevelDao implements Dao<BMLevel> {

    private static final String INSERT =
            "insert into " + BMLevelTable.TABLE_NAME
                    + "(" + BMLevelTable.NAME_BMLEVEL + ")" +
                    " values (?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public BMLevelDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(BMLevelDao.INSERT);
    }

    @Override
    public long save(BMLevel type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getNameBMLevel());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(BMLevel type) {
        final ContentValues values = new ContentValues();
        values.put(BMLevelTable.NAME_BMLEVEL, type.getNameBMLevel());
        db.update(BMLevelTable.TABLE_NAME, values, BaseColumns._ID
                        + " = ?",
                new String[] { String.valueOf(type.getIdBMLevel()) });
    }

    @Override
    public void delete(BMLevel type) {
        if (type.getIdBMLevel() > 0) {
            db.delete(BMLevelTable.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] { String.valueOf(type.getIdBMLevel())});
        }
    }

    @Override
    public BMLevel get(int id) {
        BMLevel bmLevel = null;
        Cursor c = db.query(BMLevelTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, BMLevelTable.NAME_BMLEVEL},
                BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id)},
                null, null, null, "1");
        if(c.moveToFirst()) {
            bmLevel = new BMLevel();
            bmLevel.setIdBMLevel(c.getInt(0));
            bmLevel.setNameBMLevel(c.getString(1));
        }
        if(!c.isClosed()) {
            c.close();
        }
        return bmLevel;
    }

    @Override
    public List<BMLevel> getAll() {
        List<BMLevel> list = new ArrayList<>();
        Cursor c = db.query(BMLevelTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, BMLevelTable.NAME_BMLEVEL},
                null, null, null, null, BMLevelTable.NAME_BMLEVEL, null);
        if(c.moveToFirst()) {
            do {
                BMLevel aLevel = new BMLevel();
                aLevel.setIdBMLevel(c.getInt(0));
                aLevel.setNameBMLevel(c.getString(1));
                list.add(aLevel);
            } while (c.moveToNext());
        }
        if(!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
