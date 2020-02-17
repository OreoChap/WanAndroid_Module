package com.oreooo.main;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import org.litepal.LitePal;

public class HomeApplication extends Application {

    // ARouter 调试开关
    private boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugARouter) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(HomeApplication.this);
        LitePal.initialize(this);
    }
}
