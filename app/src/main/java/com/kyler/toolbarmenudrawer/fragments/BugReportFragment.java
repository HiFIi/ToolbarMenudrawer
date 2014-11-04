package com.kyler.toolbarmenudrawer.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kyler.toolbarmenudrawer.R;

public class BugReportFragment extends Fragment {
    Context context;
    WebView wv;

    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_view, container, false);

        WebView wv = (WebView) view.findViewById(R.id.wv);

        wv.loadUrl("https://github.com/I-am-Reinvented/ToolbarMenudrawer/issues/new");

        wv.getSettings().setJavaScriptEnabled(true);

        wv.clearCache(true);

        WebSettings webSettings = wv.getSettings();

        wv.getSettings().setPluginState(PluginState.ON);

        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);

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