package com.oreooo.main.login

import com.oreooo.baselibrary.mvpbase.BaseContract
import com.oreooo.main.pojo.User

interface LoginContract {
    interface Presenter : BaseContract.BasePresenter {
        fun login(userName:String, password:String)
        fun skipLogin()
    }

    interface View : BaseContract.BaseView {
        fun loginResult(user:User?)
    }
}