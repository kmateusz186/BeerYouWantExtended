package com.example.przemek.beeryouwantv2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by Przemek on 12.12.2016.
 */

public class TimeOfAlcohol extends Service implements Serializable {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
