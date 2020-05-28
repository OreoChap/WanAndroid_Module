package com.oreooo.wanandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.oreooo.baselibrary.IContext
import com.oreooo.main.MainActivity
import org.litepal.LitePal

class App : Application() {

    private val isDebugARouter = true

    override fun onCreate() {
        super.onCreate()
        if (isDebugARouter) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        instance = this
        MainActivity.mContext = instance
        ARouter.init(this)
        LitePal.initialize(this)
    }

    companion object {
        @Volatile
        private lateinit var instance:App

        fun getApplication():Application {
            return instance
        }
    }
}