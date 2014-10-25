package com.kyler.toolbarmenudrawer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.kyler.toolbarmenudrawer.activities.About;
import com.kyler.toolbarmenudrawer.activities.FirstRun;
import com.kyler.toolbarmenudrawer.adapter.ToolbarMenudrawerAdapter;
import com.kyler.toolbarmenudrawer.fragments.BugReportFragment;
import com.kyler.toolbarmenudrawer.fragments.DemoFragment;
import com.kyler.toolbarmenudrawer.fragments.RequestFragment;
import com.kyler.toolbarmenudrawer.ui.Icons;

import java.util.ArrayList;

import static com.kyler.toolbarmenudrawer.R.color.toolbarcolor;

public class ToolbarMenudrawer extends ActionBarActivity {

    final Context context = this;
    public ImageView iv;
    public RippleDrawable rb;
    Fragment demo = new DemoFragment();
    Fragment request = new RequestFragment();
    Fragment report = new BugReportFragment();
    Fragment WebViewDemo = new WebViewFragment();
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
    public Toolbar mToolbar;
    WebView wv;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences first = PreferenceManager
                .getDefaultSharedPreferences(this);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            super.finish();
        }

        if (!first.getBoolean("firstTime", false)) {

            Intent intent = new Intent(this, FirstRun.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

            SharedPreferences.Editor editor = first.edit();

            editor.putBoolean("firstTime", true);
            editor.commit();

        }

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

        MDIcons.recycle();

        adapter = new ToolbarMenudrawerAdapter(getApplicationContext(), icons);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header,
                mDrawerList, false);

        final ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer,
                mDrawerList, false);

        // Give your Toolbar a subtitle!
        /* mToolbar.setSubtitle("Subtitle"); */

        mDrawerList.addHeaderView(header, null, true); // true = clickable
        mDrawerList.addFooterView(footer, null, true); // true = clickable

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {

                invalidateOptionsMenu();

            }

            public void onDrawerOpened(View drawerView) {

                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                /* TODO:
                Add stuff. :p */

            }
        };

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
            case R.id.option1:
                Toast.makeText(this, "Option 1", Toast.LENGTH_SHORT)
                        .show();
                break;

            case R.id.option2:
                Toast.makeText(this, "Option 2", Toast.LENGTH_SHORT)
                        .show();
                break;

            case R.id.option3:
                Toast.makeText(this, "Option 3", Toast.LENGTH_SHORT)
                        .show();
                break;

            default:

        }
        ;

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void selectItem(int position) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FrameLayout DFL = (FrameLayout) findViewById(R.id.dynamicFrameLayoutBG);

        switch (position) {

            case 0:
                getSupportActionBar().setTitle("");
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {

                    // TODO:
                    // Different themes

                    getWindow().setNavigationBarColor(getResources().getColor(R.color.navbarcolor));
                    mToolbar.setBackgroundColor(getResources().getColor(toolbarcolor));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.statusbarcolor_darker));
                } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                    getSupportActionBar().setTitle("");
                    DFL.setBackgroundColor(getResources().getColor(R.color.statusbarcolor_darker));
                }

                ft.replace(R.id.content_frame, demo);
                break;

            case 1:
                getSupportActionBar().setTitle("Request");
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {

                    // TODO:
                    // Different themes

                    getWindow().setNavigationBarColor(getResources().getColor(R.color.navbarcolor_request));
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.toolbarcolor_request));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.statusbarcolor_request_darker));
                } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                    getSupportActionBar().setTitle("");
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.toolbarcolor_request));
                    DFL.setBackgroundColor(getResources().getColor(R.color.statusbarcolor_request_darker));
                }
                ft.replace(R.id.content_frame, request);
                break;

            case 2:
                mDrawerLayout.closeDrawer(mDrawerList);
                ft.replace(R.id.content_frame, report);
                getSupportActionBar().setTitle("Report");
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setNavigationBarColor(getResources().getColor(R.color.navbarcolor_report));
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.toolbarcolor_report));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.statusbarcolor_report_darker));
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    getSupportActionBar().setTitle("");
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.toolbarcolor_report));
                    DFL.setBackgroundColor(getResources().getColor(R.color.statusbarcolor_report_darker));
                }
                ft.replace(R.id.content_frame, report);
                break;

            case 3:
                getSupportActionBar().setTitle("WebView!");
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {

                    // TODO:
                    // Different themes

                    getWindow().setNavigationBarColor(getResources().getColor(R.color.navbarcolor));
                    mToolbar.setBackgroundColor(getResources().getColor(toolbarcolor));
                    getWindow().setStatusBarColor(getResources().getColor(R.color.statusbarcolor_darker));
                } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                    getSupportActionBar().setTitle("");
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.toolbarcolor));
                    DFL.setBackgroundColor(getResources().getColor(R.color.statusbarcolor_darker));
                }
                ft.replace(R.id.content_frame, WebViewDemo);
                break;

            case 4:
                // Not quite ready yet

               /* Intent about = new Intent(this, About.class);
                startActivity(about); */

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

   @SuppressLint("ValidFragment")
   public class WebViewFragment extends Fragment {
        Context context;
        WebView wv;
        String google = "https://google.com";

        @SuppressWarnings("deprecation")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.web_view, container, false);
            WebView wv = (WebView) view.findViewById(R.id.wv);

        /* Use this to make the webview link convert from Mobile to Desktop. ;)

        wv.getSettings().setUserAgentString("Mozilla/5.0 " +
                "(Windows NT 6.2; " +
                "WOW64) AppleWebKit/537.31 " +
                "(KHTML, like Gecko) Chrome/20 " +
                "Safari/537.31"); */

            wv.loadUrl(google);

            wv.clearCache(true);

            WebSettings webSettings = wv.getSettings();

            wv.getSettings().setPluginState(WebSettings.PluginState.ON);

            webSettings.setJavaScriptEnabled(true);

            webSettings.setDomStorageEnabled(true);

            wv.setDownloadListener(new DownloadListener() {

                @Override
                public void onDownloadStart(String url, String userAgent,
                                            String contentDisposition, String mimetype,
                                            long contentLength) {

                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            });

            wv.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                }
            });


            wv.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    {
                        view.loadUrl(url);
                        return true;
                    }
                }
            });
            return wv;
        }
    }
}