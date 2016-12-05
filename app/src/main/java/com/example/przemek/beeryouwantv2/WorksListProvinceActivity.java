package com.example.przemek.beeryouwantv2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class WorksListProvinceActivity extends AppCompatActivity {

    public final static String EXTRA_COUNTRYNO = "province";
    private SQLiteDatabase db;
    private Cursor cursor;

    private ListView listView;
    private int countryNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works_list_province);
        //cursor = db.query("PROVINCE", new String[]{"_id", "NAME"}, "COUNTRY = ?", new String[]{Integer.toString(countryNo)}, null, null, "NAME ASC");
        listView = (ListView) findViewById(R.id.works_province_list);
        countryNo = getIntent().getExtras().getInt(EXTRA_COUNTRYNO);
        /*listView.setAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
}
