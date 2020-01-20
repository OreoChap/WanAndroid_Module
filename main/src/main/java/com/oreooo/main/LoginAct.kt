package com.oreooo.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Button

import com.alibaba.android.arouter.launcher.ARouter
import com.oreooo.baselibrary.ButtonClickListener
import com.oreooo.baselibrary.MvpBase.BaseActivity
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath
import com.oreooo.main.databinding.ActLoginBinding
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseActivity(), LoginContract.View{

    private val viewModel by lazy { ViewModelProviders.of(this).get(LoginModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActLoginBinding = DataBindingUtil.setContentView(this, R.layout.act_login)
        binding.lifecycleOwner = this
        binding.model = viewModel
        ARouter.getInstance().inject(this)
        initView()
    }

    private fun initView() {

        btn_skipLogin.setOnClickListener(object : ButtonClickListener() {
            override fun onSingleClick() {
                ARouter.getInstance().build(WanAndroidRoutePath.WANANDROID_ACTIVITY).navigation()
                finish()
            }
        })
    }

    override fun loginResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
