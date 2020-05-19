package com.oreooo.module_user

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.baselibrary.mvp.BaseFragment
import kotlinx.android.synthetic.main.frag_collect_article.*

class CollectArticleFragment : BaseFragment() {

    override fun setContentView(): Int {
        return R.layout.frag_collect_article
    }

    override fun init(view: View, savedInstanceState: Bundle?) {
        val testList = ArrayList<String>()
        testList.add("1")
        testList.add("2")
        testList.add("2")
        testList.add("2")
        testList.add("2")
        testList.add("2")
        testList.add("2")
        testList.add("2")
        testList.add("2")

        collect_article_list.adapter = object : BaseRecyclerAdapter<String>
        (context, testList, android.R.layout.simple_list_item_1, null) {
            override fun bindHolder(holder: BaseViewHolder?, item: String?, position: Int) {
                val title = holder!!.getView<TextView>(android.R.id.text1)
                title.text = item
            }
        }
    }

    companion object {
        @Volatile
        private var instance: CollectArticleFragment? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: CollectArticleFragment().also { instance = it }
        }
    }
}