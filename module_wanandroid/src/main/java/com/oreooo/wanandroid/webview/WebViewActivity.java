package com.oreooo.wanandroid.webview;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.oreooo.baselibrary.mvp.BaseActivity;
import com.oreooo.baselibrary.route.RoutePath;
import com.oreooo.wanandroid.R;

@Route(path = RoutePath.WEBVIEW_ACTIVITY)
public class WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.webview_container_fragment, WebViewFragment.getInstance())
                .commit();
        findViewById(R.id.toolbar_webView_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
