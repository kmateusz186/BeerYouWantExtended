package com.example.przemek.beeryouwantv2;


import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.przemek.beeryouwantv2.model.Country;

import java.util.ArrayList;

public class WorksListCountryFragment extends ListFragment {

    MyApplication app;
    ArrayList<Country> countries;
    CountryAdapter adapter;
    public WorksListCountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        app = (MyApplication) getActivity().getApplication();
        countries = new ArrayList<>();
        countries.clear();
        countries.addAll(app.getDataManager().getCountries());
        adapter = new CountryAdapter(getActivity(), countries);
        setListAdapter(adapter);
        // Inflate the layout for this fragment
        //cursor = db.query("COUNTRY", new String[]{"_id", "NAME"}, null, null, null, null, "NAME ASC");
        //setListAdapter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

  /*  @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), WorksListProvinceActivity.class);
        intent.putExtra(WorksListProvinceActivity.EXTRA_COUNTRYNO, (int) id);
        startActivity(intent);
    } */

    private class CountryAdapter extends ArrayAdapter<Country> {

        private ArrayList<Country> countryList;

        public CountryAdapter(Context context, ArrayList<Country> countryList) {
            super(context, 0, countryList);
            this.countryList = new ArrayList<Country>();
            this.countryList.addAll(countryList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            final Country country = countryList.get(position);
            TextView tvName = null;
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_group, parent, false);
                tvName = (TextView) convertView.findViewById(R.id.tvName);
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getActivity(), "Kliknalem kraj :P", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), WorksListProvinceActivity.class);
                        intent.putExtra(WorksListProvinceActivity.EXTRA_COUNTRYNO, country.getIdCountry());
                        startActivity(intent);
                    }
                });
            }
            // Lookup view for data population

            // Populate the data into the template view using the data object
            tvName.setText(country.getNameCountry());
            // Return the completed view to render on screen
            return convertView;
        }
    }

}
