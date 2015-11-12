package com.cockandroid.webview;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

/**
 * Created by com on 2015-11-12.
 */
public class CookWebView extends WebViewClient{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {

        super.onPageFinished(view, url);
        View v = view.getRootView();
        EditText etUrl = (EditText) v.findViewById(R.id.editText1);
        etUrl.setText(url);
    }
}
