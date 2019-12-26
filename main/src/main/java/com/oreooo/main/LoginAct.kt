package com.oreooo.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Button

import com.alibaba.android.arouter.launcher.ARouter
import com.oreooo.baselibrary.ButtonClickListener
import com.oreooo.baselibrary.MvpBase.BaseActivity
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath
import com.oreooo.main.databinding.ActLoginBinding

class LoginAct : BaseActivity() {

    internal var btn_skipLogin: Button
    internal var mActLoginBinding: ActLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.act_login);
        mActLoginBinding = DataBindingUtil.setContentView(this, R.layout.act_login)
        ARouter.getInstance().inject(this)
        initView()
    }

    private fun initView() {
        btn_skipLogin = findViewById(R.id.btn_skipLogin)
        btn_skipLogin.setOnClickListener(object : ButtonClickListener() {
            override fun onSingleClick() {
                ARouter.getInstance().build(WanAndroidRoutePath.WANANDROID_ACTIVITY).navigation()
                finish()
            }
        })
    }

}
