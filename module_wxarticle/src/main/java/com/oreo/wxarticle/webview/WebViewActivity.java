package com.oreo.wxarticle.webview;

import android.os.Bundle;

import com.oreo.wxarticle.R;
import com.oreooo.baselibrary.mvp.BaseActivity;

public class WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.webview_container_fragment, WebViewFragment.getInstance())
                .commit();
    }
}
