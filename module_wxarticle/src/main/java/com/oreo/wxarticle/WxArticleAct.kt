package com.oreo.wxarticle

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.oreo.module_wxarticle.R
import com.oreo.module_wxarticle.databinding.ActWxarticleBinding
import com.oreooo.baselibrary.mvpbase.BaseActivity
import com.oreooo.baselibrary.mvpbase.BaseFragment
import kotlinx.android.synthetic.main.act_wxarticle.*

class WxArticleAct : BaseActivity() {
    private var frags: MutableList<BaseFragment> = ArrayList()

    companion object {
        lateinit var mContext: Context
    }

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