package com.kyler.toolbarmenudrawer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyler.toolbarmenudrawer.R;

public class DemoFragment extends Fragment {

    public DemoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_for_this_example, container, false);
    }
}
