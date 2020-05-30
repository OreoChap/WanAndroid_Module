package com.oreooo.module_user

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.oreooo.baselibrary.newmvp.BaseAct
import com.oreooo.baselibrary.route.RoutePath
import kotlinx.android.synthetic.main.act_user.*

@Route(path = RoutePath.USER_ACTIVITY)
class UserAct : BaseAct<UserContract.Presenter>(), UserContract.View {

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)
        supportFragmentManager.beginTransaction()
                .replace(R.id.user_act_container, UserFragment.getInstance())
                .commit()
        toolbar_user_back.setOnClickListener { finish() }
    }

    override fun collectArticleRefresh(data: List<CollectArticle>) {

    }

    override fun setPresenter(): UserContract.Presenter {
        return UserPresenter.getInstance()
    }

    override fun onBackPressed() {
        finish()
    }
}