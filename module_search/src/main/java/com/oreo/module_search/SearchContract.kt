package com.oreo.module_search

import com.oreooo.baselibrary.mvpbase.BaseContract
import com.oreooo.baselibrary.pojo.Article

interface SearchContract {
    interface View : BaseContract.BaseView {
        fun showArticleResult(data: Article)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getSearchArticle(page: Int, keyword: String)
    }
}