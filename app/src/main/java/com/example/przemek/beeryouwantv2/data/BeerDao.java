package com.example.przemek.beeryouwantv2.data;

import com.example.przemek.beeryouwantv2.model.Beer;

import java.util.List;



public class BeerDao implements Dao<Beer> {

    @Override
    public long save(Beer type) {
        return 0;
    }

    @Override
    public void update(Beer type) {

    }

    @Override
    public void delete(Beer type) {

    }

    @Override
    public Beer get(long id) {
        return null;
    }

    @Override
    public List<Beer> getAll() {
        return null;
    }
}
