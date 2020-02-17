package com.oreooo.main

import android.util.Log
import com.oreooo.baselibrary.MvpBase.BaseContract

class LoginPresenter : LoginContract.Presenter {

    private lateinit var mView: LoginContract.View


    companion object {
        val instance: LoginPresenter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LoginPresenter()
        }
    }

    override fun login() {

    }

    override fun setView(view: BaseContract.BaseView?) {
        mView = view as? LoginContract.View ?: return
    }


}