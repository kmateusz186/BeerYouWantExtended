package com.example.przemek.beeryouwantv2;


import android.app.Fragment;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    private ListView listView;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        SQLiteOpenHelper openHelper = new OpenHelper(getActivity());
        openHelper.getReadableDatabase();
        //cursor = db.query("WORKS", new String[]{"_id", "NAME"}, "FAVORITE = 1", null, null, null, null);
        /*listView = (ListView) view.findViewById(R.id.favorites_works);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WorksListDetailActivity.class);
                intent.putExtra(WorksListDetailActivity.EXTRA_WORKSNO, (int) l);
                startActivity(intent);
            }
        }); */
        return view;
    }
}