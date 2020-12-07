package com.oreooo.baselibrary.util.aspectj

import android.view.View
import com.oreooo.baselibrary.R

/**
 *  配合AspectJ使用的点击限制器
 */
class FastClickCheckUtil {

    companion object {
        /**
         * 判断是否属于快速点击
         *
         * @param view     点击的View
         * @param interval 快速点击的阈值
         * @return true：快速点击
         */
        fun isFastClick(view: View, interval: Long): Boolean {
            val key = R.id.aspectj_id

            // 最近的点击时间
            val currentClickTime = System.currentTimeMillis()

            if (null == view.getTag(key)) {
                // 第一次点击，保存时间
                view.setTag(key, currentClickTime)
                return false
            }

            val lastClickTime = view.getTag(key) as Long

            // 根据 interval 比较两次点击时间的间隔
            return if (currentClickTime - lastClickTime < interval) {
                true
            } else {
                view.setTag(key, currentClickTime)
                false
            }
        }
    }
}