package com.oreooo.wanandroid.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oreooo.baselibrary.mvp.BaseFragment;
import com.oreooo.wanandroid.R;

public class WebViewFragment extends BaseFragment {

    public static WebViewFragment getInstance() {
        return WebViewFragmentHolder.Instance;
    }

    private static class WebViewFragmentHolder {
        private static WebViewFragment Instance = new WebViewFragment();
    }

    @Override
    public void init(View view, Bundle savedInstanceState) {
        WebView mWebView = view.findViewById(R.id.wanAndroid_webView);
        mWebView.loadUrl(String.valueOf(((Activity) getContext()).getIntent().getStringExtra("webUrl")));
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public int setContentView() {
        return R.layout.frag_webview;
    }
}
