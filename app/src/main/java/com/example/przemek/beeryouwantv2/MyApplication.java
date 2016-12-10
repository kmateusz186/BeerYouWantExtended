package com.example.przemek.beeryouwantv2;

import android.app.Application;

/**
 * Created by M@ti on 2016-12-10.
 */

public class MyApplication extends Application {
    private DataManager dataManager;

    public DataManager getDataManager() {
        return this.dataManager;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        dataManager = new DataManagerImplementation(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
