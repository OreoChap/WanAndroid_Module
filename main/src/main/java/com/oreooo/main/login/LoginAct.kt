package com.oreooo.main.login

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast

import com.alibaba.android.arouter.launcher.ARouter
import com.oreooo.baselibrary.mvpbase.StartActivity
import com.oreooo.baselibrary.route.RoutePath
import com.oreooo.main.R
import com.oreooo.main.databinding.ActLoginBinding
import com.oreooo.main.pojo.User

class LoginAct : StartActivity(), LoginContract.View {

    private val viewModel by lazy { ViewModelProviders.of(this).get(LoginModel::class.java) }
    private var mPresenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        val binding: ActLoginBinding = DataBindingUtil.setContentView(this, R.layout.act_login)
        binding.lifecycleOwner = this
        binding.model = viewModel
        initView()
    }

    private fun initView() {

    }

    override fun onStart() {
        super.onStart()
        subscribe()
        mPresenter!!.setView(this)
    }

    override fun onStop() {
        super.onStop()
        unsubscribe()
    }

    override fun loginResult(user: User?) {
        when(user?.errorCode?:1) {
            -1 -> {
                Toast.makeText(this,"登陆失败～",Toast.LENGTH_SHORT).show()
            }
            0 -> {
                Toast.makeText(this,"成功登陆～",Toast.LENGTH_SHORT).show()
                ARouter.getInstance().build(RoutePath.WANANDROID_ACTIVITY).navigation()
                finish()
            }
            1 -> {
                Toast.makeText(this,"跳过登陆～",Toast.LENGTH_SHORT).show()
                ARouter.getInstance().build(RoutePath.WANANDROID_ACTIVITY).navigation()
                finish()
            }
        }
    }

    override fun subscribe() {
        mPresenter = LoginPresenter.instance
    }

    override fun unsubscribe() {
        mPresenter = null
    }
}
