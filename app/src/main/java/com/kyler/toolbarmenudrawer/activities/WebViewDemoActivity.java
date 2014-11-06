package com.kyler.toolbarmenudrawer.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kyler.toolbarmenudrawer.R;

/**
 * Created by kyler on 11/2/14.
 */
public class WebViewDemoActivity extends Activity {
    Context context;
    WebView wv;
    String google = "https://google.com";

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.web_view);

        wv = (WebView) findViewById(R.id.wv);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        getActionBar().setTitle("Webview demo");

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;

            default:

        };

        return super.onOptionsItemSelected(item);
    }
}
