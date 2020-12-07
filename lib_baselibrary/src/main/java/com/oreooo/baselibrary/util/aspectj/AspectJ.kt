package com.oreooo.baselibrary.util.aspectj

import android.util.Log
import android.view.View
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

@Aspect
 class AspectJ {
    
    companion object {
        val TAG = "TestAspect"
        const val FAST_CLICK_INTERVAL_GLOBAL:Long = 2000L
    }

    // 生命周期记录
    @Before("execution(* android.app.Activity.on**(..))")
    @Throws(Throwable::class)
    fun onActivityMethodBefore(joinPoint: JoinPoint) {
        val key = joinPoint.signature.toString()
        Log.i(TAG, "onActivityMethodBefore: $key");
    }

    // 点击限制器
    // 定义一个切入点：View.OnClickListener#onClick()方法
    @Pointcut("execution(void android.view.View.OnClickListener.onClick(..))")
    fun methodViewOnClick() {

    }

    // 定义环绕增强，包装methodViewOnClick()切入点
    @Throws(Throwable::class)
    @Around("methodViewOnClick()")
    fun aroundViewOnClick(joinPoint: ProceedingJoinPoint) {
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method

        var interval = FAST_CLICK_INTERVAL_GLOBAL

        // todo 这玩意有点难弄？
        if (method.isAnnotationPresent(FastClick::class.java)){
            val singleClick = method.getAnnotation(FastClick::class.java)
            interval = singleClick.interval
            Log.i(TAG, "间隔为—— $interval")
        }

        val target = joinPoint.args[0] as View
        if (!FastClickCheckUtil.isFastClick(target, interval)) {
            joinPoint.proceed()
        }
        Log.i(TAG, "点击了一个按钮: $target")
    }
}