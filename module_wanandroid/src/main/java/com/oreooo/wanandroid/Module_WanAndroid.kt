package com.oreooo.wanandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.oreooo.wanandroid.wanandroid.WanAndroidAct

class Module_WanAndroid : Application() {

    private val isDebugARouter = true

    override fun onCreate() {
        super.onCreate()
        if (isDebugARouter) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        WanAndroidAct.mContext = this
    }
}