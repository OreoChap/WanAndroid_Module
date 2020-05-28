package com.oreooo.baselibrary.newmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends AbstractPresenter> extends com.oreooo.baselibrary.mvpbase.StartFragment implements AbstractView {

    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = setPresenter();
        return inflater.inflate(setContentView(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(BaseFragment.this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }

    /**
     * 设置presenter
     */
    public abstract T setPresenter();
}
