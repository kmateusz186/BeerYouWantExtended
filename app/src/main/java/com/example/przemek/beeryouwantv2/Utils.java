package com.example.przemek.beeryouwantv2;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * Created by M@ti on 2016-12-12.
 */

public class Utils {
    public static void setsTheme(int sTheme) {
        Utils.sTheme = sTheme;
    }

    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_ORANGE = 1;
    public final static int THEME_GREY = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        //activity.startActivity(activity.getIntent());
        Log.v("UTILS", sTheme + "");
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * Set the theme of the activity, according to the configuration.
     *
     * @param activity
     */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_ORANGE:
                activity.setTheme(R.style.MyFirstTheme);
                break;
            case THEME_GREY:
                activity.setTheme(R.style.MySecondTheme);
                break;
        }
    }
}