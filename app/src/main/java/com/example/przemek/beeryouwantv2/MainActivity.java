package com.example.przemek.beeryouwantv2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.przemek.beeryouwantv2.TimeOfAppRunningService.LocalBinder;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private int currentPosition;
    private String[] titles;
    private ActionBarDrawerToggle drawerToggle;
    private boolean preferencesChanged = true;
    int theme_number;

    public static final String THEMES = "pref_chosenTheme";



    private TimeOfAppRunningService timeService;
    boolean mBound = false;
    public TimeOfAppRunningService getTimeService() {
        return timeService;
    }
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder myBinder = (LocalBinder) service;
            timeService = myBinder.getService();
            mBound = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String themes =
                PreferenceManager.getDefaultSharedPreferences(this).getString(MainActivity.THEMES, null);
        if(themes!=null) {
            theme_number = Integer.parseInt(themes);
            Log.v("MainActivity", theme_number + "");
            Utils.setsTheme(theme_number);
            Utils.onActivityCreateSetTheme(this);
        }

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        // przypisuje domyślne wartości do SharedPreferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // rejestruje obiekt nasłuchujący zmian SharedPreferences
        PreferenceManager.getDefaultSharedPreferences(this).
                registerOnSharedPreferenceChangeListener(preferencesChangeListener);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.drawer_list);
        titles = getResources().getStringArray(R.array.titles);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, titles));
        listView.setOnItemClickListener(new DrawerItemClickListener());

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("currentPosition");
            setActionBarTitle(currentPosition);
        } else {
            selectItem(0);
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible fragment");
                        if (fragment instanceof TopFragment) {
                            currentPosition = 0;
                        }
                        if (fragment instanceof FindYourBeerFragment) {
                            currentPosition = 1;
                        }
                        if (fragment instanceof WorksListCountryFragment) {
                            currentPosition = 2;
                        }
                        if (fragment instanceof AlcoholicStrengthOfFragment) {
                            currentPosition = 3;
                        }

                        setActionBarTitle(currentPosition);
                        listView.setItemChecked(currentPosition, true);
                    }
                }
        );
    }

    private SharedPreferences.OnSharedPreferenceChangeListener preferencesChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            preferencesChanged = true; // użytkownik zmienił ustawienia aplikacji
            Log.v("MainActivity", "Zmieniles ustawienia");
            if (s.equals(THEMES)) {
                String themes =
                        sharedPreferences.getString(MainActivity.THEMES, null);
                theme_number = Integer.parseInt(themes);
                Utils.changeToTheme(MainActivity.this, theme_number);
            }
        }
    };

    @Override
    protected void onStart(){
        super.onStart();
        if (preferencesChanged) {
            Log.v("MainActivity", "Zmieniles ustawienia");
            String themes =
                    PreferenceManager.getDefaultSharedPreferences(this).getString(MainActivity.THEMES, null);
            theme_number = Integer.parseInt(themes);
            Log.v("MainActivity", theme_number + "");
            Utils.setsTheme(theme_number);
            //Utils.changeToTheme(this, theme_number);
            preferencesChanged = false;
        }
        bindService(new Intent(getApplicationContext(), TimeOfAppRunningService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mBound){
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void OnButtonClicked(View view) {
        Fragment fragment = new FindYourBeerFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "visible fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectItem(i);
        }
    }

    private void setActionBarTitle(int i) {
        String title;
        if (i == 0) {
            title = getResources().getString(R.string.app_name);
        } else {
            title = titles[i];
        }
        getSupportActionBar().setTitle(title);
    }

    private void selectItem(int i) {
        currentPosition = i;
        Fragment fragment;
        switch (currentPosition) {
            case 1:
                fragment = new FindYourBeerFragment();
                break;
            case 2:
                fragment = new WorksListCountryFragment();
                break;
            case 3:
                fragment = new AlcoholicStrengthOfFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "visible fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        setActionBarTitle(i);

        drawerLayout.closeDrawer(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent preferencesIntent = new Intent(this, SettingsActivity.class);
                startActivity(preferencesIntent);
                return true;
            case R.id.get_time:
                timeService.getTime();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentPosition", currentPosition);
    }
}
