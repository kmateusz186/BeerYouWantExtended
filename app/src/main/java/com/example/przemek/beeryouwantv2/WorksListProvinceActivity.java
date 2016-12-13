package com.example.przemek.beeryouwantv2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.example.przemek.beeryouwantv2.model.Province;

import java.util.ArrayList;

public class WorksListProvinceActivity extends AppCompatActivity {

    public final static String EXTRA_COUNTRYNO = "province";
    MyApplication app;
    ArrayList<Province> provinces;
    ProvinceAdapter adapter;

    private ListView listView;
    private int countryNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_works_list_province);
        app = (MyApplication) getApplication();
        listView = (ListView) findViewById(R.id.works_province_list);
        countryNo = getIntent().getExtras().getInt(EXTRA_COUNTRYNO);
        provinces = new ArrayList<>();
        provinces.clear();
        provinces.addAll(app.getDataManager().getCountry(countryNo).getProvincesList());
        Log.v("Provinces", " " + provinces.get(0).getNameProvince());
        adapter = new ProvinceAdapter(this, provinces);
        listView.setAdapter(adapter);
        getSupportActionBar().setTitle(getResources().getString(R.string.works_list_province_activity_title));
    }


    private class ProvinceAdapter extends ArrayAdapter<Province> {

        private ArrayList<Province> provinceList;

        public ProvinceAdapter(Context context, ArrayList<Province> provinceList) {
            super(context, 0, provinceList);
            this.provinceList = new ArrayList<Province>();
            this.provinceList.addAll(provinceList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final Province province = provinceList.get(position);
            TextView tvName = null;
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_province, parent, false);
                tvName = (TextView) convertView.findViewById(R.id.tvNameProvince);
                tvName.setText(province.getNameProvince());
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(), "Kliknalem wojewodztwo", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), WorksListActivity.class);
                        intent.putExtra(WorksListActivity.EXTRA_PROVINCENO, province.getIdProvince());
                        startActivity(intent);
                    }
                });
            }
            // Lookup view for data population

            // Populate the data into the template view using the data object
            Log.v("Adapter", province.getNameProvince());
            //tvName.setText(province.getNameProvince());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
