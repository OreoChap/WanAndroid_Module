package com.oreooo.wanandroid.base;

import io.reactivex.disposables.Disposable;

/**
 *  Presenter 基类接口
 * @author oreo
 * @date 2020/05/09
 */
public interface AbstractPresenter<T extends AbstractView> {

    void attachView(T view);

    void detachView();

    void addSubscribe(Disposable disposable);
}
