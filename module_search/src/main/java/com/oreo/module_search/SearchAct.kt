package com.oreo.module_search

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.baselibrary.mvpbase.StartActivity
import com.oreooo.baselibrary.pojo.Article
import com.oreooo.baselibrary.pojo.ArticleDatas
import com.oreooo.baselibrary.route.RoutePath
import com.oreooo.baselibrary.util.StringUtil
import kotlinx.android.synthetic.main.act_search.*

@Route(path = RoutePath.SEARCH_ACTIVITY)
class SearchAct : StartActivity(), SearchContract.View {

    private var mPresenter: SearchContract.Presenter? = null

    // 页码， 上划加载更多时使用
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_search)
        init()
    }

    fun init() {
        search_btn.setOnClickListener {
            val keyword = edit_text_search.text.toString()
            if (!StringUtil.isEmpty(keyword)) {
                mPresenter?.getSearchArticle(0, keyword)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        subscribe()
    }

    override fun onStop() {
        super.onStop()
        unsubscribe()
    }

    override fun showArticleResult(data: Article) {
        if (recycler_search.adapter == null) {
            val adapter: BaseRecyclerAdapter<ArticleDatas> = object : BaseRecyclerAdapter<ArticleDatas>
            (this, data.data.datas, R.layout.list_item_article, null) {
                override fun bindHolder(holder: BaseViewHolder?, item: ArticleDatas?, position: Int) {
                    holder?.getView<TextView>(R.id.txt_article_name)?.text =
                            Html.fromHtml("《" + item!!.title + "》")
                }
            }
            recycler_search.adapter = adapter
        } else {
            val adapter = recycler_search.adapter as BaseRecyclerAdapter<ArticleDatas>
            adapter.setNewData(data.data.datas)
            adapter.notifyDataSetChanged()
        }
    }

    override fun subscribe() {
        mPresenter = SearchPresenter.instance
        mPresenter?.setView(this)
    }

    override fun unsubscribe() {
        mPresenter = null
    }
}