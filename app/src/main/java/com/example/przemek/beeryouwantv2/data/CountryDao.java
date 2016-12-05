package com.example.przemek.beeryouwantv2.data;

import com.example.przemek.beeryouwantv2.model.Country;

import java.util.List;



public class CountryDao implements Dao<Country> {

    @Override
    public long save(Country type) {
        return 0;
    }

    @Override
    public void update(Country type) {

    }

    @Override
    public void delete(Country type) {

    }

    @Override
    public Country get(long id) {
        return null;
    }

    @Override
    public List<Country> getAll() {
        return null;
    }
}
