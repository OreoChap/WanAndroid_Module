package com.oreooo.baselibrary.aspectj

import android.view.View
import com.oreooo.baselibrary.R

class FastClickCheckUtil {

    companion object {
        fun isFastClick(view: View, interval: Long): Boolean {
            val key = R.id.aspectj_id

            val currentClickTime = System.currentTimeMillis()

            if (null == view.getTag(key)) {
                view.setTag(key, currentClickTime)
                return false
            }

            val lastClickTime = view.getTag(key) as Long
            return if (currentClickTime - lastClickTime < interval) {
                true
            } else {
                view.setTag(key, currentClickTime)
                false
            }
        }
    }
}