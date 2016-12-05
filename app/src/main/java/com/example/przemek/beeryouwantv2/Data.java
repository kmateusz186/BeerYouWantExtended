package com.example.przemek.beeryouwantv2;

import android.os.Environment;

/**
 * Created by Przemek on 05.12.2016.
 */
public class Data {
    private static final String APPLICATION_PACKAGE_NAME = "com.example.przemek.pm_lab06_v3";

    private static final String EXTERNAL_DATA_NAME = "studentsManager";
    public static final String EXTERNAL_DATA_PATH =
            Environment.getExternalStorageDirectory() + "/" + Data.EXTERNAL_DATA_NAME;

    public static final String DATABASE_NAME = "studentsManager.db";
    public static final String DATABASE_PATH =
            Environment.getDataDirectory() + "/data/" + Data.APPLICATION_PACKAGE_NAME + "/databases/"
                    + Data.DATABASE_NAME;


}
