package com.oreooo.module_user

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.baselibrary.mvpbase.StartFragment
import com.oreooo.module_user.UserAct.Companion.mListResource
import kotlinx.android.synthetic.main.frag_collect_article.*
import java.util.*

class CollectArticleFragment : StartFragment() {

    override fun setContentView(): Int {
        return R.layout.frag_collect_article
    }

    override fun init(view: View, savedInstanceState: Bundle?) {
        collect_article_list.adapter = object : BaseRecyclerAdapter<CollectArticle>
        (context, mListResource, android.R.layout.simple_list_item_1, null) {
            override fun bindHolder(holder: BaseViewHolder?, item: CollectArticle?, position: Int) {
                val title = holder!!.getView<TextView>(android.R.id.text1)
                title.text = item?.title?:""
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