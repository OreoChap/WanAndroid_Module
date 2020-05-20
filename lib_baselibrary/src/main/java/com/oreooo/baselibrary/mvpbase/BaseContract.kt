package com.oreooo.baselibrary.mvpbase

/**
 *  BaseContract
 * @author oreo
 */
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