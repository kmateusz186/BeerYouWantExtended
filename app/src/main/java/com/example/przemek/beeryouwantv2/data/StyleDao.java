package com.example.przemek.beeryouwantv2.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import com.example.przemek.beeryouwantv2.Table.BeerTable;
import com.example.przemek.beeryouwantv2.Table.StyleTable;
import com.example.przemek.beeryouwantv2.model.Beer;
import com.example.przemek.beeryouwantv2.model.Province;
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

    public List<Beer> getBeersFromStyle(long styleId) {
        List<Beer> list = new ArrayList<>();
        String sql =
                "select " + BeerTable.TABLE_NAME + "." + BaseColumns._ID + ", "
                        + BeerTable.TABLE_NAME + "." + BeerTable.NAME_BEER + ", "
                        + BeerTable.TABLE_NAME + "." + BeerTable.STYLE + ", "
                        + BeerTable.TABLE_NAME + "." + BeerTable.WORKS + " from "
                        + BeerTable.TABLE_NAME + " where "
                        + BeerTable.TABLE_NAME + "." + BeerTable.STYLE + " = ?";
        Cursor c = db.rawQuery(sql, new String[] { String.valueOf(styleId) });
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

    public List<Style> getChosenStyles(String color, int bitter, int malt, int alcohol, String wheat_malt, String fermentation) {
        List<Style> list = new ArrayList<>();
        Cursor cursor = null;
        if (!color.equals("unknown")) {
            if (bitter != 0) {
                if (malt != 0) {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ? AND ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ? AND ALCOHOL = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol)},
                                        null, null, null
                                );
                            }
                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ? AND MALT_WHEAT = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(malt)},
                                        null, null, null
                                );
                            }
                        }
                    }
                } else {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND ALCOHOL = ?",
                                        new String[]{color, Integer.toString(bitter), Integer.toString(alcohol)},
                                        null, null, null
                                );
                            }
                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND MALT_WHEAT = ?",
                                        new String[]{color, Integer.toString(bitter), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(bitter), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND BITTER = ?",
                                        new String[]{color, Integer.toString(bitter)},
                                        null, null, null
                                );
                            }
                        }
                    }
                }
            } else {
                if (malt != 0) {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(malt), Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{color, Integer.toString(malt), Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ? AND ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(malt), Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ? AND ALCOHOL = ?",
                                        new String[]{color, Integer.toString(malt), Integer.toString(alcohol)},
                                        null, null, null
                                );
                            }

                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(malt), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ? AND MALT_WHEAT = ?",
                                        new String[]{color, Integer.toString(malt), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(malt), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT = ?",
                                        new String[]{color, Integer.toString(malt)},
                                        null, null, null
                                );
                            }
                        }
                    }
                } else {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{color, Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{color, Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND ALCOHOL = ?",
                                        new String[]{color, Integer.toString(alcohol)},
                                        null, null, null
                                );
                            }
                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{color, wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND MALT_WHEAT = ?",
                                        new String[]{color, wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ? AND FERMENTATION = ?",
                                        new String[]{color, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query("STYLE", new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "COLOR = ?",
                                        new String[]{color},
                                        null, null, null
                                );
                            }
                        }
                    }
                }
            }
        } else {
            if (bitter != 0) {
                if (malt != 0) {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ? AND ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ? AND ALCOHOL = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt), Integer.toString(alcohol)},
                                        null, null, null
                                );
                            }
                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ? AND MALT_WHEAT = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(malt)},
                                        null, null, null
                                );
                            }
                        }
                    }
                } else {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            }
                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND MALT_WHEAT = ?",
                                        new String[]{Integer.toString(bitter), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(bitter), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "BITTER = ?",
                                        new String[]{Integer.toString(bitter)},
                                        null, null, null
                                );
                            }
                        }
                    }
                }
            } else {
                if (malt != 0) {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(malt), Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ? AND ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{Integer.toString(malt), Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ? AND ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(malt), Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ? AND ALCOHOL = ?",
                                        new String[]{Integer.toString(malt), Integer.toString(alcohol)},
                                        null, null, null
                                );
                            }
                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(malt), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ? AND MALT_WHEAT = ?",
                                        new String[]{Integer.toString(malt), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(malt), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT = ?",
                                        new String[]{Integer.toString(malt)},
                                        null, null, null
                                );
                            }
                        }
                    }
                } else {
                    if (alcohol != 0) {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "ALCOHOL = ? AND MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(alcohol), wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "ALCOHOL = ? AND MALT_WHEAT = ?",
                                        new String[]{Integer.toString(alcohol), wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "ALCOHOL = ? AND FERMENTATION = ?",
                                        new String[]{Integer.toString(alcohol), fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "ALCOHOL = ?",
                                        new String[]{Integer.toString(alcohol)},
                                        null, null, null
                                );
                            }
                        }
                    } else {
                        if (!wheat_malt.equals("unknown")) {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT_WHEAT = ? AND FERMENTATION = ?",
                                        new String[]{wheat_malt, fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "MALT_WHEAT = ?",
                                        new String[]{wheat_malt},
                                        null, null, null
                                );
                            }
                        } else {
                            if (!fermentation.equals("unknown")) {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, "FERMENTATION = ?",
                                        new String[]{fermentation},
                                        null, null, null
                                );
                            } else {
                                cursor = db.query(StyleTable.TABLE_NAME, new String[]{BaseColumns._ID, StyleTable.NAME_STYLE}, null,
                                        null,
                                        null, null, null
                                );
                            }
                        }
                    }
                }
            }
        }
        if (cursor.moveToFirst()) {
            do {
                Style style = new Style(cursor.getInt(0), cursor.getString(1));
                list.add(style);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
}
