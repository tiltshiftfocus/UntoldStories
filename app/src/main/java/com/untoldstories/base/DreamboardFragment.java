package com.untoldstories.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.untoldstories.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DreamboardFragment extends Fragment {


    public DreamboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dreamboard, container, false);
    }


}
