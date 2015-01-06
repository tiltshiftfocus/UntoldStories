package com.untoldstories.signup;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.untoldstories.R;
import com.untoldstories.base.BaseActivity;

public class SetAnniversaryActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_anniversary);

        setUpToolbar();
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setAnniversary(View v){
        DialogFragment datePicker = new DatePickerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("buttonID",R.id.anniversary_date);

        datePicker.setArguments(bundle);

        datePicker.show(getFragmentManager(),"datepicker");

    }

    public void saveDate(View v){
        Intent i = new Intent(SetAnniversaryActivity.this, BaseActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        SetAnniversaryActivity.this.finish();
    }
}
