package com.kyler.toolbarmenudrawer;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kyler.toolbarmenudrawer.adapter.ToolbarMenudrawerAdapter;
import com.kyler.toolbarmenudrawer.fragments.WeatherFragment;
import com.kyler.toolbarmenudrawer.ui.Icons;
import com.wisemandesigns.android.widgets.CircularImageView;

import java.util.ArrayList;

public class ToolbarMenudrawer extends ActionBarActivity {

    final Context context = this;
    public CircularImageView iv;
    Fragment weather = new WeatherFragment();
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    @SuppressWarnings("unused")
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ArrayList<Icons> icons;
    private ToolbarMenudrawerAdapter adapter;
    private String[] MDTitles;
    private TypedArray MDIcons;
    private Toolbar mToolbar;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.toolbarmenudrawer);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(R.string.blank_text);

        mTitle = mDrawerTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        MDTitles = getResources().getStringArray(
                R.array.navigation_main_sections);

        MDIcons = getResources().obtainTypedArray(R.array.drawable_ids);

        icons = new ArrayList<Icons>();

        icons.add(new Icons(MDTitles[0], MDIcons.getResourceId(0, -1)));

        icons.add(new Icons(MDTitles[1], MDIcons.getResourceId(1, -2)));

        icons.add(new Icons(MDTitles[2], MDIcons.getResourceId(2, -3)));

        icons.add(new Icons(MDTitles[3], MDIcons.getResourceId(3, -4)));

        MDIcons.recycle();

        adapter = new ToolbarMenudrawerAdapter(getApplicationContext(), icons);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header,
                mDrawerList, false);


        // Give your Actionbar a subtitle!
        /* mToolbar.setSubtitle("Subtitle"); */

        mDrawerList.addHeaderView(header, null, false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );

        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {

            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.toolbarmenudrawer_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        switch (item.getItemId()) {

            default:

        }
        ;

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (position) {

            case 0:
                ft.replace(R.id.content_frame, weather);
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

        }

        ft.commit();

        mDrawerList.setItemChecked(position, true);

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public static class CategoriesFragment extends Fragment {

        public static final String ARG_CATEGORY = "category";

        public CategoriesFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_categories,
                    container, false);

            int i = getArguments().getInt(ARG_CATEGORY);

            String List = getResources().getStringArray(
                    R.array.navigation_main_sections)[i];

            getActivity().setTitle(List);

            return rootView;

        }
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
    }
}
