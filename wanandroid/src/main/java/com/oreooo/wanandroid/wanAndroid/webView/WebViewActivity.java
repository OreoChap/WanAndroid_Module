package com.oreooo.wanandroid.wanAndroid.webView;

import android.os.Bundle;

import com.oreooo.baselibrary.MvpBase.BaseActivity;
import com.oreooo.wanandroid.R;

//@Route(path = WanAndroidRoutePath.WEBVIEW_ACTIVITY)
public class WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
//        ARouter.getInstance().inject(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.webview_container_fragment, WebViewFragment.getInstance())
                .commit();
    }
}
