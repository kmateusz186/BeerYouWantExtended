package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.Table.StyleTable;
import com.example.przemek.beeryouwantv2.model.Style;

import java.util.ArrayList;
import java.util.List;


public class StyleDao implements Dao<Style> {

    private static final String INSERT =
            "insert into " + StyleTable.TABLE_NAME
                    + "(" + StyleTable.NAME_STYLE +
                    ", " + StyleTable.COLOR +
                    ", " + StyleTable.BITTER +
                    ", " + StyleTable.MALT +
                    ", " + StyleTable.ALCOHOL +
                    ", " + StyleTable.MALT_WHEAT +
                    ", " + StyleTable.FERMENTATION  +")" +
                    " values (?, ?, ?, ?, ?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public StyleDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(StyleDao.INSERT);
    }

    @Override
    public long save(Style type) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, type.getNameStyle());
        insertStatement.bindString(2, type.getColorStyle());
        insertStatement.bindLong(3, (long) type.getBitter().getIdBMLevel());
        insertStatement.bindLong(4, (long) type.getMalt().getIdBMLevel());
        insertStatement.bindLong(5, (long) type.getAlcohol().getIdALevel());
        insertStatement.bindString(6, type.getMaltWheat());
        insertStatement.bindString(7, type.getFermentation());
        return insertStatement.executeInsert();
    }

    @Override
    public void update(Style type) {
        final ContentValues values = new ContentValues();
        values.put(StyleTable.NAME_STYLE, type.getNameStyle());
        values.put(StyleTable.COLOR, type.getColorStyle());
        values.put(StyleTable.BITTER, type.getBitter().getIdBMLevel());
        values.put(StyleTable.MALT, type.getMalt().getIdBMLevel());
        values.put(StyleTable.ALCOHOL, type.getAlcohol().getIdALevel());
        values.put(StyleTable.MALT_WHEAT, type.getMaltWheat());
        values.put(StyleTable.FERMENTATION, type.getFermentation());
        db.update(StyleTable.TABLE_NAME, values, BaseColumns._ID
                        + " = ?",
                new String[] { String.valueOf(type.getIdStyle()) });
    }

    @Override
    public void delete(Style type) {
        if (type.getIdStyle() > 0) {
            db.delete(StyleTable.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] { String.valueOf(type.getIdStyle())});
        }
    }

    @Override
    public Style get(int id) {
        Style style = null;
        Cursor c = db.query(StyleTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, StyleTable.NAME_STYLE,
                        StyleTable.COLOR, StyleTable.BITTER,
                        StyleTable.MALT, StyleTable.ALCOHOL, StyleTable.MALT_WHEAT, StyleTable.FERMENTATION},
                BaseColumns._ID + " = ?",
                new String[] { String.valueOf(id)},
                null, null, null, "1");
        if(c.moveToFirst()) {
            style = new Style(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getString(6), c.getString(7));
        }
        if(!c.isClosed()) {
            c.close();
        }
        return style;
    }

    @Override
    public List<Style> getAll() {
        Style style = null;
        List<Style> list = new ArrayList<>();
        Cursor c = db.query(StyleTable.TABLE_NAME,
                new String[] {
                        BaseColumns._ID, StyleTable.NAME_STYLE,
                        StyleTable.COLOR, StyleTable.BITTER,
                        StyleTable.MALT, StyleTable.ALCOHOL, StyleTable.MALT_WHEAT, StyleTable.FERMENTATION},
                null, null, null, null, StyleTable.NAME_STYLE, null);
        if(c.moveToFirst()) {
            do {
                style = new Style(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getString(6), c.getString(7));
                if (style != null) {
                    list.add(style);
                }
            } while (c.moveToNext());
        }
        if(!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
