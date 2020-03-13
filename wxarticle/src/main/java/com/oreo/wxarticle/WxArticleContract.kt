package com.oreo.wxarticle

import com.oreo.wxarticle.pojo.ArticleClass
import com.oreooo.baselibrary.mvp.BaseContract
import com.oreooo.baselibrary.pojo.Article

interface WxArticleContract : BaseContract {
    interface View : BaseContract.BaseView {
        fun classRefresh(data: ArticleClass)
        fun keywordArticleRefresh(data: Article)
        fun AuthorArticleRefresh(data: Article)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun articleClassRequest()
        fun keywordArticleRequest(authorId: Int, articlePage: Int, keyWord: String)
        fun authorArticleRequest(authorId: Int, articlePage: Int)
    }
}