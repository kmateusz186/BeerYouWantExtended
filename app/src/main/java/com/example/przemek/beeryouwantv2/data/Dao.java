package com.example.przemek.beeryouwantv2.data;

import java.util.List;


public interface Dao<T> {
    long save(T type);
    void update(T type);
    void delete(T type);
    T get(int id);
    List<T> getAll();
}
