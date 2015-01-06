package com.untoldstories.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.untoldstories.R;

import java.util.List;

/**
 * Created by zm on 18/12/2014.
 */
public class DrawerListAdapter extends ArrayAdapter<String> {

    private Context context;

    public DrawerListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
