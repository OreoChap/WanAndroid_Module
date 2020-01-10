package com.oreooo.main

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableFloat

class LoginModel : ViewModel() {

    //手机号码
    val name = ObservableField<String>()
    //验证码
    val pwd = ObservableField<String>()

    //登录按钮可点击状态
    val loginEnable = object : ObservableBoolean(name,pwd) {
        override fun get(): Boolean {
            val nameStr = name.get()
            val pwdStr = pwd.get()
            //手机框和密码框都存在输入值时，才允许点击登录按钮
            return if (nameStr.isNullOrEmpty() || pwdStr.isNullOrEmpty())
                false
            else
                true
        }
    }
    //登录按钮的透明度
    val loginAlpha = object: ObservableFloat(loginEnable){
        override fun get(): Float {
            //获取按钮是否可点击的布尔值
            val enable = loginEnable.get()
            return if (enable)
                1.0f
            else
                0.5f
        }
    }
}
