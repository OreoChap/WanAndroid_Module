package com.oreo.wxarticle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.oreo.wxarticle.databinding.ActWxarticleBinding
import com.oreooo.baselibrary.mvpbase.StartActivity
import com.oreooo.baselibrary.mvpbase.StartFragment
import kotlinx.android.synthetic.main.act_wxarticle.*

class WxArticleAct : StartActivity() {
    private var frags: MutableList<StartFragment> = ArrayList()

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

    private fun switchFrags(frag: StartFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (!frags.contains(frag)) {
            frags.add(frag)
        }
        transaction.replace(R.id.wxarticle_container, frag).commit()
    }
}