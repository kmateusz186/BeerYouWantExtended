package com.example.przemek.beeryouwantv2;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorksListCountryFragment extends ListFragment {


    public WorksListCountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //cursor = db.query("COUNTRY", new String[]{"_id", "NAME"}, null, null, null, null, "NAME ASC");
        //setListAdapter(new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1,
        //        cursor, new String[]{"NAME"}, new int[]{android.R.id.text1}, 0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

  /*  @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), WorksListProvinceActivity.class);
        intent.putExtra(WorksListProvinceActivity.EXTRA_COUNTRYNO, (int) id);
        startActivity(intent);
    } */

}
