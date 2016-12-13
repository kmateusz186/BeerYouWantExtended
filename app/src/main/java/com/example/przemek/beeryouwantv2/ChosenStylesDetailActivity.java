package com.example.przemek.beeryouwantv2;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.przemek.beeryouwantv2.model.Beer;
import com.example.przemek.beeryouwantv2.model.Style;

import java.util.ArrayList;

public class ChosenStylesDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STYLENO = "style";
    private int styleNo;
    MyApplication app;
    BeerAdapter adapter;

    private TextView nameTextView;
    private TextView colorTextView;
    private TextView bitterTextView;
    private TextView maltTextView;
    private TextView alcoholTextView;
    private TextView maltWheatTextView;
    private TextView fermentationTextView;
    private ListView listView;

    private String name;
    private String color;
    private String bitter;
    private String malt;
    private String alcohol;
    private String maltWheat;
    private String fermentation;

    private ArrayList<Beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_chosen_styles_detail);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        styleNo = getIntent().getExtras().getInt(EXTRA_STYLENO);
        app = (MyApplication) getApplication();

        nameTextView = (TextView) findViewById(R.id.chosen_style_detail_name);
        colorTextView = (TextView) findViewById(R.id.chosen_style_detail_color);
        bitterTextView = (TextView) findViewById(R.id.chosen_style_detail_bitter);
        maltTextView = (TextView) findViewById(R.id.chosen_style_detail_malt);
        alcoholTextView = (TextView) findViewById(R.id.chosen_style_detail_alcohol);
        maltWheatTextView = (TextView) findViewById(R.id.chosen_style_detail_wheat_malt);
        fermentationTextView = (TextView) findViewById(R.id.chosen_style_detail_fermentation);

        Style style = app.getDataManager().getStyle(styleNo);

        name = style.getNameStyle();
        color = style.getColorStyle();
        bitter = app.getDataManager().getBMLevel(style.getIdBitter()).getNameBMLevel();
        malt = app.getDataManager().getBMLevel(style.getIdMalt()).getNameBMLevel();
        alcohol = app.getDataManager().getALevel(style.getIdAlcohol()).getNameALevel();
        maltWheat = style.getMaltWheat();
        fermentation = style.getFermentation();

        nameTextView.setText(name);
        colorTextView.setText(color);
        bitterTextView.setText(bitter);
        maltTextView.setText(malt);
        alcoholTextView.setText(alcohol);
        maltWheatTextView.setText(maltWheat);
        fermentationTextView.setText(fermentation);
        getSupportActionBar().setTitle(R.string.chosen_styles_detail_activity_title);

        listView = (ListView) findViewById(R.id.chosen_styles_list_view);
        beers = new ArrayList<>();
        beers.clear();
        beers.addAll(style.getBeerList());
        adapter = new BeerAdapter(this, beers);
        listView.setAdapter(adapter);
    }

    private class BeerAdapter extends ArrayAdapter<Beer> {

        private ArrayList<Beer> beerList;

        public BeerAdapter(Context context, ArrayList<Beer> beerList) {
            super(context, 0, beerList);
            this.beerList = new ArrayList<Beer>();
            this.beerList.addAll(beerList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final Beer beer = beerList.get(position);
            TextView tvName = null;
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_beer_style, parent, false);
                tvName = (TextView) convertView.findViewById(R.id.tvNameBeerStyle);
                tvName.setText(beer.getNameBeer());
            }
            // Lookup view for data population

            // Populate the data into the template view using the data object
            Log.v("Adapter", beer.getNameBeer());
            //tvName.setText(province.getNameProvince());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
