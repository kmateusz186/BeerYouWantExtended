package com.example.przemek.beeryouwantv2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.przemek.beeryouwantv2.model.Style;

import java.util.ArrayList;

public class ChosenStylesActivity extends AppCompatActivity {

    public static final String EXTRA_COLOR = "color";
    public static final String EXTRA_BITTER = "bitter";
    public static final String EXTRA_MALT = "malt";
    public static final String EXTRA_ALCOHOL = "alcohol";
    public static final String EXTRA_WHEAT_MALT = "wheatmalt";
    public static final String EXTRA_FERMENTATION = "fermentation";

    private String color;
    private int bitter;
    private int malt;
    private int alcohol;
    private String wheat_malt;
    private String fermentation;
    private String[] colors;
    private String[] maltWheats;
    private String[] fermentations;

    private ListView listView;
    private ArrayList<Style> styles;
    MyApplication app;
    StyleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_chosen_styles);
        listView = (ListView) findViewById(R.id.chosen_styles_list);
        colors = getResources().getStringArray(R.array.parameters_values_color);
        maltWheats = getResources().getStringArray(R.array.parameters_values_malt_wheat);
        fermentations = getResources().getStringArray(R.array.parameters_values_fermentation);

        color = getIntent().getExtras().getString(EXTRA_COLOR);
        if (color.equals("light")) {
            color = colors[0];
        } else if (color.equals("dark")) {
            color = colors[1];
        }
        bitter = getIntent().getExtras().getInt(EXTRA_BITTER);
        malt = getIntent().getExtras().getInt(EXTRA_MALT);
        alcohol = getIntent().getExtras().getInt(EXTRA_ALCOHOL);
        wheat_malt = getIntent().getExtras().getString(EXTRA_WHEAT_MALT);
        if (wheat_malt.equals("yes")) {
            wheat_malt = maltWheats[0];
        } else if (wheat_malt.equals("not_nes")) {
            wheat_malt = maltWheats[1];
        }
        fermentation = getIntent().getExtras().getString(EXTRA_FERMENTATION);
        if (fermentation.equals("upper")) {
            fermentation = fermentations[0];
        } else if (fermentation.equals("lower")) {
            fermentation = fermentations[1];
        }
        app = (MyApplication) getApplication();
        styles = new ArrayList<>();
        styles.clear();
        styles.addAll(app.getDataManager().getChosenStyles(color,bitter,malt,alcohol,wheat_malt,fermentation));
        adapter = new StyleAdapter(this, styles);
        listView.setAdapter(adapter);

    }

    private class StyleAdapter extends ArrayAdapter<Style> {

        private ArrayList<Style> stylesList;

        public StyleAdapter(Context context, ArrayList<Style> stylesList) {
            super(context, 0, stylesList);
            this.stylesList = new ArrayList<Style>();
            this.stylesList.addAll(stylesList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final Style style = stylesList.get(position);
            TextView tvName = null;
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_style, parent, false);
                tvName = (TextView) convertView.findViewById(R.id.tvNameStyle);
                tvName.setText(style.getNameStyle());
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(), "Kliknalem styl", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), ChosenStylesDetailActivity.class);
                        intent.putExtra(ChosenStylesDetailActivity.EXTRA_STYLENO, style.getIdStyle());
                        startActivity(intent);
                    }
                });
            }
            // Lookup view for data population

            // Populate the data into the template view using the data object
            Log.v("Adapter", style.getNameStyle());
            //tvName.setText(province.getNameProvince());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
