package com.oreooo.module_user

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.oreooo.baselibrary.newmvp.BaseAct
import com.oreooo.baselibrary.route.RoutePath
import kotlinx.android.synthetic.main.act_user.*
import kotlinx.android.synthetic.main.frag_collect_article.*

@Route(path = RoutePath.USER_ACTIVITY)
class UserAct : BaseAct<UserContract.Presenter>(), UserContract.View {

    companion object {
        lateinit var mContext: Context

        @Volatile
        var mListResource = java.util.ArrayList<CollectArticle>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)

        UserPresenter.getInstance().getCollectArticle(0)

        supportFragmentManager.beginTransaction()
                .replace(R.id.user_act_container, UserFragment.getInstance())
                .commit()
        
        initListener()
    }

    private fun initListener() {
        toolbar_user_back.setOnClickListener { finish() }
    }

    override fun collectArticleRefresh(data: List<CollectArticle>) {
        mListResource = data as ArrayList
        CollectArticleFragment.getInstance().collect_article_list.adapter?.notifyDataSetChanged()
    }

    override fun setPresenter(): UserContract.Presenter {
        return UserPresenter.getInstance()
    }

    override fun onBackPressed() {
        finish()
    }
}