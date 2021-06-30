package com.oreooo.main.login

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableFloat
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.oreooo.baselibrary.route.RoutePath
import com.oreooo.main.services.LoginServiceApi

@Route(path = RoutePath.API_LOGIN)
class LoginModel : ViewModel(),LoginServiceApi {

    val name = ObservableField<String>()
    val pwd = ObservableField<String>()

    //登录按钮可点击状态
    val loginEnable = object : ObservableBoolean(name, pwd) {
        override fun get(): Boolean {
            val nameStr = name.get()
            val pwdStr = pwd.get()
            return !(nameStr.isNullOrEmpty() || pwdStr.isNullOrEmpty())
        }
    }

    //登录按钮的透明度
    val loginAlpha = object : ObservableFloat(loginEnable) {
        override fun get(): Float {
            val enable = loginEnable.get()
            return if (enable)
                1.0f
            else
                0.5f
        }
    }

    // 登录按钮的点击事件
    fun loginClickListener(view: View) {
        LoginPresenter.instance.login(name.get()!!, pwd.get()!!)
        Log.e("xyz", "点击了login按钮")
    }

    // 跳过按钮的点击事件
    fun skipLogin(view: View) {
        LoginPresenter.instance.skipLogin()
    }

    override fun getUserId(): Int {
        return 99
    }

    override fun init(context: Context?) {

    }
}
