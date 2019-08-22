package com.oreooo.wanandroid.wanAndroid;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath;
import com.oreooo.library.MvpBase.BaseActivity;
import com.oreooo.wanandroid.R;

@Route(path = WanAndroidRoutePath.WANANDROID_ACTIVITY)
public class WanAndroidActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.main_container_fragment, WanAndroidFragment.getInstance())
                .commit();
    }
}
