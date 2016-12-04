package com.example.przemek.beeryouwantv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChosenStylesActivity extends AppCompatActivity {

    public static final String EXTRA_COLOR = "color";
    public static final String EXTRA_BITTER = "bitter";
    public static final String EXTRA_MALT = "malt";
    public static final String EXTRA_ALCOHOL = "alcohol";
    public static final String EXTRA_WHEAT_MALT = "wheatmalt";
    public static final String EXTRA_FERMENTATION = "fermentation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_styles);
    }
}
