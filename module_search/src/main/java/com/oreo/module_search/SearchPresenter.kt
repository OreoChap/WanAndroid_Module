package com.oreo.module_search

import com.oreo.module_search.network.Api
import com.oreooo.baselibrary.mvpbase.BaseContract
import kotlinx.coroutines.*

class SearchPresenter : SearchContract.Presenter {

    private var mView: SearchContract.View? = null

    companion object {
        val instance: SearchPresenter by lazy {
            SearchPresenter()
        }
    }

    override fun getSearchArticle(page: Int, keyword: String) {
        GlobalScope.launch {
            val result = async {
                Api.create().getArticle(page, keyword)
            }
            withContext(Dispatchers.Main) {
                mView!!.showArticleResult(result.await())
            }
        }
    }

    override fun setView(view: BaseContract.BaseView?) {
        mView = view as? SearchContract.View
    }

    override fun clearRequest() {

    }
}