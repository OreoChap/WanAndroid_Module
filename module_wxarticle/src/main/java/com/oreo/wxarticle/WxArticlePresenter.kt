package com.oreo.wxarticle

import com.oreo.wxarticle.network.Api
import com.oreooo.baselibrary.mvpbase.BaseContract
import kotlinx.coroutines.*

/**
 *  kotlin Coroutines + retrofit
 */
class WxArticlePresenter : WxArticleContract.Presenter {

    private lateinit var mView: WxArticleContract.View

    companion object {
        val instance: WxArticlePresenter by lazy {
            WxArticlePresenter()
        }
    }

    override fun setView(view: BaseContract.BaseView?) {
        mView = view as WxArticleContract.View
    }

    override fun clearRequest() {
    }

    override fun articleClassRequest() {
        // 协程
        CoroutineScope(Dispatchers.IO).launch {
            val result = async {
                Api.create().getArticleClass()
            }
            // 主线程中运行
            withContext(Dispatchers.Main) {
                mView.classRefresh(result.await())
            }
        }
    }

    override fun keywordArticleRequest(authorId: Int, articlePage: Int, keyWord: String) {
        // 协程
        GlobalScope.launch {
            val result = async {
                Api.create().getKeywordArticle(authorId, articlePage, keyWord)
            }
            withContext(Dispatchers.Main) {
                mView.keywordArticleRefresh(result.await())
            }
        }
    }

    override fun authorArticleRequest(authorId: Int, articlePage: Int) {
        GlobalScope.launch {
            val result = async {
                Api.create().getAuthorArticle(authorId, articlePage)
            }
            withContext(Dispatchers.Main) {
                mView.AuthorArticleRefresh(result.await())
            }
        }
    }
}