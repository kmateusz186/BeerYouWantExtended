package com.example.przemek.beeryouwantv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    private DataManagerImplementation(Context context){

    }

    @Override
    public ALevel getALevel(int idALevel) {
        return null;
    }

    @Override
    public List<ALevel> getALevels() {
        return null;
    }

    @Override
    public int saveALevel(ALevel aLevel) {
        return 0;
    }

    @Override
    public boolean deleteALevel(int idALevel) {
        return false;
    }

    @Override
    public boolean updateALevel(ALevel aLevel) {
        return false;
    }

    @Override
    public BMLevel getBMLevel(int idBMLevel) {
        return null;
    }

    @Override
    public List<BMLevel> getBMLevels() {
        return null;
    }

    @Override
    public int saveBMLevel(BMLevel bmLevel) {
        return 0;
    }

    @Override
    public boolean deleteBMLevel(int idBMLevel) {
        return false;
    }

    @Override
    public boolean updateBMLevel(BMLevel bmLevel) {
        return false;
    }

    @Override
    public Beer getBeer(int idBeer) {
        return null;
    }

    @Override
    public List<Beer> getBeers() {
        return null;
    }

    @Override
    public int saveBeer(Beer beer) {
        return 0;
    }

    @Override
    public boolean deleteBeer(int idBeer) {
        return false;
    }

    @Override
    public boolean updateBeer(Beer beer) {
        return false;
    }

    @Override
    public Country getCountry(int idCountry) {
        return null;
    }

    @Override
    public List<Country> getCountries() {
        return null;
    }

    @Override
    public int saveCountry(Country country) {
        return 0;
    }

    @Override
    public boolean deleteCountry(int idCountry) {
        return false;
    }

    @Override
    public boolean updateCountry(Country country) {
        return false;
    }

    @Override
    public Province getProvince(int idProvince) {
        return null;
    }

    @Override
    public List<Province> getProvinces() {
        return null;
    }

    @Override
    public int saveProvince(Province province) {
        return 0;
    }

    @Override
    public boolean deleteProvince(int idProvince) {
        return false;
    }

    @Override
    public boolean updateProvince(Province province) {
        return false;
    }

    @Override
    public Style getStyle(int idStyle) {
        return null;
    }

    @Override
    public List<Style> getStyles() {
        return null;
    }

    @Override
    public int saveStyle(Style style) {
        return 0;
    }

    @Override
    public boolean deleteStyle(int idStyle) {
        return false;
    }

    @Override
    public boolean updateStyle(Style Style) {
        return false;
    }

    @Override
    public Works getWorks(int idWorks) {
        return null;
    }

    @Override
    public List<Works> getWorkses() {
        return null;
    }

    @Override
    public int saveWorks(Works works) {
        return 0;
    }

    @Override
    public boolean deleteWorks(int idWorks) {
        return false;
    }

    @Override
    public boolean updateWorks(Works works) {
        return false;
    }
}
