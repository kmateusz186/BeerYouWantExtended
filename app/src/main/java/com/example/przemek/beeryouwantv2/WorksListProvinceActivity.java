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
        setContentView(R.layout.activity_works_list_province);
        app = (MyApplication) getApplication();
        listView = (ListView) findViewById(R.id.works_province_list);
        countryNo = getIntent().getExtras().getInt(EXTRA_COUNTRYNO);
        provinces = new ArrayList<>();
        provinces.clear();
        provinces.addAll(app.getDataManager().getCountry(countryNo).getProvincesList());
        Log.v("Provinces", " " + provinces.get(0).getNameProvince());
        adapter = new ProvinceAdapter(getApplicationContext(), provinces);
        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), WorksListActivity.class);
                intent.putExtra(WorksListActivity.EXTRA_PROVINCENO, (int) l);
                startActivity(intent);
            }
        }); */
        getSupportActionBar().setTitle(getResources().getString(R.string.works_list_province_activity_title));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Kliknalem kraj :P", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(), WorksListProvinceActivity.class);
                        //intent.putExtra(WorksListActivity.EXTRA_PROVINCENO, province.getIdCountry());
                        //startActivity(intent);
                    }
                });
            }
            // Lookup view for data population

            // Populate the data into the template view using the data object
            //tvName.setText(province.getNameProvince());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
