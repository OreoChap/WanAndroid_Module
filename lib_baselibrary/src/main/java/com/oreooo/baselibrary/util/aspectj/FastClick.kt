package com.oreooo.baselibrary.util.aspectj

import com.oreooo.baselibrary.util.aspectj.AspectJ.Companion.FAST_CLICK_INTERVAL_GLOBAL

/**
 * 在需要定制时间间隔地方添加@FastClick注解
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class FastClick(val interval: Long = FAST_CLICK_INTERVAL_GLOBAL)