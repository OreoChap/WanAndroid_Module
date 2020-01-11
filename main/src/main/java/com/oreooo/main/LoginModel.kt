package com.oreooo.main

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableFloat
import android.view.View

class LoginModel : ViewModel() {

    val name = ObservableField<String>()
    val pwd = ObservableField<String>()

    //登录按钮可点击状态
    val loginEnable = object : ObservableBoolean(name,pwd) {
        override fun get(): Boolean {
            val nameStr = name.get()
            val pwdStr = pwd.get()
            return if (nameStr.isNullOrEmpty() || pwdStr.isNullOrEmpty())
                false
            else
                true
        }
    }

    //登录按钮的透明度
    val loginAlpha = object: ObservableFloat(loginEnable){
        override fun get(): Float {
            val enable = loginEnable.get()
            return if (enable)
                1.0f
            else
                0.5f
        }
    }

    //登录按钮的点击事件
    fun loginClickListener(view:View){

    }
}
