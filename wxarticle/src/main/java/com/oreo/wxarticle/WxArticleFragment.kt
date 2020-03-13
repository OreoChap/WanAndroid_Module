package com.oreo.wxarticle

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.oreo.wxarticle.pojo.ArticleClass
import com.oreo.wxarticle.pojo.ArticleClassData
import com.oreo.wxarticle.webview.WebViewActivity
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.baselibrary.mvp.BaseFragment
import com.oreooo.baselibrary.pojo.Article
import com.oreooo.baselibrary.pojo.ArticleDatas
import com.oreooo.baselibrary.route.RoutePath
import kotlinx.android.synthetic.main.frag_wxarticle.*

@Route(path = RoutePath.WXARTICLE_FRAGMENT)
class WxArticleFragment : BaseFragment(), WxArticleContract.View {

    private var mPresenter: WxArticleContract.Presenter? = null

    private var articlePage: Int = 1
    private var authorId: Int = 0

    companion object {
        val instance: WxArticleFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            WxArticleFragment()
        }
    }

    override fun setContentView(): Int {
        return R.layout.frag_wxarticle
    }

    override fun init(view: View, savedInstanceState: Bundle?) {
        ARouter.init(Application())

//        (list_article as RecyclerView).layoutManager = LinearLayoutManager(context)
//
//        (list_article_class as RecyclerView).layoutManager = LinearLayoutManager(context)
    }

    override fun subscribe() {
        mPresenter = WxArticlePresenter.presenterFactory.makePresenter()
        mPresenter?.setView(this)
    }

    override fun unsubscribe() {
        mPresenter?.setView(null)
        mPresenter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    // 获取公众号列表，frag刚创建的时候，才会发起请求
    override fun classRefresh(data: ArticleClass) {
//        list_article_class.addItemDecoration(object : RecyclerView.ItemDecoration() {
//
//        })
        // 设置公众号class的排列为横向
        (list_article_class.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL

        list_article_class.adapter = object : BaseRecyclerAdapter<ArticleClassData>(context,
                data.data, R.layout.list_item_class, object : BaseRecyclerAdapter.OnViewHolderClickListener {
            override fun onClick(position: Int, view: View?) {
                // 判断是否同一个公众号，并发起请求
                val clazz = data.data[position]
                if (authorId == clazz.id) {
                    articlePage++
                } else {
                    authorId = clazz.id
                    articlePage = 1
                }
                mPresenter!!.authorArticleRequest(clazz.id, articlePage)
            }
        }) {
            override fun bindHolder(holder: BaseViewHolder?, item: ArticleClassData?, position: Int) {
                holder?.getView<TextView>(R.id.article_class_tv)?.text = item?.name
            }
        }
        mPresenter!!.authorArticleRequest(408, 1)
    }

    override fun AuthorArticleRefresh(data: Article) {
        var articleListAdapter: BaseRecyclerAdapter<ArticleDatas>?
        if (list_article.adapter == null) {
            articleListAdapter =
                    object : BaseRecyclerAdapter<ArticleDatas>(context, ArrayList(), R.layout.list_item_article,
                            object : BaseRecyclerAdapter.OnViewHolderClickListener {
                                override fun onClick(position: Int, view: View?) {
//                                    ARouter.getInstance().build(RoutePath.WEBVIEW_ACTIVITY)
//                                            .withString("webUrl", data.data.datas[position].link)
//                                            .navigation()

                                    val intent = Intent(context, WebViewActivity::class.java)
                                    intent.putExtra("webUrl", data.data.datas[position].link)
                                    startActivity(intent)
                                }
                            }) {
                        override fun bindHolder(holder: BaseViewHolder?, item: ArticleDatas?, position: Int) {
                            holder!!.getView<TextView>(R.id.txt_article_name).text = item!!.title
                            holder.getView<TextView>(R.id.txt_article_super_chapter_name).text = item.superChapterName
                            holder.getView<TextView>(R.id.txt_chapter_name).text = item.chapterName
                            holder.getView<TextView>(R.id.txt_article_author).text = item.author
                            holder.getView<TextView>(R.id.txt_article_nice_date).text = item.niceDate
                        }
                    }
            list_article.adapter = articleListAdapter
        } else {
            articleListAdapter = list_article.adapter as BaseRecyclerAdapter<ArticleDatas>
        }
        articleListAdapter.setNewData(data.data.datas)
        articleListAdapter.notifyDataSetChanged()
    }

    override fun keywordArticleRefresh(data: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        super.onStop()
        unsubscribe()
    }

    override fun onStart() {
        super.onStart()
        subscribe()
        mPresenter?.articleClassRequest()
                ?: (Toast.makeText(context, "error: articleClassRequest()", Toast.LENGTH_SHORT)).show()
    }
}