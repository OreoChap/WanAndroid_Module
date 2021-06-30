@file:Suppress("SENSELESS_COMPARISON")
package com.oreooo.baselibrary.util

import android.util.Log

@Suppress("MemberVisibilityCanBePrivate")
object LogUtil {

    var defaultTag = "日志Tag"
    fun d(msg: String) {
        Log.d(defaultTag, msg)
    }

    fun i(msg: String) {
        Log.i(defaultTag, msg)
    }

    fun e(msg: String) {
        Log.e(defaultTag, msg)
    }

}