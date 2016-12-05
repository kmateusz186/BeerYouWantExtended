package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.Table.ALevelTable;
import com.example.przemek.beeryouwantv2.model.ALevel;

import java.util.ArrayList;
import java.util.List;


public class ALevelDao implements Dao<ALevel> {

    private static final String INSERT =
            "insert into " + ALevelTable.TABLE_NAME
                    + "(" + ALevelTable.NAME_ALEVEL + ")" +
                    " values (?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public ALevelDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(ALevelDao.INSERT);
    }

    @Override
    public long save(ALevel type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getNameALevel());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(ALevel type) {
        final ContentValues values = new ContentValues();
        values.put(ALevelTable.NAME_ALEVEL, type.getNameALevel());
        db.update(ALevelTable.TABLE_NAME, values, BaseColumns._ID
                        + " = ?",
                new String[] { String.valueOf(type.getIdALevel()) });
    }

    @Override
    public void delete(ALevel type) {
        if (type.getIdALevel() > 0) {
            db.delete(ALevelTable.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] { String.valueOf(type.getIdALevel())});
        }
    }

    @Override
    public ALevel get(int id) {
        ALevel aLevel = null;
        Cursor c = db.query(ALevelTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, ALevelTable.NAME_ALEVEL},
                BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id)},
                null, null, null, "1");
        if(c.moveToFirst()) {
            aLevel = new ALevel();
            aLevel.setIdALevel(c.getInt(0));
            aLevel.setNameALevel(c.getString(1));
        }
        if(!c.isClosed()) {
            c.close();
        }
        return aLevel;
    }

    @Override
    public List<ALevel> getAll() {
        List<ALevel> list = new ArrayList<>();
        Cursor c = db.query(ALevelTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, ALevelTable.NAME_ALEVEL},
                null, null, null, null, ALevelTable.NAME_ALEVEL, null);
        if(c.moveToFirst()) {
            do {
                ALevel aLevel = new ALevel();
                aLevel.setIdALevel(c.getInt(0));
                aLevel.setNameALevel(c.getString(1));
                list.add(aLevel);
            } while (c.moveToNext());
        }
        if(!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
