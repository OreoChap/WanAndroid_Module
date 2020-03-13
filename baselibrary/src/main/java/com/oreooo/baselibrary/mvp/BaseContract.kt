package com.oreooo.baselibrary.mvp

interface BaseContract {
    interface BaseView {
        fun subscribe()
        fun unsubscribe()
    }

    interface BasePresenter {
        fun setView(view: BaseView?)
        fun clearRequest()
    }
}