package com.oreooo.wanandroid.wanAndroid.webView;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.oreooo.baselibrary.MvpBase.BaseActivity;
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath;
import com.oreooo.wanandroid.R;

@Route(path = WanAndroidRoutePath.WEBVIEW_ACTIVITY)
public class WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ARouter.getInstance().inject(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.webview_container_fragment, WebViewFragment.getInstance())
                .commit();
    }
}
