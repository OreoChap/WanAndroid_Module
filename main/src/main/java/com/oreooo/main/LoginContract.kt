package com.oreooo.main

import com.oreooo.baselibrary.MvpBase.BaseContract

interface LoginContract {
    interface Presenter : BaseContract.BasePresenter {
        fun login()
    }

    interface View : BaseContract.BaseView {
        fun loginResult()
    }
}