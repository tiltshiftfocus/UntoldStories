package com.untoldstories.base;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.untoldstories.R;

public class BaseActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setUpToolbar();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout1);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_layout,new NewsFeedFragment()).commit();

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer1);
        drawerFragment.setUp(R.id.nav_drawer1,drawerLayout,toolbar);


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
