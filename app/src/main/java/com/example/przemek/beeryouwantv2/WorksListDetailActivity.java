package com.example.przemek.beeryouwantv2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.przemek.beeryouwantv2.model.Beer;
import com.example.przemek.beeryouwantv2.model.Province;
import com.example.przemek.beeryouwantv2.model.Works;

import java.util.ArrayList;

public class WorksListDetailActivity extends AppCompatActivity {

    public final static String EXTRA_WORKSNO = "works";
    public final static String EXTRA_MESSAGE = "message";
    MyApplication app;
    BeerAdapter adapter;
    private int worksNo;
    private CheckBox favoriteCheckBox;
    private ContentValues contentValues;

    private TextView nameTextView;
    private ImageView imageView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private ListView listView;

    private String name;
    private int imageResourceId;
    private String description;
    private String country;
    private String province;
    private Boolean isFavorite;
    private Province provinceObject;
    private ArrayList<Beer> beers;

    Works works;
    String message;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_works_list_detail);

        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("my_own_action");
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, filter);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        worksNo = getIntent().getExtras().getInt(EXTRA_WORKSNO);
        nameTextView = (TextView) findViewById(R.id.works_detail_name);
        imageView = (ImageView) findViewById(R.id.works_detail_image);
        descriptionTextView = (TextView) findViewById(R.id.works_detail_description);
        locationTextView = (TextView) findViewById(R.id.works_detail_location);
        favoriteCheckBox = (CheckBox) findViewById(R.id.works_detail_checkbox);
        listView = (ListView) findViewById(R.id.works_detail_list_beers);
        app = (MyApplication) getApplication();

        works = app.getDataManager().getWorks(worksNo);
        name = works.getNameWorks();
        imageResourceId = works.getImageResourceID();
        description = works.getDescriptionWorks();
        provinceObject = app.getDataManager().getProvince(works.getIdProvince());
        //Log.v("WorksListDetailActivity", provinceObject.toString());
        province = provinceObject.getNameProvince();
        country = app.getDataManager().getCountry(provinceObject.getIdCountry()).getNameCountry();
        isFavorite = (works.getFavouriteWorks() == 1);

        nameTextView.setText(name);
        imageView.setImageResource(imageResourceId);
        imageView.setContentDescription(name);
        descriptionTextView.setText(description);
        locationTextView.setText(getResources().getString(R.string.works_detail_location, province, country));
        favoriteCheckBox.setChecked(isFavorite);

        beers = new ArrayList<>();
        beers.clear();
        beers.addAll(works.getBeersList());
        adapter = new BeerAdapter(this, beers);
        listView.setAdapter(adapter);

        getSupportActionBar().setTitle(getResources().getString(R.string.works_detail_title));
    }

    public void onFavoriteClicked(View view) {
        works = app.getDataManager().getWorks(worksNo);
        message = getResources().getString(R.string.add_to_favourite) + " " + works.getNameWorks() + " !";
        //Toast.makeText(getApplicationContext(), "Kliknalem ulubione", Toast.LENGTH_SHORT).show();

        if(favoriteCheckBox.isChecked()) {
            works.setFavouriteWorks(1);
            app.getDataManager().updateWorks(works);
            Intent intent = new Intent();
            intent.setAction("my_own_action");
            intent.putExtra(EXTRA_MESSAGE, message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        } else {
            works.setFavouriteWorks(0);
            app.getDataManager().updateWorks(works);
        }
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_beer, parent, false);
                tvName = (TextView) convertView.findViewById(R.id.tvNameBeer);
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