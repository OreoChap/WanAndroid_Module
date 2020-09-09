package com.oreooo.baselibrary.aspectj

import android.util.Log
import android.view.View
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

@Aspect
class AspectJ {
    companion object {
        val TAG = "TestAspect"
    }

    // 点击限制器
    @Before("execution(* android.app.Activity.on**(..))")
    @Throws(Throwable::class)
    fun onActivityMethodBefore(joinPoint: JoinPoint) {
        val key = joinPoint.signature.toString()
        Log.d(TAG, "onActivityMethodBefore: $key");
    }

    @Pointcut("execution(void android.view.View.OnClickListener.onClick(..))")
    fun methodViewOnClick() {

    }

    @Throws(Throwable::class)
    @Around("methodViewOnClick()")
    fun aroundViewOnClick(joinPoint: ProceedingJoinPoint) {
        val target = joinPoint.args[0] as View
        if (!FastClickCheckUtil.isFastClick(target, 2000)) {
            joinPoint.proceed()
        }
    }
}