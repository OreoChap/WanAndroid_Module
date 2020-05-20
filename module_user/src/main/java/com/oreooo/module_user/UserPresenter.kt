package com.oreooo.module_user

import com.oreooo.baselibrary.mvpbase.BaseContract
import com.oreooo.baselibrary.newmvp.BasePresenter

class UserPresenter:BasePresenter<UserContract.View>(), UserContract.Presenter{

    companion object {
        @Volatile
        private var instance:UserPresenter? = null

        fun  getInstance():UserPresenter = synchronized(this) {
            instance?: UserPresenter().also { instance = it }
        }
    }

    override fun getCollectArticle(page: Int) {

    }
}