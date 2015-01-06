package com.untoldstories.signup;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by zm on 9/12/2014.
 */
public class GenderSpinner implements AdapterView.OnItemSelectedListener {

    private Context context;

    public GenderSpinner(Context context){
        this.context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0){
            Toast.makeText(context,"Male",Toast.LENGTH_SHORT).show();
        }
        if(position==1){
            Toast.makeText(context,"Female",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
