package com.example.przemek.beeryouwantv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;

import com.example.przemek.beeryouwantv2.data.ALevelDao;
import com.example.przemek.beeryouwantv2.data.BMLevelDao;
import com.example.przemek.beeryouwantv2.data.BeerDao;
import com.example.przemek.beeryouwantv2.data.CountryDao;
import com.example.przemek.beeryouwantv2.data.ProvinceDao;
import com.example.przemek.beeryouwantv2.data.StyleDao;
import com.example.przemek.beeryouwantv2.data.WorksDao;
import com.example.przemek.beeryouwantv2.model.ALevel;
import com.example.przemek.beeryouwantv2.model.BMLevel;
import com.example.przemek.beeryouwantv2.model.Beer;
import com.example.przemek.beeryouwantv2.model.Country;
import com.example.przemek.beeryouwantv2.model.Province;
import com.example.przemek.beeryouwantv2.model.Style;
import com.example.przemek.beeryouwantv2.model.Works;

import java.util.List;

/**
 * Created by Przemek on 04.12.2016.
 */

public class DataManagerImplementation implements DataManager {
    private Context context;
    private SQLiteDatabase db;

    private ALevelDao aLevelDao;
    private BMLevelDao bmLevelDao;
    private BeerDao beerDao;
    private CountryDao countryDao;
    private ProvinceDao provinceDao;
    private StyleDao styleDao;
    private WorksDao worksDao;

    public DataManagerImplementation(Context context){
        this.context = context;
        SQLiteOpenHelper openHelper = new OpenHelper(this.context);
        db = openHelper.getWritableDatabase();

        aLevelDao = new ALevelDao(db);
        bmLevelDao = new BMLevelDao(db);
        beerDao = new BeerDao(db);
        countryDao = new CountryDao(db);
        provinceDao = new ProvinceDao(db);
        styleDao = new StyleDao(db);
        worksDao = new WorksDao(db);

    }

    private void openDatabase() {
        if (!db.isOpen()) {
            db = SQLiteDatabase.openDatabase(Data.DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
            aLevelDao = new ALevelDao(db);
            bmLevelDao = new BMLevelDao(db);
            beerDao = new BeerDao(db);
            countryDao = new CountryDao(db);
            provinceDao = new ProvinceDao(db);
            styleDao = new StyleDao(db);
            worksDao = new WorksDao(db);
        }
    }
    private void closeDatabase() {
        if (db.isOpen()) {
            db.close();
        }
    }
    private void resetDb() {
        closeDatabase();
        SystemClock.sleep(500);
        openDatabase();
    }

    @Override
    public ALevel getALevel(int idALevel) {
        ALevel aLevel = aLevelDao.get(idALevel);
        return aLevel;
    }

    @Override
    public List<ALevel> getALevels() {
        return aLevelDao.getAll();
    }

    @Override
    public BMLevel getBMLevel(int idBMLevel) {
        BMLevel bmLevel = bmLevelDao.get(idBMLevel);
        return bmLevel;
    }

    @Override
    public List<BMLevel> getBMLevels() {
        return bmLevelDao.getAll();
    }

    @Override
    public Beer getBeer(int idBeer) {
        Beer beer = beerDao.get(idBeer);
        return beer;
    }

    @Override
    public List<Beer> getBeers() {
        return beerDao.getAll();
    }

    @Override
    public Country getCountry(int idCountry) {
        Country country = countryDao.get(idCountry);
        if(country!=null) {
            country.getProvincesList().addAll(countryDao.getProvinces(country.getIdCountry()));
        }
        return country;
    }

    @Override
    public List<Country> getCountries() {
        return countryDao.getAll();
    }

    @Override
    public Province getProvince(int idProvince) {
        Province province = provinceDao.get(idProvince);
        if(province!=null) {
            province.getWorksList().addAll(provinceDao.getWorks(province.getIdProvince()));
        }
        return province;
    }

    @Override
    public List<Province> getProvinces() {
        return provinceDao.getAll();
    }

    @Override
    public Style getStyle(int idStyle) {
        Style style = styleDao.get(idStyle);
        if(style!=null) {
            style.getBeerList().addAll(styleDao.getBeersFromStyle(style.getIdStyle()));
        }
        return style;
    }

    @Override
    public List<Style> getStyles() {
        return styleDao.getAll();
    }

    @Override
    public List<Style> getChosenStyles(String color, int bitter, int malt, int alcohol, String wheat_malt, String fermentation) {
        return styleDao.getChosenStyles(color, bitter, malt, alcohol, wheat_malt, fermentation);
    }

    @Override
    public Works getWorks(int idWorks) {
        Works works = worksDao.get(idWorks);
        if(works!=null) {
            works.getBeersList().addAll(worksDao.getBeersFromWorks(works.getIdWorks()));
        }
        return works;
    }

    @Override
    public List<Works> getWorkses() {
        return worksDao.getAll();
    }

    @Override
    public boolean updateWorks(Works works) {
        worksDao.update(works);
        return true;
    }

    @Override
    public List<Works> getFavouriteWorks() {
        return worksDao.getFavouriteWorks();
    }
}
