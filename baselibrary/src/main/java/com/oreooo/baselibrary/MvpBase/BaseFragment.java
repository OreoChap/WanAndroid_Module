package com.oreooo.baselibrary.MvpBase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    private Context context;
    private View mView;

    /**
     *   如果重写了onCreateView()，子类需要调用super.onCreateView()，才能使init()正常运作
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(setContentView(), container, false);
        init(mView, savedInstanceState);
        return mView;
    }

    public abstract void init(View view, Bundle savedInstanceState);

    public abstract int setContentView();

    protected Context getMyContext() {
        return context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
}
