package com.example.przemek.beeryouwantv2;

import android.content.Context;
import android.content.Intent;
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

import com.example.przemek.beeryouwantv2.model.Works;

import java.util.ArrayList;

public class WorksListActivity extends AppCompatActivity {

    public final static String EXTRA_PROVINCENO = "province";

    private int provinceNo;
    private ListView listView;
    MyApplication app;
    ArrayList<Works> workses;
    WorksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_works_list);
        app = (MyApplication) getApplication();
        listView = (ListView) findViewById(R.id.works_list);
        provinceNo = getIntent().getExtras().getInt(EXTRA_PROVINCENO);
        workses = new ArrayList<>();
        workses.clear();
        workses.addAll(app.getDataManager().getProvince(provinceNo).getWorksList());
        Log.v("Provinces", " " + workses.get(0).getNameWorks());
        adapter = new WorksAdapter(this, workses);
        listView.setAdapter(adapter);
        getSupportActionBar().setTitle(getResources().getString(R.string.works_list_activity_title));
    }

    private class WorksAdapter extends ArrayAdapter<Works> {

        private ArrayList<Works> worksList;

        public WorksAdapter(Context context, ArrayList<Works> worksList) {
            super(context, 0, worksList);
            this.worksList = new ArrayList<Works>();
            this.worksList.addAll(worksList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final Works works = worksList.get(position);
            TextView tvName = null;
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_works, parent, false);
                tvName = (TextView) convertView.findViewById(R.id.tvNameWorks);
                tvName.setText(works.getNameWorks());
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(), "Kliknalem browar", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), WorksListDetailActivity.class);
                        intent.putExtra(WorksListDetailActivity.EXTRA_WORKSNO, works.getIdWorks());
                        startActivity(intent);
                    }
                });
            }
            // Lookup view for data population

            // Populate the data into the template view using the data object
            Log.v("Adapter", works.getNameWorks());
            //tvName.setText(province.getNameProvince());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
