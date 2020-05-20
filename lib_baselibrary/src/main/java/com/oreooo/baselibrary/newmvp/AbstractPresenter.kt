package com.oreooo.baselibrary.newmvp

import io.reactivex.disposables.Disposable

/**
 *  Presenter 基类接口
 * @author oreo
 * @date 2020/05/20
 */
interface AbstractPresenter<T: AbstractView> {
    fun attachView(view: T)

    fun detachView()

    fun addSubscribe(disposable: Disposable?)
}