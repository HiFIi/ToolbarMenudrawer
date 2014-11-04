package com.kyler.toolbarmenudrawer.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kyler.toolbarmenudrawer.R;

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
