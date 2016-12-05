package com.example.przemek.beeryouwantv2;

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

public interface DataManager {
    ALevel getALevel( int idALevel );
    List<ALevel> getALevels();
    int saveALevel(ALevel aLevel);
    boolean deleteALevel( int idALevel);
    boolean updateALevel (ALevel aLevel);

    BMLevel getBMLevel( int idBMLevel );
    List<BMLevel> getBMLevels();
    int saveBMLevel( BMLevel bmLevel);
    boolean deleteBMLevel( int idBMLevel);
    boolean updateBMLevel (BMLevel bmLevel);

    Beer getBeer(int idBeer );
    List<Beer> getBeers();
    int saveBeer( Beer beer);
    boolean deleteBeer(int idBeer);
    boolean updateBeer (Beer beer);

    Country getCountry(int idCountry );
    List<Country> getCountries();
    int saveCountry( Country country);
    boolean deleteCountry(int idCountry);
    boolean updateCountry (Country country);

    Province getProvince(int idProvince );
    List<Province> getProvinces();
    int saveProvince( Province province);
    boolean deleteProvince(int idProvince);
    boolean updateProvince (Province province);

    Style getStyle(int idStyle );
    List<Style> getStyles();
    int saveStyle( Style style);
    boolean deleteStyle(int idStyle);
    boolean updateStyle (Style Style);

    Works getWorks(int idWorks );
    List<Works> getWorkses();
    int saveWorks( Works works);
    boolean deleteWorks(int idWorks);
    boolean updateWorks (Works works);

}
