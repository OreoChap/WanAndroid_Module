package com.oreooo.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.oreooo.baselibrary.ButtonClickListener;
import com.oreooo.baselibrary.MvpBase.BaseActivity;
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath;

public class LoadingAct extends BaseActivity {

    Button btn_skipLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_loading);
        ARouter.getInstance().inject(this);
        initView();
    }

    private void initView() {
        btn_skipLogin = findViewById(R.id.btn_skipLogin);
        btn_skipLogin.setOnClickListener(new ButtonClickListener() {
            @Override
            protected void onSingleClick() {
                ARouter.getInstance().build(WanAndroidRoutePath.WANANDROID_ACTIVITY).navigation();
                finish();
            }
        });
    }
}
