package com.oreo.wxarticle

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class Module_WxArticle:Application() {

    private val isDebugARouter = true

    override fun onCreate() {
        super.onCreate()
        if (isDebugARouter) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }
}