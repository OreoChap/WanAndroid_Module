package com.oreooo.module_user

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import com.oreooo.baselibrary.mvpbase.StartFragment
import kotlinx.android.synthetic.main.frag_user.*

class UserFragment : StartFragment() {

    private lateinit var pagerAdapter: FragmentStatePagerAdapter
    private val pagerList: MutableList<StartFragment> = ArrayList()

    override fun setContentView(): Int {
        return R.layout.frag_user
    }

    override fun init(view: View, savedInstanceState: Bundle?) {
        pagerList.clear()
        pagerList.add(CollectArticleFragment.getInstance())

        userCollect_viewPager.adapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return pagerList[position]
            }

            override fun getCount(): Int {
                return pagerList.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                var title: String = ""
                when (position) {
                    0 -> title = "收藏文章"
                    1 -> title = "收藏网站"
                }
                return title
            }
        }
        UserPresenter.getInstance().getCollectArticle(0)
    }

    companion object {
        @Volatile
        private var instance: UserFragment? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: UserFragment().also { instance = it }
        }
    }
}