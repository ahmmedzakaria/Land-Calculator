package com.example.ahmme.landcalculator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.example.ahmme.landcalculator.quadrangle.LandInfo;
import com.example.ahmme.landcalculator.quadrangle.QuadrangleFragmet;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment currentFragment;
    FragmentManager manager;
    FragmentTransaction transaction;

    NavigationView navigationView=null;
    Toolbar toolbar=null;
    LandInfo landInfo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        landInfo=new LandInfo();
        landInfo.setContext(this);

        currentFragment=new QuadrangleFragmet();
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.fragment_container, currentFragment);
        transaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.quadrangle) {
            currentFragment=new QuadrangleFragmet();
        } else if (id == R.id.triangle) {
            currentFragment=new TriangleFragment();

        } else if (id == R.id.history) {
            currentFragment=new HistoryFragment();

        } else if (id == R.id.about) {
            currentFragment=new AboutFragment();

        } else if (id == R.id.setting) {
            currentFragment=new SettingFragment();
        }

        transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_container,currentFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void softKey(){
        InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(MainActivity.this.getWindow().getCurrentFocus().getWindowToken(), 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void basicInitialization() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);

        landInfo.setContext(this);
        landInfo.sharedPreferences=getSharedPreferences(landInfo.LAND_CALCULATOR,Context.MODE_PRIVATE);
        landInfo.editor=landInfo.sharedPreferences.edit();
        landInfo.editor.commit();
    }
}
