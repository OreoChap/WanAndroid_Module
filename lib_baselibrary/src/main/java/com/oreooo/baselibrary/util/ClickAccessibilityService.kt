package com.oreooo.baselibrary.util

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

/**
 *  可用于打印点击事件等，AspectJ 也可实现相同功能
 */
class ClickAccessibilityService : AccessibilityService() {
    override fun onInterrupt() {

    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val eventType = event?.eventType
        val className = event?.className

        when (eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> Log.i(TAG, "点击了一个按钮=$className")
        }

    }

    companion object {
        val TAG = "AccessibilityService"
    }
}