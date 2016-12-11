package com.example.przemek.beeryouwantv2;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.przemek.beeryouwantv2.model.Works;

import java.util.ArrayList;

/**
 * Created by Przemek on 11.12.2016.
 */

public class AlcoholicStrengthOfFragment extends Fragment {

    public AlcoholicStrengthOfFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        return view;
    }
}
