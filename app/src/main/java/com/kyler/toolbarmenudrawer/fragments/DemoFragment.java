package com.kyler.toolbarmenudrawer.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;

import com.kyler.toolbarmenudrawer.R;

public class DemoFragment extends Fragment {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_for_this_example, container,
                false);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {

            ImageButton fab = (ImageButton) rootView.findViewById(R.id.fab);
            //fab.setOutline(outline);
            ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {

                @Override
                public void getOutline(View view, Outline outline) {
                    // Or read size directly from the view's width/height
                    int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                    outline.setOval(0, 0, size, size);
                }
            };
            fab.setOutlineProvider(viewOutlineProvider);
        }

        return rootView;
    }
}
