package com.oreooo.baselibrary.newmvp;

import android.os.Bundle;

import com.oreooo.baselibrary.mvpbase.StartActivity;

import org.jetbrains.annotations.Nullable;

public abstract class BaseAct<T extends AbstractPresenter> extends StartActivity implements AbstractView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = setPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    public abstract T setPresenter();
}