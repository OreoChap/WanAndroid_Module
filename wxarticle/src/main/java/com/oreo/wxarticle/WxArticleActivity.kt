package com.oreo.wxarticle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.oreo.wxarticle.databinding.ActWxarticleBinding
import com.oreo.wxarticle.pojo.ArticleClassData
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.baselibrary.mvp.BaseActivity
import com.oreooo.baselibrary.mvp.BaseFragment
import kotlinx.android.synthetic.main.act_wxarticle.*

class WxArticleActivity : BaseActivity() {
    private var frags: MutableList<BaseFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActWxarticleBinding = ActWxarticleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        init(binding)
    }

    private fun init(binding: ActWxarticleBinding) {
        toolbar_wxarticle_navigation.visibility = View.GONE
        switchFrags(WxArticleFragment.instance)
    }

    private fun switchFrags(frag: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (!frags.contains(frag)) {
            frags.add(frag)
        }
        transaction.replace(R.id.wxarticle_container, frag).commit()
    }
}