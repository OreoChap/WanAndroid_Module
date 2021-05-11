package com.oreooo.wanandroid_test

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
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
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    companion object {
        @Volatile
        private lateinit var instance: App

        @Volatile
        private var activityAmount: Int = 0

        @Volatile
        private var isAppForeground: Boolean = false
    }

    /**
     *  App 前后台记录
     */
    private val activityLifecycleCallbacks: ActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStarted(activity: Activity) {
            if (activityAmount == 0) {
                isAppForeground = true
            }
            activityAmount++
        }

        override fun onActivityDestroyed(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityStopped(activity: Activity) {
            activityAmount--
            if (activityAmount == 0) {
                isAppForeground = false
            }
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityResumed(activity: Activity) {
        }
    }
}