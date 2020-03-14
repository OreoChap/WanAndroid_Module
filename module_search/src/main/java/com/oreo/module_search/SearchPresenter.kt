package com.oreo.module_search

import com.oreo.module_search.network.Api
import com.oreooo.baselibrary.mvp.BaseContract
import kotlinx.coroutines.*

class SearchPresenter : com.oreo.module_search.SearchContract.Presenter {

    private var mView: com.oreo.module_search.SearchContract.View? = null

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
        mView = view as? com.oreo.module_search.SearchContract.View
    }

    override fun clearRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}