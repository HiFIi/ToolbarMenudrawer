package com.kyler.toolbarmenudrawer.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;

import com.kyler.toolbarmenudrawer.R;

public class BugReportFragment extends Fragment {

    private WebView wv;

    public BugReportFragment(){
    }

    public static BugReportFragment newInstance() {
        return new BugReportFragment();
    }

    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_view, container, false);

        wv = (WebView) view.findViewById(R.id.wv);

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
                view.loadUrl(url);
                return true;
            }
        });

        return view;
    }

    public boolean canGoBack() {
        return this.wv != null &&  this.wv.canGoBack();
    }

    public void goBack() {
        if(this.wv != null) {
            this.wv.goBack();
        }
    }
}