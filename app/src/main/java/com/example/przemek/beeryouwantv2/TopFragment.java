package com.example.przemek.beeryouwantv2;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.przemek.beeryouwantv2.model.Works;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    private ListView listView;
    private ArrayList<Works> workses;
    MyApplication app;
    WorksAdapter adapter;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        app = (MyApplication) getActivity().getApplication();
        listView = (ListView) view.findViewById(R.id.favorites_works);
        workses = new ArrayList<Works>();
        workses.clear();
        workses.addAll(app.getDataManager().getFavouriteWorks());
        adapter = new WorksAdapter(getActivity(), workses);
        listView.setAdapter(adapter);
        return view;
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_favourite_works, parent, false);
                tvName = (TextView) convertView.findViewById(R.id.tvNameWorksFav);
                tvName.setText(works.getNameWorks());
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getActivity(), "" + works.getIdWorks(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), WorksListDetailActivity.class);
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