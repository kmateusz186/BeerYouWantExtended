package com.example.przemek.beeryouwantv2;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SettingsActivityFragment extends PreferenceFragment {
    // tworzy interfejs opcji na podstawie pliku preferences.xml znajdującego się w katalogu res/xml
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferences); // ładuje kod XML
    }
}
